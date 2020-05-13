package com.editor.expression.freemarkerexpressioneditor.domain.domainRefs;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product2classgrps")
public class ClassGroupsRefs {
    private @Column("classificationgroups") Long classificationGroupId;

    public ClassGroupsRefs(Long classificationGroupId) {
        this.classificationGroupId = classificationGroupId;
    }
}
