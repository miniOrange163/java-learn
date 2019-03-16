package pers.lin.zookeeper.service;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: 林嘉宝
 *  
 * @Date: 2019/3/16
 *  
 * @name: 
 *
 * @Description: 
 */
public class ZooKeeperConnection implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(ZooKeeperConnection.class);

    private volatile static ZooKeeperConnection connection ;

    private static ZooKeeper zookeeper;

    private static boolean pass = false;

    private static String host = "192.168.9.131:2181";

    private ZooKeeperConnection(){

    }

    public ZooKeeper getZooKeeper(){
        return zookeeper;
    }

    public static ZooKeeperConnection instance() throws IOException {

        if (connection == null || zookeeper == null) {
            synchronized (ZooKeeperConnection.class) {
                if (connection == null) {
                    connection = new ZooKeeperConnection();
                    zookeeper = new ZooKeeper(host,5000,connection);
                    while (true){

                        if (pass) {
                            break;
                        }
                        logger.info("检测连接状态："+pass);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            logger.error(e.toString(),e);
                        }
                    }
                }
            }
        }

        return connection;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        if (state == Event.KeeperState.SyncConnected) {

            pass = true;
            logger.info("创建连接成功，host:"+host);
        }

    }
}
