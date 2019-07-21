package com.chendehe.test;

public class Test2 {
  public static void PrintString(String s, Print<String> print) {
    print.print(s);
  }
  public static void main(String[] args) {
    PrintString("test", (x) -> System.out.println(x));
  }
}
@FunctionalInterface
interface Print<T> {
  public void print(T x);
}