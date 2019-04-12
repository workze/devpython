package com.ze.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by ZE-C on 2017/11/19.
 */
public class TestZookeeper {
    private static final int TIME_OUT = 3000;

    private static String HOST = "192.168.2.10:2181";

    public static void main(String[] args) {
        try {
            ZooKeeper zookeeper = new ZooKeeper(HOST, TIME_OUT, null);
            System.out.println("=========创建节点===========");
            if(zookeeper.exists("/test", false) == null)
            {
                zookeeper.create("/test", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println("=============查看节点是否安装成功===============");
            System.out.println(new String(zookeeper.getData("/test", false, null)));

            System.out.println("=========修改节点的数据==========");
            String data = "zNode2";
            zookeeper.setData("/test", data.getBytes(), -1);

            System.out.println("========查看修改的节点是否成功=========");
            System.out.println(new String(zookeeper.getData("/test", false, null)));

            System.out.println("=======删除节点==========");
            zookeeper.delete("/test", -1);

            System.out.println("==========查看节点是否被删除============");
            System.out.println("节点状态：" + zookeeper.exists("/test", false));

            zookeeper.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
