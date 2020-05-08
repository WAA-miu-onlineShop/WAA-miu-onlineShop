package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.domain.UserRole;
import com.miu.waa.groupbravo.onlineshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRole> findAll() {

        return roleRepository.findAll();
    }

    @Override
    public UserRole get(Long id) {

        return roleRepository.findById(id).get();
    }

}
