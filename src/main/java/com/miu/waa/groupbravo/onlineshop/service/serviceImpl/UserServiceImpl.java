package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ERoleType;
import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.domain.UserRole;
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

    public User findByUsername(String username) {
        //System.out.println("Username in the service layer: " + username);
        return userRepository.findByUsername(username);
    }

    public User approveSeller(User seller){
        //auth.getPrincipal();
        return userRepository.save(seller);
    }

    public List<User> findAll(){
        return (List<User>)userRepository.findAll();
    }

    public User saveUser(User user) {
        //System.out.println(passwordEncoder.encode("pass12345"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.isNew()){
            Optional<UserRole> userRole = userRoleRepository.findById(user.getUserRole().getId());
            user.setUserRole(userRole.get());
            String userNumber = sequenceNumberService.getNextUserNumber(user.getUserRole().getRoleType());
            user.setUserNumber(userNumber);
        }
        //user.getUserRole().getRoleType().compareTo(ERoleType.SELLER) == 0
        if(user.getUserRole().getRoleType().compareTo(ERoleType.SELLER) == 0){
            user.setUserStatus(EUserStatus.NEW);
            //user.setActive(0);
        }else{
            user.setUserStatus(EUserStatus.APPROVED);
            //user.setActive(1);
        }
        return userRepository.save(user);
    }

}
