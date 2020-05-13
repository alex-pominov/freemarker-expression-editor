package com.editor.expression.freemarkerexpressioneditor.domain;

public class Variables {
    // JSON
    private String name;
    private String groupName;
    private String parentPath;
    private String documentation;
    private Boolean isComplexType;
    private Boolean isList;
    private String template;
    private String listTemplate;

    public Variables(String name, String groupName, String parentPath, String documentation, Boolean isComplexType,
                     Boolean isList, String template, String listTemplate
    ) {
        this.groupName = groupName;
        this.name = name;
        this.parentPath = parentPath;
        this.documentation = documentation + ". is complex:  " + isComplexType;
        this.isComplexType = isComplexType;
        this.isList = isList;
        this.template = template;

        if (isList) {
            this.listTemplate = "<#list " + listTemplate + " as item>\n" +
                    "   ${item." + name + "}\n" +
                    "</#list>";
        }
    }

    @Override
    public String toString() {
        return "Variables{" +
                "name ='" + name + '\'' +
                ", type='" + groupName + '\'' +
                ", subcategory='" + parentPath + '\'' +
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
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