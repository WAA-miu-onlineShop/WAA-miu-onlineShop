package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.model.User;


public interface UserService {
    User saveUser(User user);

    User findUserByUsername(String username);
}
