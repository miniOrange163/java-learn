package pers.lin.zookeeper.service;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/3/16
 *  
 * @name: 
 *
 * @Description: 
 */
public class ZooKeeperWatcher implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(ZooKeeperConnection.class);

    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        if (state == Event.KeeperState.SyncConnected) {
            logger.info("创建连接成功");

        }
    }
}
