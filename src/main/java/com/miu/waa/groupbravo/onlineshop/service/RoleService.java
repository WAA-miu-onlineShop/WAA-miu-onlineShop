package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.domain.UserRole;

import java.util.List;

public interface RoleService {
    List<UserRole> findAll();

    UserRole get(Long id);
}
