package com.editor.expression.freemarkerexpressioneditor.domain;

public class Variable {
    // JSON
    private String name;
    private String groupName;
    private String parentPath;
    private String documentation;
    private String parameters;
    private Boolean isComplexType;
    private Boolean isList;
    private String template;
    private String listTemplate;
    private String example;

    public Variable() {
    }

    public String getName() {
        return name;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getParameters() {
        return parameters;
    }

    public Boolean getComplexType() {
        return isComplexType;
    }

    public Boolean getList() {
        return isList;
    }

    public String getTemplate() {
        return template;
    }

    public String getListTemplate() {
        return listTemplate;
    }

    public String getExample() {
        return example;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public void setDocumentation(String variablePath) {
        this.documentation = "Expression to get <span><strong>" + this.name + "</strong></span> according to " +
                "<span><em>" + variablePath + "</em></span>" + " path.";
    }

    public void setParameters(Boolean isList) {
        if (isList) {
            this.parameters = "<ul><li><span>Index</span> : number of item in collection.</li></ul>";
        }
    }

    public void setComplexType(Boolean complexType) {
        isComplexType = complexType;
    }

    public void setList(Boolean list) {
        isList = list;
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