package com.miu.waa.groupbravo.onlineshop.service.message.Impl;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;
import com.miu.waa.groupbravo.onlineshop.repository.message.UserMessageRepository;
import com.miu.waa.groupbravo.onlineshop.service.message.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserMessageServiceImpl  implements UserMessageService {
    @Autowired
    private UserMessageRepository userMessageRepository;
    @Override
    public UserMessage findById(Integer id) {
        return userMessageRepository.findById(id).get();
    }
    @Override
    public UserMessage findByCode(Integer code) {
        return userMessageRepository.findByCode(code);
    }
}
