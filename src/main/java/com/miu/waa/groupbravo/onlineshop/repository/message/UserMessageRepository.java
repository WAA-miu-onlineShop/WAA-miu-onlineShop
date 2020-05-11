package com.miu.waa.groupbravo.onlineshop.repository.message;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends CrudRepository<UserMessage,Integer> {
    UserMessage findByCode(Integer code);
}
