package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ERoleType;
import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.SequenceNumber;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.SequenceNumberRepository;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class SequenceNumberServiceImpl  implements SequenceNumberService {
     private SequenceNumberRepository sequenceNumberRepository;
    public SequenceNumberServiceImpl(SequenceNumberRepository sequenceNumberRepository){
        this.sequenceNumberRepository=sequenceNumberRepository;
    }
    @Override
    public String getNextUserNumber(ERoleType roleType) {

        SequenceNumber sequenceNumber=sequenceNumberRepository.findBySequenceType(ESequenceType.USER);
        //SE+SEQUENCE+YEAR
        //CU+SEQUENCE+YEAR
        //AD+SEQUENCE+YEAR
        String prefix="";
        if(roleType.compareTo(ERoleType.SELLER)==0){
            prefix="SE";
        }else if(roleType.compareTo(ERoleType.BUYER)==0){
            prefix="CU";
        }else if(roleType.compareTo(ERoleType.ADMIN)==0){
           prefix="AD";
        }
        return prefix+getNextSequence(ESequenceType.USER)+getCurrentYear();
    }

    @Override
    public String getNextCouponNumber() {
       SequenceNumber sequenceNumber=sequenceNumberRepository.findBySequenceType(ESequenceType.COUPON);
       //CP+Sequence+year
       return "CP"+sequenceNumber+getCurrentYear();
    }

    private Long getNextSequence(ESequenceType sequenceType){
        try {
            SequenceNumber sequence =sequenceNumberRepository.findBySequenceType(sequenceType);
            sequence.setSequence(sequence.getSequence() + Long.valueOf((1)));
            sequenceNumberRepository.save(sequence);
            return sequence.getSequence();
        } catch (Exception ex) {

            // That means we do not have any sequence, we need to create one
            SequenceNumber sequence = new SequenceNumber();
            sequence.setSequenceType(sequenceType);
            sequence.setSequence(Long.valueOf((1)));
            // create the sequence
            sequenceNumberRepository.save(sequence);
            return sequence.getSequence();
        }
    }
    private Integer getCurrentYear(){
        int year= LocalDate.now().getYear();
        return  year;
    }
    @Override
    public String getNextOrderNumber() {

       String  prefix="OR";

        //OR+SEQUENCE+YEAR

        return  prefix+getNextSequence(ESequenceType.ORDER)+getCurrentYear();
    }

    @Override
    public String getNextProductNumber() {
        //PR+SEQUENCE+YEAR

        String  prefix="PR";

        //OR+SEQUENCE+YEAR

        return  prefix+getNextSequence(ESequenceType.PRODUCT)+getCurrentYear();
    }

    @Override
    public String getNextPaymentNumber() {

        //P+SEQUENCE+YEAR

        String  prefix="P";

        //OR+SEQUENCE+YEAR

        return  prefix+getNextSequence(ESequenceType.PAYMENT)+getCurrentYear();
    }
}
