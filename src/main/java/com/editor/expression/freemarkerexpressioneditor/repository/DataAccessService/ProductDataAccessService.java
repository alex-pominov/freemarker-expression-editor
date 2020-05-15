package com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product getProduct(Long id) {
        String sql = "select * from product WHERE id=" + id;
        List<Product> productList = jdbcTemplate.query(sql, mapProductFromDb());
        return productList.get(0);
    }

    public List<Product> getProducts() {
        String sql = "select * from product;";
        return jdbcTemplate.query(sql, mapProductFromDb());
    }

    private RowMapper<Product> mapProductFromDb() {
        return (resultSet, i) -> {
            Long productId = Long.parseLong(resultSet.getString("id"));
            String shortDesc = resultSet.getString("shortDesc");

            // Price List
            String priceSql = "" +
                    "select price.contractid, price.price, price.validfromquantity, price.currencyid from price " +
                    "WHERE price.product = " + productId;

            List<Price> prices = new ArrayList<>();
            List<Map<String, Object>> priceRows = jdbcTemplate.queryForList(priceSql);
            for (Map<String, Object> row : priceRows) {
                Price price = new Price(
                        Long.parseLong(row.get("currencyId").toString()),
                        Float.parseFloat(row.get("price").toString()),
                        (int) Double.parseDouble(row.get("validFromQuantity").toString()),
                        Long.parseLong(row.get("contractId").toString())
                );
                prices.add(price);
            }

            return  new Product(productId, shortDesc, prices);
        };
    }
}