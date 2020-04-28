//package com.editor.expression.freemarkerexpressioneditor.controller;
//
//import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
//import com.editor.expression.freemarkerexpressioneditor.repository.SnippetRepository;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.io.StringWriter;
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//public class SnippetController {
//
//    private final SnippetRepository snippetRepository;
//
//    @Autowired
//    public SnippetController(SnippetRepository snippetRepository) {
//        this.snippetRepository = snippetRepository;
//    }
//
//    @PostMapping("/snippet")
//    public String all(@RequestBody Snippet snippet) {
//        System.out.println(snippet.getFreemarkerText());
//
//        return "result";
//    }
//
//}
