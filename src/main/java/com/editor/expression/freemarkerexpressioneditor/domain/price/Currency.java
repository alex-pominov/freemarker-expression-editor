package com.editor.expression.freemarkerexpressioneditor.domain.price;

import org.springframework.data.annotation.Id;

public class Currency {
    @Id
    private Long id;
    private String currencyType;

    public Currency(Long id, String currencyType) {
        this.id = id;
        this.currencyType = currencyType;
    }
}
