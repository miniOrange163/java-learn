package pers.lin.zookeeper.service;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @author: 林嘉宝
 * @Date: 2019/3/16
 * @name:
 * @Description:
 */
public interface ZooKeeperService {


    String createNode(String path,String data) throws Exception;

    Stat exists(String path, boolean watcher) throws KeeperException, InterruptedException;

    void delete(String path,int version) throws Exception;

    byte[] getData(String path, Watcher watcher, Stat stat) throws Exception;

    List<String> getChildren(String path, Watcher watcher) throws Exception;

    Stat setData(String path, byte[] data, int version) throws Exception;
}
