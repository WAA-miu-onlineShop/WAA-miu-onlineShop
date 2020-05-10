package com.miu.waa.groupbravo.onlineshop.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.builder.SequenceNumberBuilder;
import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.SequenceNumber;
import com.miu.waa.groupbravo.onlineshop.repository.SequenceNumberRepository;
import com.miu.waa.groupbravo.onlineshop.service.serviceImpl.SequenceNumberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SequenceNumberServiceImplTest {
    private SequenceNumberServiceImpl sequenceNumberService;
    @Mock
    private SequenceNumberRepository sequenceNumberRepository;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sequenceNumberService = new SequenceNumberServiceImpl(sequenceNumberRepository);
    }

    @Test
    public void getNextOrderNumberFirstSequenceTest(){
       SequenceNumber sequenceNumber=new SequenceNumberBuilder().withSequence(null).withSequenceType(ESequenceType.ORDER).build();
       when(sequenceNumberRepository.findBySequenceType(ESequenceType.ORDER)).thenReturn(sequenceNumber);
       assertEquals(sequenceNumberService.getNextOrderNumber(),"OR12020");
        Mockito.verify(sequenceNumberRepository, Mockito.times(1)).findBySequenceType(ESequenceType.ORDER);

    }
    @Test
    public void getNextOrderNumberSecondSequenceTest(){
        SequenceNumber sequenceNumber=new SequenceNumberBuilder().withSequence(Long.valueOf(1)).withSequenceType(ESequenceType.ORDER).build();
        when(sequenceNumberRepository.findBySequenceType(ESequenceType.ORDER)).thenReturn(sequenceNumber);
        assertEquals(sequenceNumberService.getNextOrderNumber(),"OR22020");
        Mockito.verify(sequenceNumberRepository, Mockito.times(1)).findBySequenceType(ESequenceType.ORDER);

    }

    @Test
    public void getNextProductNumberFirstSequenceTest(){
        SequenceNumber sequenceNumber=new SequenceNumberBuilder().withSequence(null).withSequenceType(ESequenceType.PRODUCT).build();
        when(sequenceNumberRepository.findBySequenceType(ESequenceType.PRODUCT)).thenReturn(sequenceNumber);
        assertEquals(sequenceNumberService.getNextProductNumber(),"PR12020");
        Mockito.verify(sequenceNumberRepository, Mockito.times(1)).findBySequenceType(ESequenceType.PRODUCT);
    }
    @Test
    public void getNextProductNumberSecondSequenceTest(){
        SequenceNumber sequenceNumber=new SequenceNumberBuilder().withSequence(Long.valueOf(1)).withSequenceType(ESequenceType.PRODUCT).build();
        when(sequenceNumberRepository.findBySequenceType(ESequenceType.PRODUCT)).thenReturn(sequenceNumber);
        assertEquals(sequenceNumberService.getNextProductNumber(),"PR22020");
        Mockito.verify(sequenceNumberRepository, Mockito.times(1)).findBySequenceType(ESequenceType.PRODUCT);

    }

}
