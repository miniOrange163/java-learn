package pers.lin.zookeeper;

import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import pers.lin.zookeeper.service.ZooKeeperService;
import pers.lin.zookeeper.service.impl.ZooKeeperServiceImpl;

import java.util.List;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/3/16
 *  
 * @name: ZooKeeper客户端API模拟示例
 *
 * @Description: 
 */
public class TestZookeeper {


    private ZooKeeperService service;

    public String node = "/java";

    /**
     * 设置节点
     * @throws Exception
     */
    @Test
    public void setData() throws Exception {
        service = new ZooKeeperServiceImpl();
        Stat stat = service.setData(node, "hello world!!!".getBytes(), -1);

        System.out.println(stat);
    }
    /**
     * 获取节点的内容
     * @throws Exception
     */
    @Test
    public void get() throws Exception {

        service = new ZooKeeperServiceImpl();
        // 存在 ，返回实体
        Stat exists = service.exists(node, false);
        if (exists != null) {

            byte[] data = service.getData(node, null, null);
            System.out.println(new String(data));
        }

    }
    /**
     * 获取节点的子节点列表
     * @throws Exception
     */
    @Test
    public void getChild() throws Exception {
        service = new ZooKeeperServiceImpl();

        List<String> children = service.getChildren(node, null);

        for (String child : children) {

            System.out.println(child);
        }
        // 如不存在节点，
        // 会抛出异常：org.apache.zookeeper.KeeperException$NoNodeException: KeeperErrorCode = NoNode for /max
        List<String> children1 = service.getChildren("/max", null);

        System.out.println(123);

    }

    /**
     * 删除节点
     * @throws Exception
     */
    @Test
    public void delete() throws Exception {

        service = new ZooKeeperServiceImpl();
        // 规则一：如不存在节点，
        // 会抛出异常：org.apache.zookeeper.KeeperException$NoNodeException: KeeperErrorCode = NoNode for /java/child-3

        // 规则二：version取值应与当前节点的dataVersion相等，否则无法删除数据
        // 会抛出异常：org.apache.zookeeper.KeeperException$BadVersionException: KeeperErrorCode = BadVersion for /java/child-1

        // 规则三：version取值-1，则不会校验dataVersion是否相等，直接删除节点

        // 规则四：如果节点存在子节点，则无法删除
        // 会抛出异常：org.apache.zookeeper.KeeperException$NotEmptyException: KeeperErrorCode = Directory not empty for /java
        service.delete("/java",-1);

        System.out.println(123);

    }

    /**
     * 创建节点
     * @throws Exception
     */
    @Test
    public void create() throws Exception {

        service = new ZooKeeperServiceImpl();
        Stat exists = service.exists(node, false);
        if (exists == null) {
            String hello_world = service.createNode(node, "hello world");
            System.out.println(hello_world);

        }
        else{
            String child1 = service.createNode(node+"/child-1", "child-1");
            String child2 = service.createNode(node+"/child-2", "child-2");
            String child3 = service.createNode(node+"/child-3", "child-3");
            System.out.println(child1);
            System.out.println(child2);
            System.out.println(child3);

        }

    }

    /**
     * 查看节点状态
     * @throws Exception
     */
    @Test
    public void exists() throws Exception {

        service = new ZooKeeperServiceImpl();
        // 存在 ，返回实体
        Stat exists = service.exists(node, false);
        // 不存在，返回null
        Stat exists3 = service.exists("/aabb", false);

        System.out.println(111);

    }


}
