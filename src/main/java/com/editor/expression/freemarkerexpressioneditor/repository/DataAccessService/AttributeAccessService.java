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
        return (rs, i) -> Attribute.builder()
                .id(rs.getLong("id"))
                .type(rs.getString("type"))
                .isMultiValued(yesNoToBoolean(rs.getString("isMultiValued")))
                .name(rs.getString("name"))
                .build();
    }

    private Boolean yesNoToBoolean(String value) {
        if (value == null) {
            return null;
        }
        return value.equals("t");
    }
}
