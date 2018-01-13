package com.chendehe.zk;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class ZkConfMgr {

  private static final String URL = "localhost:2181,localhost:2182,localhost:2183";
  private static final int TIME_OUT = 5000;
  private static final String ROOT = "/myConf";
  // 数据库url name pwd
  private static final String URL_NODE = ROOT + "/url";
  private static final String USERNAME_NODE = ROOT + "/username";
  private static final String PWD_NODE = ROOT + "/password";
  private static final String ROOT_NAME = "root";
  private static final String URL_DB = "10.1.1.2:8594";
  private static final String USERNAME_DB = "username123";
  private static final String PWD_DB = "password123";

  private static final String AUTH_TYPE = "digest";
  private static final String ADMIN = "admin:admin123";
  private static final String GUEST = "guest:guest1";

  private static final boolean IS_WARCH = true;

  public static void main(String[] args)
      throws IOException, InterruptedException, KeeperException, NoSuchAlgorithmException {
    System.out.println("Start");

    ZooKeeper zk = getZk();
    addAuth(zk);
    del(zk);
    init(zk);

    for (int i = 0; i < 10; i++) {
      print(zk);
      Thread.sleep(TIME_OUT);
    }

    System.out.println("End" + zk.exists(ROOT, IS_WARCH));
  }

  private static void print(ZooKeeper zk) throws KeeperException, InterruptedException {
    System.out.println("1==>" + new String(zk.getData(URL_NODE, IS_WARCH, null)));
    System.out.println("2==>" + new String(zk.getData(USERNAME_NODE, IS_WARCH, null)));
    System.out.println("3==>" + new String(zk.getData(PWD_NODE, IS_WARCH, null)));
  }

  private static void addAuth(ZooKeeper zk) throws NoSuchAlgorithmException {
    zk.addAuthInfo(AUTH_TYPE, ADMIN.getBytes());
    zk.addAuthInfo(AUTH_TYPE, DigestAuthenticationProvider.generateDigest(GUEST).getBytes());
  }

  private static void del(ZooKeeper zk) throws InterruptedException, KeeperException {
    zk.delete(URL_NODE, -1);
    zk.delete(USERNAME_NODE, -1);
    zk.delete(PWD_NODE, -1);
    zk.delete(ROOT, -1);
  }

  private static void init(ZooKeeper zk) throws KeeperException, InterruptedException {
    if (null == zk.exists(ROOT, IS_WARCH)) {
      zk.create(ROOT, ROOT_NAME.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }
    if (null == zk.exists(URL_NODE, IS_WARCH)) {
      zk.create(URL_NODE, URL_DB.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }
    if (null == zk.exists(USERNAME_NODE, IS_WARCH)) {
      zk.create(USERNAME_NODE, USERNAME_DB.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }
    if (null == zk.exists(PWD_NODE, IS_WARCH)) {
      zk.create(PWD_NODE, PWD_DB.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }
  }

  private static ZooKeeper getZk() throws IOException, InterruptedException {
    // 监控所有被触发的事件
    ZooKeeper zk = new ZooKeeper(URL, TIME_OUT, getWatcher());

    while (States.CONNECTED != zk.getState()) {
      Thread.sleep(TIME_OUT);
    }
    return zk;
  }

  private static Watcher getWatcher() {
    return event -> {
      EventType eventType = event.getType();
      if (EventType.None == eventType) {
        System.out.println("连接服务器！");
      } else if (EventType.NodeCreated == eventType) {
        System.out.println("创建节点！");
      } else if (EventType.NodeChildrenChanged == eventType) {
        System.out.println("更新子节点！");
      } else if (EventType.NodeDataChanged == eventType) {
        System.out.println("更新节点！");
      } else if (EventType.NodeDeleted == eventType) {
        System.out.println("删除节点！");
      }
    };
  }

  // 查看权限 getAcl /myConf
  // 获得权限 addauth digest admin:admin123
  // 查看子节点 ls /myConf
  // 获取值 get /myConf
  // 修改节点 set /myConf/username username321
  // 创建节点 create /Mutex mutex
  // 删除节点 delete /Mutex/lock- //rmr
  //  ZooKeeper -server host:port cmd args
  //  stat path [watch]
  //  set path data [version]
  //  ls path [watch]
  //  delquota [-n|-b] path
  //  ls2 path [watch]
  //  setAcl path acl
  //  setquota -n|-b val path
  //  history
  //  redo cmdno
  //  printwatches on|off
  //  delete path [version]
  //  sync path
  //  listquota path
  //  rmr path
  //  get path [watch]
  //  create [-s] [-e] path data acl
  //  addauth scheme auth
  //  quit
  //  getAcl path
  //  close
  //  connect host:port
}
