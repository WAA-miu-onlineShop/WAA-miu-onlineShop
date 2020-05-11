package com.miu.waa.groupbravo.onlineshop.domain;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserMessageTest {
 private UserMessage userMessage;
 @Before
    public void setUp(){
        userMessage=new UserMessage();
    }
    @Test
    public void testSetValue(){
        String value="Error while saving";
        userMessage.setValue(value);
        assertEquals(value,userMessage.getValue());
    }


}
