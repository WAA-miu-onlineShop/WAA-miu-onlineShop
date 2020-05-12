package com.miu.waa.groupbravo.onlineshop.exceptions;

import com.miu.waa.groupbravo.onlineshop.domain.message.UserMessage;
import com.miu.waa.groupbravo.onlineshop.service.message.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

public class RequestBravoException extends RuntimeException {
    private final Collection<String> messages = new ArrayList<>();

    public RequestBravoException(String message){
        messages.add(message);
    }
    public RequestBravoException(Exception ex){
        messages.add(ex.getMessage());
    }
    public RequestBravoException(Exception ex, String message,Object ...objects){
        messages.add(ex.getMessage());
        messages.add(MessageFormat.format(message,objects));
    }
    public RequestBravoException(Exception ex, int messageNumber){
        messages.add(ex.getMessage());
        messages.add(getMessageByMessageNumber(messageNumber));
    }
    public RequestBravoException(Exception ex, int messageNumber,Object ...objects){
        messages.add(ex.getMessage());
        messages.add(getMessageByMessageNumber(messageNumber,objects));

    }
    @Override
    public String getMessage() {

        StringBuilder b = new StringBuilder();

        for (String msg : messages) {
            b.append(" " + msg + ".");
        }
        return b.toString();
    }
    private String getMessageByMessageNumber(int messageNumber) {
        try {

            ApplicationContext applicationContext= SpringApplication.run(RequestBravoException.class);
            UserMessageService userMessageService=applicationContext.getBean(UserMessageService.class);
            UserMessage userMessage = userMessageService.findByCode(messageNumber);
            return MessageFormat.format(userMessage.getValue(),"");
        } catch (Exception ex) {
            return "Error fetching the error message. Please check if it exists in the database.";
        }
    }
    private String getMessageByMessageNumber(int messageNumber,final Object... arguments) {
        try {
            ApplicationContext applicationContext= SpringApplication.run(RequestBravoException.class);
            UserMessageService userMessageService=applicationContext.getBean(UserMessageService.class);
            UserMessage userMessage = userMessageService.findByCode(messageNumber);
            return MessageFormat.format(userMessage.getValue(), arguments);
        } catch (Exception ex) {
            return "Error fetching the error message. Please check if it exists in the database.";
        }
    }


}
