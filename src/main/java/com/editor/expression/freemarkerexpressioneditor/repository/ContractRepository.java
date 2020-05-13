package com.editor.expression.freemarkerexpressioneditor.repository;

import com.editor.expression.freemarkerexpressioneditor.domain.price.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
