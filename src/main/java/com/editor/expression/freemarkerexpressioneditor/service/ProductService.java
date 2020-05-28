package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ProductDataAccessService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Getter private final ProductDataAccessService productDataAccessService;

    public Product getProduct(Long id) {
        return productDataAccessService.getProduct(id);
    }

    public List<Product> getAllProducts() {
        return productDataAccessService.getAllProducts();
    }
}
