package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import org.springframework.data.annotation.Id;

import java.util.List;

public class ClassificationGroup {
    private String classGroupName;
    private List<Classification> classifications;

    public ClassificationGroup(String classGroupName, List<Classification> classifications) {
        this.classGroupName = classGroupName;
        this.classifications = classifications;
    }

    public String getClassGroupName() {
        return classGroupName;
    }

    public void setClassGroupName(String classGroupName) {
        this.classGroupName = classGroupName;
    }

    public List<Classification> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    @Override
    public String toString() {
        return "\n\tClassificationGroup {\n" +
                "\t\tclassGroupName = " + classGroupName + ",\n" +
                "\t\tclassifications = " + classifications + ",\n" +
                "   }";
    }
}
