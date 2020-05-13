package com.editor.expression.freemarkerexpressioneditor.domain;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Product {
    @Id
    private Long id;
    private String shortDesc; // TODO multiple lang
    private List<Price> prices;
    private List<ClassificationGroup> classGroups;
//    private List<AttributeValue> attributeValues;
//    private List<Document> documents;


    public Product(
            @JsonProperty("id") Long id,
            @JsonProperty("shortDesc") String shortDesc,
            @JsonProperty("prices") List<Price> prices,
            @JsonProperty("classGroups") List<ClassificationGroup> classGroups
    ) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.prices = prices;
        this.classGroups = classGroups;
    }

    @Override
    public String toString() {
        return "Product {\n" +
                "   id = " + id +  ",\n" +
                "   shortDesc = " + shortDesc + ",\n" +
                "   prices = " + prices + ",\n" +
                "   classGroups = " + classGroups + ",\n" +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<ClassificationGroup> getClassGroups() {
        return classGroups;
    }

    public void setClassGroups(List<ClassificationGroup> classGroups) {
        this.classGroups = classGroups;
    }

}
