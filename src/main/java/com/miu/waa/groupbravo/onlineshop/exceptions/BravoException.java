package com.miu.waa.groupbravo.onlineshop.exceptions;

public class BravoException extends  RuntimeException {

    public BravoException(String message){
        super(message);
    }
    public  BravoException(Exception ex){
        super(ex);
    }
}
