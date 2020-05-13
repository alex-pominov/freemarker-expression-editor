package com.editor.expression.freemarkerexpressioneditor.domain;

public class Variables {
    // JSON
    private String name;
    private String type;
    private String subcategory;
    private String documentation;
    private Boolean isComplexType;
    private Boolean isList;
    private String template;
    private String listTemplate;

    public Variables(String name, String type, String subcategory, String documentation, Boolean isComplexType,
                     Boolean isList, String template, String listTemplate
    ) {
        this.type = type;
        this.name = name;
        this.subcategory = subcategory;
        this.documentation = documentation + ". is complex:  " + isComplexType;
        this.isComplexType = isComplexType;
        this.isList = isList;
        this.template = isList ? listTemplate + "[index]." + name  : template;

        if (isList) {
            String tmpl = "<#list " + listTemplate + " as item>\n" +
                    "   ${item." + name + "}\n" +
                    "</#list>";
            this.listTemplate = tmpl;
            if (isComplexType) {
                this.listTemplate = "" +
                        "<#list " + listTemplate + " as item>\n" +
                        "   <#list item." + name + " as i>\n" +
                        "       ${i}\n" +
                        "  </#list>\n" +
                        "</#list>";
            }
        }
    }

    @Override
    public String toString() {
        return "Variables{" +
                "name ='" + name + '\'' +
                ", type='" + type + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", documentation='" + documentation + '\'' +
                ", isComplexType=" + isComplexType +
                ", isList=" + isList +
                ", template='" + template + '\'' +
                '}';
    }

    public String getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(String listTemplate) {
        this.listTemplate = listTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public Boolean getComplexType() {
        return isComplexType;
    }

    public void setComplexType(Boolean complexType) {
        isComplexType = complexType;
    }

    public Boolean getList() {
        return isList;
    }

    public void setList(Boolean list) {
        isList = list;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}