package com.editor.expression.freemarkerexpressioneditor.domain.price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("currency")
public class Currency {
    private @Id Long id;
    private @Column("name") String name;
}
