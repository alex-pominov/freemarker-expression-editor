package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import org.springframework.data.annotation.Id;

public class Classification {
    private String classificationName;

    public Classification(String classificationName) {
        this.classificationName = classificationName;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    @Override
    public String toString() {
        return "\n\t\t\tClassification {\n" +
                "\t\t\t\tclassificationName = " + classificationName + ",\n" +
                "\t\t}";
    }
}
