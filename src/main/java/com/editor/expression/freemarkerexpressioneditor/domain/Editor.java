package com.editor.expression.freemarkerexpressioneditor.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Editor {
    private String snippetText;
    private String formatType;
    private String resultType;
    private boolean performEvaluation;
}
