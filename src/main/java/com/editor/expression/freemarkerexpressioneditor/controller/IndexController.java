package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String expressionEditor() {
        return "index";
    }

    @GetMapping(value = "/freemarkerReferences", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> getFreemarkerReferences() {
        Resource resource = new ClassPathResource("/static/freemarkerReferences.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(resource.getInputStream(), Object.class);
            return new ResponseEntity<Object>(obj, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


