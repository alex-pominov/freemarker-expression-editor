package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("classification")
public class Classification {
    private @Id final Long id;
    private final String name;
    @Column(value = "classification", keyColumn = "classificationKey")
    private final List<ClassificationGroup> classificationGroups;

    public Classification(Long id, String name, List<ClassificationGroup> classificationGroups) {
        this.id = id;
        this.name = name;
        this.classificationGroups = classificationGroups;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ClassificationGroup> getClassificationGroups() {
        return classificationGroups;
    }
}
