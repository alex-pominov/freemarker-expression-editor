package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ClassificationGroups")
public class ClassificationGroup {
    private @Column("classGroupId") Long classGroupId;
    private @Column("parentId") Long parentId;
    private @Column("classGroupName") String classGroupName;

    public ClassificationGroup(Long classGroupId, Long parentId, String classGroupName) {
        this.classGroupId = classGroupId;
        this.parentId = parentId;
        this.classGroupName = classGroupName;
    }

    public Long getClassGroupId() {
        return classGroupId;
    }

    public void setClassGroupId(Long classGroupId) {
        this.classGroupId = classGroupId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getClassGroupName() {
        return classGroupName;
    }

    public void setClassGroupName(String classGroupName) {
        this.classGroupName = classGroupName;
    }
}
