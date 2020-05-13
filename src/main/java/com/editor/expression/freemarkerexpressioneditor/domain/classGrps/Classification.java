package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.List;

public class Classification {
    @Id
    private Long id;
    private String name;
    @Column(value = "classification", keyColumn = "classificationKey")
    private List<ClassificationGroup> classificationGroups;

    public Classification(Long id, String name, List<ClassificationGroup> classificationGroups) {
        this.id = id;
        this.name = name;
        this.classificationGroups = classificationGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassificationGroup> getClassificationGroups() {
        return classificationGroups;
    }

    public void setClassificationGroups(List<ClassificationGroup> classificationGroups) {
        this.classificationGroups = classificationGroups;
    }

    @Override
    public String toString() {
        return "\n\t\t\tClassification {\n" +
                "\t\t\t\tclassificationName = " + name + ",\n" +
                "\t\t}";
    }
}
