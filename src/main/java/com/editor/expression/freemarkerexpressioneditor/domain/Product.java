package com.editor.expression.freemarkerexpressioneditor.domain;

import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.domainRefs.ClassGroupsRefs;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name="Product", namespace="Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @Id
    private Long id;
    private String shortDesc; // TODO multiple lang
    private List<Price> prices;
    private Set<ClassGroupsRefs> classGroups = new HashSet<>();

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
        this.classGroups.add(new ClassGroupsRefs(classificationGroup.getClassGroupId()));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", shortDesc='" + shortDesc + '\'' +
                ", prices=" + prices +
                '}';
    }

    @XmlAttribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    @XmlAttribute
    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
