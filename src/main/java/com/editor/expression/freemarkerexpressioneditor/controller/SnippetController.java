package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Customer;
import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.editor.expression.freemarkerexpressioneditor.service.SnippetService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SnippetController {

    private final SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @RequestMapping(value = "/processTemplate", method = RequestMethod.GET)
    public ResponseEntity<String> processTemplate(@ModelAttribute Snippet snippet) throws IOException, TemplateException {

        Map<String, Object> dataModel = new HashMap<>();
        Product product = new Product("Table", "001233", 150.5, 75d, 100d);
        Customer customer = new Customer("Alex", "Moonlight street, 15", "+375 (29) 738-07-14", "Activity, Reading books");
        dataModel.put("product", product);
        dataModel.put("customer", customer);

        return snippetService.processTemplate(snippet, dataModel);
    }
}
