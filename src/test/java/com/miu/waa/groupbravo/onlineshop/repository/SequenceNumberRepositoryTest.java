package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.SequenceNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SequenceNumberRepositoryTest {
    @Autowired
    private SequenceNumberRepository sequenceNumberRepository;
    @Before
    public void setUp() throws Exception {
    }
    @Test(expected = NullPointerException.class)
    public void testFindBySequenceType(){
        SequenceNumber sequenceNumber=sequenceNumberRepository.findBySequenceType(ESequenceType.ORDER);
        assertNotNull(sequenceNumber.getSequence());

    }
}
