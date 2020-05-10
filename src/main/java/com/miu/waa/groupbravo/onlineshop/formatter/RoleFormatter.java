package com.miu.waa.groupbravo.onlineshop.formatter;


import com.miu.waa.groupbravo.onlineshop.domain.UserRole;
import com.miu.waa.groupbravo.onlineshop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class RoleFormatter implements Formatter<UserRole> {

    @Autowired
    private RoleService roleService;

    @Override
    public String print(UserRole role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public UserRole parse(String text, Locale locale) throws ParseException {
        return roleService.get(Long.parseLong(text));
    }

}
