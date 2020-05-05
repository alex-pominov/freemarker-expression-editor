package com.editor.expression.freemarkerexpressioneditor.domain;

public class Product {
    private String name;
    private String id;
    private Double price;
    private Double height;
    private Double width;

    public Product(String name, String id, Double price, Double height, Double width) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.height = height;
        this.width = width;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
