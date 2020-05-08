package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
