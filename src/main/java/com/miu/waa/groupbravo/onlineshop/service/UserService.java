package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.User;

public interface UserService {
    User saveUser(User user);

    User findByUsername(String username);
}
