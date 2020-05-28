package com.editor.expression.freemarkerexpressioneditor.domain;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.domainRefs.Attribute2ClassGroupsRefs;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table("attribute")
@Builder(toBuilder = true)
public class Attribute {

    private @Id final Long id;
    private final String type;
    private @Column("isMultiValued") final boolean isMultiValued;
    private final String name;
    private final Set<Attribute2ClassGroupsRefs> classificationGroups = new HashSet<>();

    public Attribute(Long id, String type, boolean isMultiValued, String name) {
        this.id = id;
        this.type = type;
        this.isMultiValued = isMultiValued;
        this.name = name;
    }

    public void addClassificationGroup(ClassificationGroup classificationGroup) {
        this.classificationGroups.add(new Attribute2ClassGroupsRefs(classificationGroup.getClassGroupId()));
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isMultiValued() {
        return isMultiValued;
    }

    public String getName() {
        return name;
    }
}
