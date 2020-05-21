package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.Attribute;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttributeAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttributeAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Attribute> getAllAttributes() {
        String sql = "select * from attribute;";
        return jdbcTemplate.query(sql, mapAttributeFromDb());
    }

    private RowMapper<Attribute> mapAttributeFromDb() {
        return (resultSet, i) -> {
            Long id = Long.parseLong(resultSet.getString("id"));
            String type = resultSet.getString("type");
            boolean isMultiValued = resultSet.getString("ismultivalued").equals("t");
            String name = resultSet.getString("name");
            return new Attribute(id, type, isMultiValued, name);
        };
    }
}
