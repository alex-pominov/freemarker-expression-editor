package com.editor.expression.freemarkerexpressioneditor.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Variable {
    private String name;
    private String groupName;
    private String parentPath;
    private String subcategory;
    private String documentation;
    private String parameters;
    private Boolean isComplexType;
    private Boolean isList;
    private String template;
    private String listTemplate;
    private String example;

    public void setDocumentation(String variablePath) {
        this.documentation = "Expression to get <span><strong>" + this.name + "</strong></span> according to " +
                "<span><em>" + variablePath + "</em></span>" + " path.";
    }

    public void setParameters(Boolean isList) {
        if (isList) {
            this.parameters = "<ul><li><span>Index</span> : number of item in collection.</li></ul>";
        }
    }

    public void setTemplate(String prefix) {
        this.template = "${" + prefix + "." + this.name + "}";
    }

    public void setListTemplate(String prefix) {
        if (this.isList) {
            this.listTemplate = "" +
                    "<#list " + prefix + " as item>\n" +
                    "   ${item." + name + "}\n" +
                    "</#list>";
        }
    }

    public void setExample() {
        if (this.isList) {
            this.example = ""+
                    "Get " + name + " by index:\n" +
                    template + "\n\n" +
                    "Iterate over a list:\n" +
                    this.listTemplate;
        } else {
            this.example = this.template;
        }
    }
}