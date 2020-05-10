package com.miu.waa.groupbravo.onlineshop.builder;

import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.SequenceNumber;

public class SequenceNumberBuilder {
    private SequenceNumber sequenceNumber;
    public SequenceNumberBuilder(){
        sequenceNumber=new SequenceNumber();
    }
    public SequenceNumberBuilder withSequence(Long sequence){
        this.sequenceNumber.setSequence(sequence);
        return  this;
    }
    public SequenceNumberBuilder withSequenceType(ESequenceType sequenceType){
        this.sequenceNumber.setSequenceType(sequenceType);
        return  this;
    }
    public SequenceNumber build() {
        return sequenceNumber;
    }
}
