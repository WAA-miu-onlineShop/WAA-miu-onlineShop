package com.miu.waa.groupbravo.onlineshop.formatter;


import com.miu.waa.groupbravo.onlineshop.model.Role;
import com.miu.waa.groupbravo.onlineshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class RoleFormatter implements Formatter<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {
        return roleService.get(Long.parseLong(text));
    }

}
