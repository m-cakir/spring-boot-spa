package com.mcakir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@ControllerAdvice
public class SinglePageApplicationCustomizer {

    public static final String FORWARD_INDEX_HTML = "forward:/static/index.html";

    @ExceptionHandler(NoHandlerFoundException.class)
    public RedirectView notFound() {
        return new RedirectView("/");
    }

    @GetMapping("/")
    public String home() {
        return FORWARD_INDEX_HTML;
    }

}
