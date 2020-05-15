package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyAccessService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Currency> getAllCurrency() {
        String sql = "select * from currency;";
        return jdbcTemplate.query(sql, mapCurrencyFromDb());
    }

    private RowMapper<Currency> mapCurrencyFromDb() {
        return (resultSet, i) -> {
            Long id = Long.parseLong(resultSet.getString("id"));
            String currencyName = resultSet.getString("name");
            return new Currency(id, currencyName);
        };
    }
}
