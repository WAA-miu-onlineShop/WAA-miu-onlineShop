package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.constant.IRepositoryConstant;
import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.SequenceNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceNumberRepository  extends CrudRepository<SequenceNumber,Long> {
    public static class QUERY{
        public static final String findBySequenceType = "select a from SequenceNumber a where a.sequenceType= :sequenceType ";
    }
    public static  class QUERY_NAME{
        public static final String findBySequenceType = "SequenceNumber.findBySequenceType";
    }
    public SequenceNumber findBySequenceType(@Param(IRepositoryConstant.SEQUENCE_TYPE) ESequenceType sequenceType);
}
