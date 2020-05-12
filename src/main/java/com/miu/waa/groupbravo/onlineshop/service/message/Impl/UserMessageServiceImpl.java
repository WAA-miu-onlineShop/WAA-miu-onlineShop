package com.miu.waa.groupbravo.onlineshop.service.message.Impl;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;
import com.miu.waa.groupbravo.onlineshop.repository.message.UserMessageRepository;
import com.miu.waa.groupbravo.onlineshop.service.message.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserMessageServiceImpl  implements UserMessageService {

    private Map<Integer,UserMessage> messages=new HashMap<>();
    @Autowired
    private UserMessageRepository userMessageRepository;
    public UserMessageServiceImpl(UserMessageRepository userMessageRepository){
        this.userMessageRepository=userMessageRepository;
        List<UserMessage> userMessageList=(List<UserMessage> )userMessageRepository.findAll();
        userMessageList.stream().collect(Collectors.toMap(UserMessage::getCode,userMessage->userMessage));
    }

    @Override
    public UserMessage findById(Integer id) {
        return userMessageRepository.findById(id).get();
    }
    @Override
    public UserMessage findByCode(Integer code) {
        UserMessage userMessage=messages.get(code);
        if(userMessage==null){
            userMessage= userMessageRepository.findByCode(code);
            messages.put(userMessage.getCode(),userMessage);
        }
        return userMessage;
    }
}
