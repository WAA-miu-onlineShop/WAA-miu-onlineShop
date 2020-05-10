package com.miu.waa.groupbravo.onlineshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SequenceNumberTest {
    private SequenceNumber sequenceNumber;

    @Before
    public void setUp(){
        sequenceNumber=new SequenceNumber();
    }
    @Test
    public  void getSequence(){
        Long sequence=Long.valueOf(1);
        sequenceNumber.setSequence(sequence);
        assertEquals(sequence,sequenceNumber.getSequence());
    }
}
