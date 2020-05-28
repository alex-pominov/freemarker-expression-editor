package com.editor.expression.freemarkerexpressioneditor.domain.domainRefs;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product2ClassGrps")
public class Product2ClassGroupsRefs {
    private @Column("classificationGroups") Long classificationGroupId;

    public Product2ClassGroupsRefs(Long classificationGroupId) {
        this.classificationGroupId = classificationGroupId;
    }
}
