package com.editor.expression.freemarkerexpressioneditor.domain.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@AllArgsConstructor
@Table("price")
public class Price {
    private @Column("currencyId") Long contractId;
    private @Column("price") Float price;
    private @Column("validFromQuantity") int validFromQuantity;
    private @Column("contractId") Long currencyId;
}
