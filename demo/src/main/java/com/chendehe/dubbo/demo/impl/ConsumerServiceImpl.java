package com.chendehe.dubbo.demo.impl;

import com.chendehe.dubbo.demo.ConsumerService;
import com.chendehe.dubbo.demo.ScheduleService;
import com.chendehe.server.bean.UserAddress;
import com.chendehe.server.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

//    @Autowired
//    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @Override
    public void consumerHello(String consumer) {
//        List<UserAddress> addressList = userService.getUserAddressList("test");
//
//        addressList.forEach(System.out::println);
        boolean b = scheduleService.generateReport("1232");
        System.out.println(b);
    }
}
