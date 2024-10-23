package com.mypg.contollerAdvices;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class RouteFallback {
    @ExceptionHandler(NoResourceFoundException.class)
    public String fallback(){
        return "redirect:/";
    }
    @ExceptionHandler(Exception.class)
    public String fallback(Exception ex, HttpSession session){
        session.setAttribute("error", ex.getMessage());
        return "redirect:/";
    }
}
