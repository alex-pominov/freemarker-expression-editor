package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CurrencyAccessService {
    @Getter private final JdbcTemplate jdbcTemplate;

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
