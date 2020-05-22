package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Attribute;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.AttributeAccessService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeService {
    @Getter private final AttributeAccessService attributeAccessService;

    public List<Attribute> getAllAttributes() {
        return attributeAccessService.getAllAttributes();
    }
}
