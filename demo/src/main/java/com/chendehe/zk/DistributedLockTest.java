package com.chendehe.zk;

import com.chendehe.zk.lock.SimpleDistributedLockMutex;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;

public class DistributedLockTest {

  private static final String URL1 = "localhost:2181";
  private static final String URL2 = "localhost:2182,localhost:2183";
  private static final int TIME_OUT = 5000;

  public static void main(String[] args) {

    final ZkClient zkClient1 =
        new ZkClient(URL1, TIME_OUT, TIME_OUT, new BytesPushThroughSerializer());
    final SimpleDistributedLockMutex mutex1 =
        new SimpleDistributedLockMutex(zkClient1, "/Mutex");

    final ZkClient zkClient2 =
        new ZkClient(URL2, TIME_OUT, TIME_OUT, new BytesPushThroughSerializer());
    final SimpleDistributedLockMutex mutex2 =
        new SimpleDistributedLockMutex(zkClient2, "/Mutex");

    try {
      mutex1.acquire();
      System.out.println("Client1 locked");
      Thread client2Thd = new Thread(() -> {
        try {
          mutex2.acquire();
          System.out.println("Client2 locked");
          mutex2.release();
          System.out.println("Client2 released lock");

        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      client2Thd.start();
      Thread.sleep(TIME_OUT);
      mutex1.release();
      System.out.println("Client1 released lock");

      client2Thd.join();

    } catch (Exception e) {

      e.printStackTrace();
    }

  }

}