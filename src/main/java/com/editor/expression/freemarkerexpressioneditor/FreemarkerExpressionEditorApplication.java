package com.editor.expression.freemarkerexpressioneditor;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Contract;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import com.editor.expression.freemarkerexpressioneditor.repository.ClassificationRepository;
import com.editor.expression.freemarkerexpressioneditor.repository.ContractRepository;
import com.editor.expression.freemarkerexpressioneditor.repository.CurrencyRepository;
import com.editor.expression.freemarkerexpressioneditor.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FreemarkerExpressionEditorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FreemarkerExpressionEditorApplication.class, args);
    }

    private final ProductRepository productRepository;
    private final CurrencyRepository currencyRepository;
    private final ContractRepository contractRepository;
    private final ClassificationRepository classificationRepository;

    @Autowired
    public FreemarkerExpressionEditorApplication(ProductRepository productRepository, CurrencyRepository currencyRepository, ContractRepository contractRepository, ClassificationRepository classificationRepository) {
        this.productRepository = productRepository;
        this.currencyRepository = currencyRepository;
        this.contractRepository = contractRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void run(String... args) {

//        Contract contract = new Contract(null,"Contract with Beladev Ltd.");
//        Contract contract1 = new Contract(null,"Contract with Liquid Ltd.");
//        contractRepository.save(contract);
//        contractRepository.save(contract1);
//
//        Currency currency = new Currency(null,"USD");
//        Currency currency2 = new Currency(null,"EUR");
//        currencyRepository.save(currency);
//        currencyRepository.save(currency2);
//
//        List<Classification> classifications = new ArrayList<>();
//        classifications.add(new Classification(null, "Tables"));
//        classifications.add(new Classification(null, "Chairs"));
//
//        List<ClassificationGroup> classificationGroups1 = new ArrayList<>();
//        classificationGroups1.add(new ClassificationGroup(null, "Home stuff", classifications));
//
//
//        List<Price> prices = new ArrayList<>();
//        prices.add(new Price(1L, 1500f, 300, 1L));
//        prices.add(new Price(2L, 2000f, 400, 2L));
//
//        Product product = new Product(null, "A car to drive into city", prices, classificationGroups1);
//        productRepository.save(product);
    }
}
