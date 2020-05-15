package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ProductDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDataAccessService productDataAccessService;

    @Autowired
    public ProductService(ProductDataAccessService productDataAccessService) {
        this.productDataAccessService = productDataAccessService;
    }

    public Product getProduct(Long id) {
        return productDataAccessService.getProduct(id);
    }

    public List<Product> getProducts() {
        return productDataAccessService.getProducts();
    }
}
