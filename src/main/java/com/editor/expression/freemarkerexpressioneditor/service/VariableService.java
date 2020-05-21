package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Variable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.tomcat.jni.Directory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class VariableService {

    public List<Variable> getVariables(Map<String, Object> dataModel) {
        List<Variable> variables = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        for (Map.Entry<String, Object> variableFromRow : dataModel.entrySet()) {
            String key = variableFromRow.getKey();
            JsonNode nodes = mapper.convertValue(variableFromRow.getValue(), JsonNode.class);
            process(variables, key, key, nodes, "", "");
        }

        return variables;
    }

    private void process(List<Variable> variables, String prefix, String parentPath,
                         JsonNode currentNode, String subcategory, String indexPrefix) {

        String prefixed = indexPrefix.isEmpty() ? prefix : prefix + indexPrefix;

        if (currentNode.isArray()) {

            ArrayNode arrayNode = (ArrayNode) currentNode;
            Iterator<JsonNode> node = arrayNode.elements();
            currentNode.fields().forEachRemaining(field -> System.out.println(field.getKey()));
            process(variables, prefixed, parentPath, node.next(), subcategory, "[index]");

        } else if (currentNode.isObject()) {

            currentNode.fields().forEachRemaining(entry -> {
                if (entry.getValue().isArray() && subcategory.equals("")) {
                    process(variables, prefixed + "." + entry.getKey(), parentPath, entry.getValue(),
                            entry.getKey(), "");
                }
            });

            currentNode.fields().forEachRemaining(field -> {
                        Variable variable = new Variable();
                        variable.setName(field.getKey());
                        variable.setGroupName(subcategory.isEmpty() ? "Variables" : "");
                        variable.setParentPath(subcategory.isEmpty() ? parentPath : subcategory);
                        variable.setSubcategory(subcategory.isEmpty() ? "" : parentPath);
                        variable.setParameters(!indexPrefix.isEmpty());
                        variable.setDocumentation(prefixed + "." + field.getKey());
                        variable.setComplexType(field.getValue().isArray());
                        variable.setList(!indexPrefix.isEmpty());
                        variable.setTemplate(prefixed);
                        variable.setListTemplate(prefix);
                        variable.setExample();
                        variables.add(variable);
                    }
            );
        }
    }
}
