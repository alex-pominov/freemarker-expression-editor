package com.editor.expression.freemarkerexpressioneditor.domain.price;

import org.springframework.data.annotation.Id;

public class Contract {
    @Id
    private Long id;
    private String contractDescription;

    public Contract(Long id, String contractDescription) {
        this.id = id;
        this.contractDescription = contractDescription;
    }
}
