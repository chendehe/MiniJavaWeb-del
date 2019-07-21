package com.chendehe.dubbo;

import com.chendehe.dubbo.demo.ConsumerService;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Dubbo Consumer client.
 */
public class Consumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();

        ConsumerService consumerService = context.getBean(ConsumerService.class);
        consumerService.consumerHello("consumerService");

        try {
            int i = System.in.read();// 按任意键退出
            System.out.println("End: " + i);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            context.stop();
        }

    }
}