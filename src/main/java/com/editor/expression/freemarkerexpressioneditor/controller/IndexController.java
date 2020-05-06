package com.editor.expression.freemarkerexpressioneditor.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String expressionEditor() {
        return "index";
    }

    @GetMapping(value = "/freemarkerReferences", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> getFreemarkerReferences() throws IOException {
        Resource resource = new ClassPathResource("/static/FreemarkerReferences/freemarkerReferences.json");
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


