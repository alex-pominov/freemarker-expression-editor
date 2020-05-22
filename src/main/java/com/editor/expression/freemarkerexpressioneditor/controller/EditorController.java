package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Editor;
import com.editor.expression.freemarkerexpressioneditor.domain.Variable;
import com.editor.expression.freemarkerexpressioneditor.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EditorController {
    @Getter private final EditorService editorService;
    @Getter private final ProductService productService;
    @Getter private final VariableService variableService;
    @Getter private final ClassificationService classificationService;
    @Getter private final CurrencyService currencyService;
    @Getter private final AttributeService attributeService;

    private List<Variable> variables;
    private Map<String, Object> dataModel;

    @GetMapping("{id}")
    public String expressionEditor(@PathVariable Long id) {
        dataModel = new HashMap<>();
        dataModel.put("product", productService.getProduct(id));
        dataModel.put("currencies", currencyService.getAllCurrency());
        dataModel.put("attributes", attributeService.getAllAttributes());
        dataModel.put("classifications", classificationService.getClassificationGroup());
        this.variables = variableService.getVariables(dataModel);

        return "index";
    }

    @RequestMapping(value = "/processTemplate", method = RequestMethod.GET)
    public ResponseEntity<String> processTemplate(@ModelAttribute Editor editor) {
        return editorService.processTemplate(editor, this.dataModel);
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
