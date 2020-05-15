package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ClassificationGrpsAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationGrpsService {

    private final ClassificationGrpsAccessService classificationGrpsAccessService;

    @Autowired
    public ClassificationGrpsService(ClassificationGrpsAccessService classificationGrpsAccessService) {
        this.classificationGrpsAccessService = classificationGrpsAccessService;
    }

    public List<ClassificationGroup> getClassificationGroup() {
        return classificationGrpsAccessService.getClassificationGrps();
    }
}
