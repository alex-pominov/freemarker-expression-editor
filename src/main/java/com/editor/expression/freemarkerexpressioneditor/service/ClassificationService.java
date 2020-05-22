package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ClassificationAccessService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassificationService {
    @Getter private final ClassificationAccessService classificationAccessService;

    public List<Classification> getClassificationGroup() {
        return classificationAccessService.getClassificationGrps();
    }
}
