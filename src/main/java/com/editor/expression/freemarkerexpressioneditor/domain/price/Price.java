package com.editor.expression.freemarkerexpressioneditor.domain.price;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("price")
public class Price {
    private @Column("currencyId") Long contractId;
    private @Column("price") Float price;
    private @Column("validFromQuantity") int validFromQuantity;
    private @Column("contractId") Long currencyId;

    public Price(Long contractId, Float price, int validFromQuantity, Long currencyId) {
        this.contractId = contractId;
        this.price = price;
        this.validFromQuantity = validFromQuantity;
        this.currencyId = currencyId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getValidFromQuantity() {
        return validFromQuantity;
    }

    public void setValidFromQuantity(int validFromQuantity) {
        this.validFromQuantity = validFromQuantity;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "\n\tPrice {\n" +
                "\t\tcontractId = " + contractId + ",\n" +
                "\t\tprice = " + price + ",\n" +
                "\t\tvalidFromQuantity = " + validFromQuantity + ",\n" +
                "\t\tcurrencyId = " + currencyId + ",\n" +
                "\t}";
    }
}
