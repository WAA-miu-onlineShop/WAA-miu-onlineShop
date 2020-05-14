package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Address;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.AddressRepository;
import com.miu.waa.groupbravo.onlineshop.service.AddressService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AddressServiceImpl  implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Override
    public void save(Address address) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userService.findByUsername(auth.getName());
        addressRepository.save(address);
    }
}
