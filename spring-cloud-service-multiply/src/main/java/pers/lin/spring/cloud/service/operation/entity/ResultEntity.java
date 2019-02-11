package pers.lin.spring.cloud.service.operation.entity;
/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/1/31
 *  
 * @name: 
 *
 * @Description: 
 */
public class ResultEntity {

    private Double result;

    private int port;

    private String name;

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
