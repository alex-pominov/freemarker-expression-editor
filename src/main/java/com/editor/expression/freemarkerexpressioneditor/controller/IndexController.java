package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.editor.expression.freemarkerexpressioneditor.service.SnippetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class IndexController {

    private final SnippetService snippetService;

    @Autowired
    public IndexController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping("/")
    public String expressionEditor(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "index";
    }

    @GetMapping(value = "/methods", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMethods() {
        Resource resource = new ClassPathResource("/static/methods.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object obj = mapper.readValue(resource.getInputStream(), Object.class);
            return new ResponseEntity<Object>(obj, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/processTemplate")
    public String evaluateOnSubmit(@ModelAttribute Snippet snippet, Model model) throws IOException, TemplateException, ParserConfigurationException, SAXException {
        return snippetService.processTemplate(snippet, model);
    }

    @PostMapping("/checkTemplate")
    public Object checkTemplateForError(@RequestBody Snippet snippet) {
        return snippetService.checkTemplateForError(snippet);
    }
}


