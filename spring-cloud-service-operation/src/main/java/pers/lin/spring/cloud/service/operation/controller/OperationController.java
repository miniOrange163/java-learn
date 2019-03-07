package pers.lin.spring.cloud.service.operation.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lin.spring.cloud.service.operation.entity.OperEntity;
import pers.lin.spring.cloud.service.operation.entity.PortInfoEntity;
import pers.lin.spring.cloud.service.operation.entity.ResultEntity;
import pers.lin.spring.cloud.service.operation.entity.TopResultEntity;
import pers.lin.spring.cloud.service.operation.service.OperationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author: 林嘉宝
 * @Date: 2019/1/16
 * @name:
 * @Description:
 */
@RestController
@RequestMapping("operation/")
public class OperationController {

    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String name;


    protected final static Pattern filePattern = Pattern.compile("^[1-9]+0|[.]+[0-9]*$");

    @Resource(name = "ribbonService")
    private OperationService ribbonService;
    @Resource(name = "feignService")
    private OperationService feignService;

    private Map<Character, Character> operMap = initOperMap();
    private Map<String, String> doOperMap = initDoOperMap();

    private Map<String,String> initDoOperMap() {

        Map<String, String> map = new HashMap<>();
        map.put("*", "*");
        map.put("/", "/");
        map.put(")", ")");

        return map;
    }

    protected final static Pattern checkNum = Pattern.compile("[0-9]+");

    protected final static Pattern checkOper = Pattern.compile("\\+|\\-");


    private Map<Character, Character> initOperMap() {
        Map<Character, Character> map = new HashMap<>();
        map.put('*', '*');
        map.put('/', '/');
        map.put('+', '+');
        map.put('-', '-');
        map.put('(', '(');
        map.put(')', ')');
        return map;
    }
    @RequestMapping(value = "name")
    public String name(HttpServletRequest request) {
        return name + "-" + port;

    }

    @RequestMapping(value = "port")
    public int port(HttpServletRequest request) {

        return Integer.parseInt(port);

    }
    @RequestMapping(value = "operPort")
    public PortInfoEntity operPort(HttpServletRequest request) {

        PortInfoEntity portInfo = ribbonService.port();
        portInfo.setMyPort(Integer.parseInt(port));
        return portInfo;

    }

    private TopResultEntity getErrorResult(String msg){
        TopResultEntity result = new TopResultEntity();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }
    @RequestMapping(value = "oper")
    public TopResultEntity oper(@RequestBody OperEntity entity) {
        if (entity == null) {
            return getErrorResult("请求参数为空");
        }
        if (StringUtils.isBlank(entity.getOperation())) {
            return getErrorResult("operation参数为空");
        }

        TopResultEntity result = computeTopResultEntity(entity.getOperation().toCharArray(),ribbonService);

        return result;
    }

    @RequestMapping(value = "operByFeign")
    public TopResultEntity operByFeign(@RequestBody OperEntity entity) {
        if (entity == null) {
            return getErrorResult("请求参数为空");
        }
        if (StringUtils.isBlank(entity.getOperation())) {
            return getErrorResult("operation参数为空");
        }

        TopResultEntity result = computeTopResultEntity(entity.getOperation().toCharArray(),feignService);

        return result;
    }

    /**
     * 从字符队列最左开始，取第一个字符串
     * 如(20*(15+5*3))/5的队列，取值依次是(、20、*、(、15、+、5、3、)、)、/、5
     * @param charQueue
     * @return
     */
    private String popStringFromCharQueue(LinkedList<Character> charQueue){
        // 声明一个List，需要返回的字符，添加到此List中
        List<Character> cList = new ArrayList<>();

        Character character = charQueue.pollFirst();
        while (character != null) {
            Character c = operMap.get(character);
            if (c == null) {
                // 数字,加入List
                cList.add(character);
            }
            else{
                // + - * / ( ) 中的一个
                if (cList.isEmpty()) {
                    // cList没有添加过数值
                    // 运算符加入到cList中直接返回
                    cList.add(character);
                    break;
                }
                else{
                    // cList已经添加过数值，将运算符加回到队列中
                    // 跳出循环，返回cList里的数值
                    charQueue.addFirst(character);
                    break;
                }
            }
            character = charQueue.pollFirst();
        }
        if (cList.isEmpty()) {
            return null;
        }
        // List转char数组
        char[] cArray = new char[cList.size()];
        for (int i = cList.size()-1; i >=0 ; i--) {
            cArray[i] = cList.get(i);
        }
        return new String(cArray);
    }


    private TopResultEntity computeTopResultEntity(char[] chars,OperationService operationService) {

        String result = compute(chars,operationService);
        boolean flag = false;
        String message = "计算失败";
        if (StringUtils.isNotBlank(result)) {
            flag = true;
            message = "计算成功";
        }
        TopResultEntity resultEntity = new TopResultEntity();
        resultEntity.setSuccess(flag);
        resultEntity.setMessage(message);
        resultEntity.setOperationName(name + "-" + port);
        resultEntity.setOperation(new String(chars));
        resultEntity.setResult(Double.parseDouble(result));

        return resultEntity;
    }

