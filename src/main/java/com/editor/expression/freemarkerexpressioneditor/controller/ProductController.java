package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
