package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.exceptions.RequestBravoException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model){
         String errorMessage=ex.getMessage();
         model.addAttribute("errorMessage",errorMessage);
        return "errorPage";
    }
    @ExceptionHandler(RequestBravoException.class)
    public String handleRequestBravoException(RequestBravoException ex,Model model  ){
        String errorMessage=ex.getMessage();
        model.addAttribute("errorMessage",errorMessage);
        return  "errorPage";
    }
}
