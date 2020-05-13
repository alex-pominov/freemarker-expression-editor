package com.editor.expression.freemarkerexpressioneditor.repository;

import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
