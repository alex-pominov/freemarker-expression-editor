package com.editor.expression.freemarkerexpressioneditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String expressionEditor() {
        return "index";
    }
}


