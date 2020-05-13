package com.editor.expression.freemarkerexpressioneditor.repository;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
