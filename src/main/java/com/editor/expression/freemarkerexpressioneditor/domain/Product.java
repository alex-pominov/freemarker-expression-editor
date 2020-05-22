package com.editor.expression.freemarkerexpressioneditor.domain;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.domainRefs.Product2ClassGroupsRefs;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Table("product")
public class Product {

    private @Id @Setter(AccessLevel.PROTECTED) Long id;
    private @Column("shortDesc") String shortDesc; // TODO multiple lang
    private List<Price> prices;
    private final Set<Product2ClassGroupsRefs> classGroups = new HashSet<>();

    public Product(
            @JsonProperty("id") Long id,
            @JsonProperty("shortDesc") String shortDesc,
            @JsonProperty("prices") List<Price> prices
    ) {
        this.id = id;
        this.shortDesc = shortDesc;
        this.prices = prices;
    }

    public void addClassificationGroup(ClassificationGroup classificationGroup) {
        this.classGroups.add(new Product2ClassGroupsRefs(classificationGroup.getClassGroupId()));
    }

    public Long getId() {
        return id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public List<Price> getPrices() {
        return prices;
    }
}