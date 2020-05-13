package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.Variables;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VariableService {

    public List<Variables> getVariables(Product prod, List<ClassificationGroup> classificationGroups) {
        List<Variables> variables = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode product = mapper.convertValue(prod, JsonNode.class);
        process(variables,"product","Product", product, "", "");

        for (ClassificationGroup classificationGroup : classificationGroups) {
            JsonNode classGrps = mapper.convertValue(classificationGroup, JsonNode.class);
            process(variables,"classificationGroup", "Classification Group", classGrps, "", "[index]");
            break;
        }

        return variables;
    }

    private void process(List<Variables> variables , String prefix, String parentPath,
                         JsonNode currentNode, String subcategory, String indexPrefix) {

        String prefixed = indexPrefix.isEmpty() ? prefix : prefix + indexPrefix;

        if (currentNode.isArray()) {

            ArrayNode arrayNode = (ArrayNode) currentNode;
            Iterator<JsonNode> node = arrayNode.elements();
            currentNode.fields().forEachRemaining(field -> System.out.println(field.getKey()));
            process(variables, prefixed, parentPath, node.next(), subcategory,"[index]");

        } else if (currentNode.isObject()) {

            currentNode.fields().forEachRemaining(entry -> {
                if (entry.getValue().isArray() && subcategory.equals("")) {
                    process(variables,prefixed + "." + entry.getKey(), parentPath, entry.getValue(),
                            entry.getKey(), "");
                }
            });

            currentNode.fields().forEachRemaining(field -> {
                        variables.add(new Variables(
                                field.getKey(), // variableName
                                parentPath,
                                subcategory, // subcategory
                                prefix + "." + field.getKey(), // documentation
                                field.getValue().isArray(), // isComplexType
                                !indexPrefix.isEmpty(), // isList
                                "${" + prefixed + "." + field.getKey() + "}", // template
                                prefix
                        ));
                    }
            );
        }
    }
}
