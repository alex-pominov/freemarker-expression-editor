package com.editor.expression.freemarkerexpressioneditor.domain.classGrps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table("ClassificationGroups")
public class ClassificationGroup {
    private @Column("classGroupId") Long classGroupId;
    private @Column("parentId") Long parentId;
    private @Column("classGroupName") String classGroupName;
}
