package com.editor.expression.freemarkerexpressioneditor.domain.price;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("contract")
public class Contract {
    @Id
    private Long id;
    private @Column("description") String contractDescription;

    public Contract(Long id, String contractDescription) {
        this.id = id;
        this.contractDescription = contractDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }
}
