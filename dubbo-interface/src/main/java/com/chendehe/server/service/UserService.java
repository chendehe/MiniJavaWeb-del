package com.chendehe.server.service;

import com.chendehe.server.bean.UserAddress;
import java.util.List;

/**
 * 用户服务
 *
 * @author lfy
 */
public interface UserService {

    /**
     * 按照用户id返回所有的收货地址
     */
    public List<UserAddress> getUserAddressList(String userId);

}
