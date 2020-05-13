package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Snippet;
import com.editor.expression.freemarkerexpressioneditor.domain.Variables;
import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.service.ProductService;
import com.editor.expression.freemarkerexpressioneditor.service.SnippetService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@Controller
public class EditorController {

    private Product product;
    private List<Variables> variables;

    private final SnippetService snippetService;
    private final ProductService productService;

    @Autowired
    public EditorController(SnippetService snippetService, ProductService productService) {
        this.snippetService = snippetService;
        this.productService = productService;
    }

    @GetMapping("{id}")
    public String expressionEditor(@PathVariable Long id) {
        this.product = productService.getProduct(id);

        variables = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.convertValue(product, JsonNode.class);
        process("product", root, "", false);

        return "index";
    }

    @RequestMapping(value = "/processTemplate", method = RequestMethod.GET)
    public ResponseEntity<String> processTemplate(@ModelAttribute Snippet snippet) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("product", this.product);
        return snippetService.processTemplate(snippet, dataModel);
    }

    @RequestMapping(value = "/freemarkerReferences", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFreemarkerReferences() {
        Resource filters = new ClassPathResource("/json/Filters.json");
        Resource controlRefs = new ClassPathResource("/json/ControlAndLoops.json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode filtersJSON = mapper.readTree(filters.getInputStream());
            JsonNode controlRefsJSON = mapper.readTree(controlRefs.getInputStream());
            JsonNode variables = mapper.valueToTree(this.variables);

            ArrayNode arrayNode = mapper.createArrayNode()
                    .add(filtersJSON)
                    .add(controlRefsJSON)
                    .add(variables);

            return new ResponseEntity<>(arrayNode, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void process(String prefix, JsonNode currentNode, String subcategory, Boolean isIterable) {
        currentNode.fields().forEachRemaining(entry -> {
            if (entry.getValue().isArray() && subcategory.equals("")) {
                getArrayNodes(prefix + "." + entry.getKey(), entry.getValue(), entry.getKey());
            }
        });

        currentNode.fields().forEachRemaining(field -> {
                    variables.add(new Variables(
                            field.getKey(), // variableName
                            "Product",
                            subcategory, // subcategory
                            prefix + "." + field.getKey(), // documentation
                            field.getValue().isArray(), // isComplexType
                            isIterable || field.getValue().isArray(), // isList
                            prefix + "." + field.getKey(), // template
                            prefix
                    ));
                }
        );
    }

    private void getArrayNodes(String prefix, JsonNode currentNode, String subcategory) {
        ArrayNode arrayNode = (ArrayNode) currentNode;
        Iterator<JsonNode> node = arrayNode.elements();

        currentNode.fields().forEachRemaining(field -> System.out.println(field.getKey()));
        process(prefix, node.next(), subcategory, true);
    }
}
