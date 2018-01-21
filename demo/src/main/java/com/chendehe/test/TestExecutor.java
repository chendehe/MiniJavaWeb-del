package com.chendehe.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutor {

  public static void main(String[] args) {
    System.out.println("Start");
    System.out.println("End1" + Thread.activeCount());
    CyclicBarrier barrier = new CyclicBarrier(4);
    System.out.println("End1" + Thread.activeCount());

    ExecutorService exe = Executors.newFixedThreadPool(2);
    System.out.println("End1" + Thread.activeCount());
    Future<?> f1 = exe.submit(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 5; i++) {
          System.out.println(Thread.currentThread() + " : " + i);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
//          if (1 == i) {
//            throw new NullPointerException();
//          }
        }
        try {
          barrier.await();
          System.out.println(barrier.getNumberWaiting());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      }
    });
    Future<?> f2 = exe.submit(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 5; i++) {
          System.out.println(Thread.currentThread() + " : " + i);
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        try {
          barrier.await();
          System.out.println(barrier.getNumberWaiting());
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (BrokenBarrierException e) {
          e.printStackTrace();
        }
      }
    });

    try {
      barrier.await();
      System.out.println(barrier.getNumberWaiting());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
    System.out.println("End1" + Thread.activeCount());
    try {
      f1.get();
      f2.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      System.out.println(exe.isShutdown());
      System.out.println(exe.isTerminated());
      exe.shutdownNow();
      System.out.println(exe.isShutdown());
      System.out.println(exe.isTerminated());
    }
    System.out.println("End2");
    System.out.println("End3");
    System.out.println("End4");

  }
}
