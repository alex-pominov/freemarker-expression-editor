package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.Attribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttributeAccessService {
    @Getter private final JdbcTemplate jdbcTemplate;

    public List<Attribute> getAllAttributes() {
        String sql = "select * from attribute;";
        return jdbcTemplate.query(sql, mapAttributeFromDb());
    }

    private RowMapper<Attribute> mapAttributeFromDb() {
        return (resultSet, i) -> {
            Long id = Long.parseLong(resultSet.getString("id"));
            String type = resultSet.getString("type");
            boolean isMultiValued = resultSet.getString("isMultiValued").equals("t");
            String name = resultSet.getString("name");
            return new Attribute(id, type, isMultiValued, name);
        };
    }
}
