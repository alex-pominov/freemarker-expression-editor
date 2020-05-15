package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ClassificationAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassificationAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
                    "AND classification.id = " + classificationId;
            List<ClassificationGroup> classificationGroups = new ArrayList<>();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            for (Map<String, Object> row : rows) {
//                Long parentId;
//                try {
//                    parentId = Long.parseLong(resultSet.getString("parentid"));
//                } catch (NumberFormatException e) {
//                    parentId = 0L;
//                }
                ClassificationGroup classificationGroup = new ClassificationGroup(
                        Long.parseLong(row.get("classGroupId").toString()),
                        0L,
                        row.get("classGroupName").toString()
                );
                classificationGroups.add(classificationGroup);
            }

            return new Classification(classificationId, classificationName, classificationGroups);
        };
    }
}
