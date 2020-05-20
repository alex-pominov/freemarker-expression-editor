package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Attribute;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.AttributeAccessService;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ClassificationGrpsAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {

    private final AttributeAccessService attributeAccessService;

    @Autowired
    public AttributeService(AttributeAccessService attributeAccessService) {
        this.attributeAccessService = attributeAccessService;
    }

    public List<Attribute> getAllAttributes() {
        return attributeAccessService.getAllAttributes();
    }
}
