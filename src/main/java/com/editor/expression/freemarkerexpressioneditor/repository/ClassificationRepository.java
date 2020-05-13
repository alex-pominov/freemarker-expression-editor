package com.editor.expression.freemarkerexpressioneditor.repository;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import org.springframework.data.repository.CrudRepository;

public interface ClassificationRepository extends CrudRepository<Classification, Long> {
}
