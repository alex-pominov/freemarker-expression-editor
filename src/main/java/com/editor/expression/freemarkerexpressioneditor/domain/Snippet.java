package com.editor.expression.freemarkerexpressioneditor.domain;

public class Snippet {
    private String freemarkerText;
    private String markdownText;
    private String editorType;
    private String resultType;

    public String getFreemarkerText() {
        return freemarkerText;
    }

    public void setFreemarkerText(String freemarkerText) {
        this.freemarkerText = freemarkerText;
    }

    public String getMarkdownText() {
        return markdownText;
    }

    public void setMarkdownText(String markdownText) {
        this.markdownText = markdownText;
    }

    public String getEditorType() {
        return editorType;
    }

    public void setEditorType(String editorType) {
        this.editorType = editorType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
