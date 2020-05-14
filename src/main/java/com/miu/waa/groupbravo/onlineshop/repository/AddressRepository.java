package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.constant.IRepositoryConstant;
import com.miu.waa.groupbravo.onlineshop.domain.Address;
import com.miu.waa.groupbravo.onlineshop.domain.EAddressRole;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends CrudRepository<Address, Long> {

    public static class QUERY{
      public static final  String  findAddressByUserAndAddressType="select a from Address a ,User u where u= :buyer and  a member of u.addresses and  a.addressRole= :addressRole";
    }
    public static class QUERY_NAME{
        public static final  String  findAddressByUserAndAddressType="Address.findAddressByUserAndAddressType";
    }
    Address findAddressByUserAndAddressType(@Param(IRepositoryConstant.BUYER) User buyer, @Param(IRepositoryConstant.ADDRESS_ROLE) EAddressRole addressRole);
}
