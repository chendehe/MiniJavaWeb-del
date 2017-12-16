package com.chendehe.test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestThread {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println("start");
    CyclicBarrier barrier = new CyclicBarrier(3);

    new Thread(getTarget(barrier), "First").start();
    System.out.println("First... end");

    new Thread(getTarget(barrier), "Second").start();
    System.out.println("Second... end");

//    try {
//      barrier.await();
//    } catch (InterruptedException | BrokenBarrierException e) {
//      e.printStackTrace();
//    }
    FutureTask<String> f1 = new FutureTask<>(() -> {
      futureRun();
      return "f11111";
    });
    new Thread(f1, "First call").start();

    FutureTask<String> f2 = new FutureTask<>(() -> {
      futureRun();
      return "f22222";
    });
    new Thread(f2, "Second call").start();
    System.out.println("First call... end" + f1.get());
    System.out.println("Second call... end" + f2.get());

    System.out.println("Other functions...");
  }

  private static Runnable getTarget(CyclicBarrier barrier) {
    return () -> {
      futureRun();
//      try {
//        barrier.await();
//      } catch (InterruptedException | BrokenBarrierException e) {
//        e.printStackTrace();
//      }
    };
  }

  private static void futureRun() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread() + " : " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
