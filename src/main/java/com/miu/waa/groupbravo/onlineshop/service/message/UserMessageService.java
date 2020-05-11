package com.miu.waa.groupbravo.onlineshop.service.message;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;

public interface UserMessageService {
    public UserMessage findById(Integer id);
    public UserMessage findByCode(Integer code);
}
