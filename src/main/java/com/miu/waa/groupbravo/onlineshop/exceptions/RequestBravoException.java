package com.miu.waa.groupbravo.onlineshop.exceptions;

public class RequestBravoException extends RuntimeException {

    public RequestBravoException(String message){
        super(message);
    }
    public RequestBravoException(Exception ex){
        super(ex);
    }
    public RequestBravoException(Exception ex, String message,Object ...objects){
        super(ex);
    }


}
