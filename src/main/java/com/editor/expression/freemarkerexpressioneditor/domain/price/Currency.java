package com.editor.expression.freemarkerexpressioneditor.domain.price;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("currency")
public class Currency {
    @Id
    private Long id;
    private @Column("name") String name;

    public Currency(Long id, String currencyType) {
        this.id = id;
        this.name = currencyType;
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

    public void setName(String currencyType) {
        this.name = currencyType;
    }
}
