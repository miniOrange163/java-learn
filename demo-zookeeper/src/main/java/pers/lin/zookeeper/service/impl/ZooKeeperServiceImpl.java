package pers.lin.zookeeper.service.impl;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.lin.zookeeper.service.ZooKeeperConnection;
import pers.lin.zookeeper.service.ZooKeeperService;

import java.io.IOException;
import java.util.List;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/3/16
 *  
 * @name: 
 *
 * @Description: 
 */
public class ZooKeeperServiceImpl implements ZooKeeperService {

    private Logger logger = LoggerFactory.getLogger(ZooKeeperServiceImpl.class);

    private ZooKeeper zooKeeper;

    {
        try {
            zooKeeper = ZooKeeperConnection.instance().getZooKeeper();
        } catch (IOException e) {
            logger.error(e.toString(),e);
        }
    };

    @Override
    public String createNode(String path, String data) throws Exception {

        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    }

    @Override
    public Stat exists(String path, boolean watcher) throws KeeperException, InterruptedException {

        Stat exists = zooKeeper.exists(path, watcher);

        return exists;
    }

    @Override
    public void delete(String path, int version) throws Exception {

        zooKeeper.delete(path,version);
    }

    @Override
    public byte[] getData(String path, Watcher watcher, Stat stat) throws Exception {

        byte[] data = zooKeeper.getData(path, watcher, stat);
        return data;
    }

    @Override
    public List<String> getChildren(String path, Watcher watcher) throws Exception {

        List<String> children = zooKeeper.getChildren(path, watcher);
        return children;
    }

    @Override
    public Stat setData(String path, byte[] data, int version) throws Exception {
        Stat stat = zooKeeper.setData(path, data, version);
        return stat;
    }

}
