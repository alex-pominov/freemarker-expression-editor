package com.editor.expression.freemarkerexpressioneditor.controller;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    @Getter private final ProductService productService;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}
