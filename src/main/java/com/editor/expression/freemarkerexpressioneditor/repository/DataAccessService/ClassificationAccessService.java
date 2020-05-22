package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ClassificationAccessService {
    @Getter private final JdbcTemplate jdbcTemplate;

    public List<Classification> getClassificationGrps() {
        String sql = "select * from classification;";
        return jdbcTemplate.query(sql, mapGroupsFromDb());
    }

    private RowMapper<Classification> mapGroupsFromDb() {
        return (resultSet, i) -> {
            Long classificationId = Long.parseLong(resultSet.getString("id"));
            String classificationName = resultSet.getString("name");

            String sql = "select classification.name, classificationgroups.classgroupname, classificationgroups.classgroupid, classificationgroups.parentid from classification, classificationgroups " +
                    "WHERE classificationgroups.classification = classification.id " +
                    "AND classificationgroups.parentid IS null " +
                    "AND classification.id = ?;";
            List<ClassificationGroup> classificationGroups = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, classificationId);
            for (Map<String, Object> row : rows) {
                Long parentId;
                try {
                    parentId = Long.parseLong(row.get("parentId").toString());
                } catch (NumberFormatException | NullPointerException e) {
                    parentId = 0L;
                }
                ClassificationGroup classificationGroup = new ClassificationGroup(
                        Long.parseLong(row.get("classGroupId").toString()),
                        parentId,
                        row.get("classGroupName").toString()
                );
                classificationGroups.add(classificationGroup);
            }

            return new Classification(classificationId, classificationName, classificationGroups);
        };
    }
}
