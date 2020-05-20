package com.editor.expression.freemarkerexpressioneditor.domain.domainRefs;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("attribute2classgrps")
public class Attribute2ClassGroupsRefs {
    private @Column("classificationgroups") Long classificationGroupId;

    public Attribute2ClassGroupsRefs(Long classificationGroupId) {
        this.classificationGroupId = classificationGroupId;
    }
}