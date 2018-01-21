package com.chendehe.test;

public class CallBackTest {

  /**
   * 测试函数使用时间，通过定义CallBack接口的execute方法.
   */
  public void testTime(CallBack callBack) {
    long begin = System.currentTimeMillis(); //测试起始时间
    String execute = callBack.execute();///进行回调操作
    long end = System.currentTimeMillis(); //测试结束时间
    System.out.println(execute + "[use time]:" + (end - begin)); //打印使用时间
  }

  public static void main(String[] args) {
    CallBackTest tool = new CallBackTest();
    tool.testTime(new CallBack() {
      //定义execute方法
      public String execute() {
        //这里可以加放一个或多个要测试运行时间的方法
        System.out.println("3213");
        return "33333";
      }
    });
  }

} 