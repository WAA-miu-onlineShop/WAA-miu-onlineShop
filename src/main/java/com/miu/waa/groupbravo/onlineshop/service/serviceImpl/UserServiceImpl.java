package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.repository.AddressRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRoleRepository;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SequenceNumberService sequenceNumberService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AddressRepository addressRepository;

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public User approveSeller(User seller){

        return userRepository.save(seller);
    }

    @Override
    public User updateUser(User buyer) {
        return userRepository.save(buyer);
    }



    public List<User> findAll(){
        return (List<User>)userRepository.findAll();
    }

    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.isNew()){
            Optional<UserRole> userRole = userRoleRepository.findById(user.getUserRole().getId());
            user.setUserRole(userRole.get());
            String userNumber = sequenceNumberService.getNextUserNumber(user.getUserRole().getRoleType());
            user.setUserNumber(userNumber);
        }
        if(user.getUserRole().getRoleType().compareTo(ERoleType.SELLER) == 0){
            user.setUserStatus(EUserStatus.NEW);

        }else{
            user.setUserStatus(EUserStatus.APPROVED);

        }
        return userRepository.save(user);
    }

}
