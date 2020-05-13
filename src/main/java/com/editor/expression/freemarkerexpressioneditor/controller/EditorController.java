package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Editor;
import com.editor.expression.freemarkerexpressioneditor.domain.Variables;
import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.service.ClassificationGrpsService;
import com.editor.expression.freemarkerexpressioneditor.service.ProductService;
import com.editor.expression.freemarkerexpressioneditor.service.EditorService;
import com.editor.expression.freemarkerexpressioneditor.service.VariableService;
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
    private List<ClassificationGroup> classificationGroups;

    private final EditorService editorService;
    private final ProductService productService;
    private final ClassificationGrpsService classificationGrps;
    private final VariableService variableService;

    @Autowired
    public EditorController(EditorService editorService, ProductService productService,
                            ClassificationGrpsService classificationGrps, VariableService variableService
    ) {
        this.editorService = editorService;
        this.productService = productService;
        this.classificationGrps = classificationGrps;
        this.variableService = variableService;
    }

    @GetMapping("{id}")
    public String expressionEditor(@PathVariable Long id) {
        this.product = productService.getProduct(id);
        this.classificationGroups = classificationGrps.getClassificationGroup();

        this.variables = variableService.getVariables(this.product, this.classificationGroups);

        return "index";
    }

    @RequestMapping(value = "/processTemplate", method = RequestMethod.GET)
    public ResponseEntity<String> processTemplate(@ModelAttribute Editor editor) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("product", this.product);
        dataModel.put("classificationGroup", this.classificationGroups);
        return editorService.processTemplate(editor, dataModel);
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
}
