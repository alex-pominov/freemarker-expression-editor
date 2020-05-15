package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ClassificationAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificationService {

    private final ClassificationAccessService classificationAccessService;

    @Autowired
    public ClassificationService(ClassificationAccessService classificationAccessService) {
        this.classificationAccessService = classificationAccessService;
    }

    public List<Classification> getClassificationGroup() {
        return classificationAccessService.getClassificationGrps();
    }
}
