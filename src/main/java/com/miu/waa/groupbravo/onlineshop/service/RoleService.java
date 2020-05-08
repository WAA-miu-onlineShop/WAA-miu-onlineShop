package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role get(Long id);
}