    /**
     * 如何计算一个表达式的具体逻辑，格式 ：(20*(15+5*3))/5
     * @param chars 字符数组
     * @param operationService  调用计算服务的方式（ribbon和feign两种方式）
     * @return
     */
    private String compute(char[] chars,OperationService operationService) {
        // 声明一个字符串队列，暂存需要运算的字符串
        LinkedList<String> operList = new LinkedList();
        // 声明一个字符队列，用char[]转换
        LinkedList<Character> charQueue = new LinkedList();
        // char[]转换成队列
        for (char c : chars) {
            charQueue.add(c);
        }
        /**
         * 基本的处理逻辑：
         * 1.从字符队列最左端开始，依次取出一个完整的字符串，放在到字符串队列最右端
         * 2.如果读到了'*'或'/'，则马上从字符串队列最右端取出一个字符串，从字符队列最左端再取一个完整的字符串，
         *   直接计算结果，放入到字符串队列最右端
         * 3.如果读到了')'，则从字符串队列最右端开始依次读取字符串，直到读到'('为止，计算从'('到')'的结果，放入到字符串队列最右端
         * 4.字符队列读取完之后，遍历字符串队列，将队列中+或-的运算计算处理完整
         * 5.此时，即得到了最后的结果，返回。
         */

        String var1 = null;
        // 从字符队列最左开始，取第一个字符串
        while ((var1 = popStringFromCharQueue(charQueue)) != null) {

            if ("*".equals(var1) || "/".equals(var1)) {
                // 从字符串队列最右端取一个字符串
                String a = operList.pollLast();
                // 从字符队列最左端取一个字符串
                String b = popStringFromCharQueue(charQueue);
                if ("(".equals(b)) {
                    // 读到了'(' ，则优先计算字符队列中'(' 到 ')' 结果
                    int num = 0;
                    // 用于保存从'('到')'的字符串
                    List<Character> cList = new ArrayList<>();
                    while (true) {
                        // 继续读取字符队列
                        Character c = charQueue.poll();
                        if (c == null) {
                            // 读取为null，即跳出循环
                            break;
                        }
                        if (c == '(') {
                            // 读取到了嵌套内的'(',计数加1
                            num++;
                            cList.add(c);
                        } else if (c == ')') {
                            // 读取到')'，判断计数是否为0
                            if (num > 0) {
                                // 计数大于0 ，说明这不是最后一个')'
                                num--;
                                cList.add(c);
                            }
                            else{
                                // 计数为0 ，说明已经是最后一个')'，跳出循环
                                break;
                            }
                        }else {
                            cList.add(c);
                        }
                    }
                    char[] cArray = new char[cList.size()];
                    for (int i = cList.size()-1; i >=0 ; i--) {
                        cArray[i] = cList.get(i);
                    }
                    // 递归计算'(' 到 ')' 之间的表达式结果
                    b = compute(cArray,operationService);
                }
                // 计算表达式的结果
                String result = compute(a, b, var1,operationService);
                if (StringUtils.isNotBlank(result)) {
                    operList.add(result);
                }
            } else if (")".equals(var1)) {
                LinkedList<String> tmpList = new LinkedList();
                String var2 = null;
                // 读取到了')'，从字符串队列最右端开始，读取直到'('之间的字符串数组
                while ((var2 = operList.pollLast()) != null) {
                    if ("(".equals(var2)) {
                        break;
                    }
                    tmpList.push(var2);
                }
                // 计算'(' 到 ')' 之间的结果
                String result =  compute(tmpList,operationService);
                operList.add(result);
            }
            else{
                // 不是'*'不是'/'也不是')'，加入到字符串队列中
                operList.add(var1);
            }
        }
        // 将队列中'+'或'-'的运算计算处理完整,再进行一次计算
        String result =  compute(operList,operationService);

        return result;

    }

    /**
     *
     * @param tmpList 需要计算的数组
     * @param operationService 调用计算服务的方式（ribbon和feign两种方式）
     * @return 计算结果
     */
    private String compute(LinkedList<String> tmpList,OperationService operationService) {

        while (tmpList.size() > 1) {
            // 一次从字符串队列中取三个字符串，用于计算
            String a = tmpList.poll();
            String o = tmpList.poll();
            String b = tmpList.poll();
            String result = compute(a,b,o,operationService);
            if (StringUtils.isNotBlank(result)) {
                tmpList.addFirst(result);
            }
            else{
                return null;
            }
        }

        return tmpList.pop();
    }

    /**
     *
     * @param a 第一个数
     * @param b 第二个数
     * @param o 运算符
     * @param operationService 调用计算服务的方式（ribbon和feign两种方式）
     * @return 计算结果
     */
    private String compute(String a, String b, String o,OperationService operationService) {

        ResultEntity result = null;
        switch (o) {
            case "+":result = operationService.add(a, b);break;
            case "-":result = operationService.substract(a,b);break;
            case "*":result = operationService.multiply(a,b);break;
            case "/":result = operationService.divide(a,b);break;
            default:break;
        }
        return result.getResult().toString();
    }


}
