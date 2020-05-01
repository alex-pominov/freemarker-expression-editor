package com.editor.expression.freemarkerexpressioneditor.domain;

public class Snippet {
    private String snippetText;
    private String formatType;
    private String resultType;
    private boolean performEvaluation;

    public String getSnippetText() {
        return snippetText;
    }

    public void setSnippetText(String snippetText) {
        this.snippetText = snippetText;
    }

    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public boolean isPerformEvaluation() {
        return performEvaluation;
    }

    public void setPerformEvaluation(boolean performEvaluation) {
        this.performEvaluation = performEvaluation;
    }
}
