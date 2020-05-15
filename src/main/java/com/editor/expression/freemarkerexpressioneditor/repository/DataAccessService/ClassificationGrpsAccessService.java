package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassificationGrpsAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassificationGrpsAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassificationGroup> getClassificationGrps() {
        String sql = "select * from classificationgroups;";
        return jdbcTemplate.query(sql, mapGroupsFromDb());
    }

    private RowMapper<ClassificationGroup> mapGroupsFromDb() {
        return (resultSet, i) -> {
            Long classGroupId = Long.parseLong(resultSet.getString("classGroupId"));
            Long parentId;
            try {
                parentId = Long.parseLong(resultSet.getString("parentId"));
            } catch (NumberFormatException e) {
                parentId = 0L;
            }
            String classGroupName = resultSet.getString("classGroupName");
            return new ClassificationGroup(classGroupId, parentId, classGroupName);
        };
    }
}
