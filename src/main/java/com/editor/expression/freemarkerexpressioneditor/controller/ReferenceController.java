package com.editor.expression.freemarkerexpressioneditor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReferenceController {

    @GetMapping(value = "/freemarkerReferences", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> getFreemarkerReferences() {
        Resource resource = new ClassPathResource("/json/freemarkerReferences.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(resource.getInputStream(), Object.class);
            return new ResponseEntity<>(obj, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
