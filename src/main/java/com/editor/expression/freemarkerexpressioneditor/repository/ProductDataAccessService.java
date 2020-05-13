package com.editor.expression.freemarkerexpressioneditor.repository;

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

        // Short description
        String sql = "select short_desc from product WHERE id=?";
        String shortDesc = (String) jdbcTemplate.queryForObject(
                sql, new Object[] { id }, String.class);

        // Price List
        String priceSql = "" +
                "select price.contract_id, price.price, price.valid_from_quantity, price.currency_id from price " +
                "WHERE price.product = " + id + ";";

        List<Price> prices = new ArrayList<>();
        List<Map<String, Object>> priceRows = jdbcTemplate.queryForList(priceSql);
        for (Map row : priceRows) {
            Price price = new Price(
                    Long.parseLong(row.get("contract_id").toString()),
                    Float.parseFloat(row.get("price").toString()),
                    (int) Double.parseDouble(row.get("valid_from_quantity").toString()),
                    Long.parseLong(row.get("contract_id").toString())
            );
            prices.add(price);
        }

        // Classification group List
        String classGrpsSql = "" +
                "select classification_group.id, classification_group.class_group_name from classification_group " +
                "WHERE classification_group.product = 1;";
        List<ClassificationGroup> classGrp = new ArrayList<>();
        List<Map<String, Object>> classGrpRows = jdbcTemplate.queryForList(classGrpsSql);
        for (Map row : classGrpRows) {
            Long classGroupId = Long.parseLong(row.get("id").toString());
            String classGroupName = row.get("class_group_name").toString();

            String conditionSql = "" +
                    "select classification_group.class_group_name, classification.id, classification.classification_name from classification, classification_group " +
                    "WHERE classification_group.product = " + id +
                    "AND classification.classification_group = classification_group.id " +
                    "AND classification_group.class_group_name = '" + classGroupName + "';";

            List<Classification> classifications = new ArrayList<>();
            List<Map<String, Object>> conditionsRows = jdbcTemplate.queryForList(conditionSql);
            for (Map conditionRow : conditionsRows) {
                classifications.add(
                        new Classification(
                                conditionRow.get("classification_name").toString()
                        ));
            }
            classGrp.add(new ClassificationGroup(classGroupName, classifications));
        }

//        Map<String, Object> productList = new HashMap<>();
//        productList.put("product", new Product(id, shortDesc, prices, classGrp));
//        productList.put("prices", prices);
//        productList.put("classifications", classGrp);
        return new Product(id, shortDesc, prices, classGrp);
    }

    public List<Product> getProducts() {
        // Short description
        String sql = "select * from product;";
        return jdbcTemplate.query(sql, mapProductFromDb());
    }

    private RowMapper<Product> mapProductFromDb() {
        return (resultSet, i) -> {
            Long productId = Long.parseLong(resultSet.getString("id"));
            String shortDesc = resultSet.getString("short_desc");

            // Price List
            String priceSql = "" +
                    "select price.contract_id, price.price, price.valid_from_quantity, price.currency_id from price " +
                    "WHERE price.product = " + productId + ";";

            List<Price> prices = new ArrayList<>();
            List<Map<String, Object>> priceRows = jdbcTemplate.queryForList(priceSql);
            for (Map row : priceRows) {
                Price price = new Price(
                        Long.parseLong(row.get("contract_id").toString()),
                        Float.parseFloat(row.get("price").toString()),
                        (int) Double.parseDouble(row.get("valid_from_quantity").toString()),
                        Long.parseLong(row.get("contract_id").toString())
                );
                prices.add(price);
            }

            // Classification group List
            String classGrpsSql = "" +
                    "select classification_group.id, classification_group.class_group_name from classification_group " +
                    "WHERE classification_group.product = " + productId;
            List<ClassificationGroup> classGrp = new ArrayList<>();
            List<Map<String, Object>> classGrpRows = jdbcTemplate.queryForList(classGrpsSql);
            for (Map row : classGrpRows) {
                Long classGroupId = Long.parseLong(row.get("id").toString());
                String classGroupName = row.get("class_group_name").toString();

                String conditionSql = "" +
                        "select classification_group.class_group_name, classification.id, classification.classification_name from classification, classification_group " +
                        "WHERE classification_group.product = " + productId +
                        "AND classification.classification_group = classification_group.id " +
                        "AND classification_group.class_group_name = '" + classGroupName + "';";

                List<Classification> classifications = new ArrayList<>();
                List<Map<String, Object>> conditionsRows = jdbcTemplate.queryForList(conditionSql);
                for (Map conditionRow : conditionsRows) {
                    classifications.add(
                            new Classification(
                                    conditionRow.get("classification_name").toString()
                            ));
                }
                classGrp.add(new ClassificationGroup(classGroupName, classifications));
            }

            return  new Product(productId, shortDesc, prices, classGrp);
        };
    }
}
