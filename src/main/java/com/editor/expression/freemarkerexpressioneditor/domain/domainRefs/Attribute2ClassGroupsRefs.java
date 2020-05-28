package com.editor.expression.freemarkerexpressioneditor.domain.domainRefs;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("attribute2ClassGrps")
public class Attribute2ClassGroupsRefs {
    private @Column("classificationGroups") Long classificationGroupId;

    public Attribute2ClassGroupsRefs(Long classificationGroupId) {
        this.classificationGroupId = classificationGroupId;
    }
}
