package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends CrudRepository<Address,Long> {
}
