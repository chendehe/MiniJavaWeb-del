package com.chendehe.zk.lock;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.I0Itec.zkclient.ZkClient;

public class SimpleDistributedLockMutex extends BaseDistributedLock implements
    DistributedLock {

  //锁名称前缀,成功创建的顺序节点如lock-0000000000,lock-0000000001,...
  private static final String LOCK_NAME = "lock-";

  // zookeeper中locker节点的路径
  private final String basePath;

  // 获取锁以后自己创建的那个顺序节点的路径
  private String ourLockPath;

  private boolean internalLock(long time, TimeUnit unit) throws Exception {
    ourLockPath = attemptLock(time, unit);
    return ourLockPath != null;
  }

  public SimpleDistributedLockMutex(ZkClient client, String basePath) {
    super(client, basePath, LOCK_NAME);
    this.basePath = basePath;
  }

  // 获取锁
  @Override
  public void acquire() throws Exception {
    if (!internalLock(-1, null)) {
      throw new IOException("连接丢失!在路径:'" + basePath + "'下不能获取锁!");
    }
  }

  // 获取锁，可以超时
  @Override
  public boolean acquire(long time, TimeUnit unit) throws Exception {
    return internalLock(time, unit);
  }

  // 释放锁
  @Override
  public void release() throws Exception {
    releaseLock(ourLockPath);
  }

}