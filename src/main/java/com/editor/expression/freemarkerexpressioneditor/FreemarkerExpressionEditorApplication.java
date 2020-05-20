package com.editor.expression.freemarkerexpressioneditor;

import com.editor.expression.freemarkerexpressioneditor.domain.Attribute;
import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.Classification;
import com.editor.expression.freemarkerexpressioneditor.domain.classGrps.ClassificationGroup;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Contract;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Price;
import com.editor.expression.freemarkerexpressioneditor.repository.*;
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
    private final AttributeRepository attributeRepository;

    @Autowired
    public FreemarkerExpressionEditorApplication(
            ProductRepository productRepository, CurrencyRepository currencyRepository,
            ContractRepository contractRepository, ClassificationRepository classificationRepository,
            ClassificationGroupRepository classificationGroupRepository, AttributeRepository attributeRepository
    ) {
        this.productRepository = productRepository;
        this.currencyRepository = currencyRepository;
        this.contractRepository = contractRepository;
        this.classificationRepository = classificationRepository;
        this.attributeRepository = attributeRepository;
    }


    @Override
    public void run(String... args) {

//        Contract contract = new Contract(null,"Bought 5 chairs and 1 sofa.");
//        Contract contract1 = new Contract(null,"Bought 1 table and 3 chairs.");
//        contractRepository.save(contract);
//        contractRepository.save(contract1);
//
//        Currency currency = new Currency(null,"USD");
//        Currency currency2 = new Currency(null,"EUR");
//        Currency currency3 = new Currency(null,"BLR");
//        currencyRepository.save(currency);
//        currencyRepository.save(currency2);
//        currencyRepository.save(currency3);
//
//
//        List<Price> prices = new ArrayList<>();
//        prices.add(new Price(1L, 1500f, 15, 1L));
//        prices.add(new Price(2L, 2000f, 6, 2L));
//
//        Product product = new Product(null, "Xiaomi S4", prices);
//        Product product2 = new Product(null, "Tables", prices);
//        Product product3 = new Product(null, "Sofa", prices);
//        productRepository.save(product2);
//        productRepository.save(product3);
//
//        List<ClassificationGroup> ebayClassGrp = new ArrayList<>();
//        ebayClassGrp.add(new ClassificationGroup(10L, null,"Collectibles&Art"));
//        ebayClassGrp.add(new ClassificationGroup(20L, null, "Computer&Tablets"));
//        ebayClassGrp.add(new ClassificationGroup(101L, 10L,"Collectibles"));
//        ebayClassGrp.add(new ClassificationGroup(201L, 20L,"Computer&Tablets"));
//        ebayClassGrp.add(new ClassificationGroup(202L, 20L,"MP3 players"));
//
//        List<ClassificationGroup> amazonClassGrp = new ArrayList<>();
//        amazonClassGrp.add(new ClassificationGroup(10L, null,"Automobile"));
//        amazonClassGrp.add(new ClassificationGroup(20L, null, "Electronics"));
//        amazonClassGrp.add(new ClassificationGroup(101L, 10L,"BMW"));
//        amazonClassGrp.add(new ClassificationGroup(201L, 20L,"Phones"));
//        amazonClassGrp.add(new ClassificationGroup(202L, 20L,"Tablets"));
//
//        product.addClassificationGroup(ebayClassGrp.get(0));
//        product.addClassificationGroup(ebayClassGrp.get(1));
//        product.addClassificationGroup(ebayClassGrp.get(2));
//        productRepository.save(product);
//
//        classificationRepository.save(new Classification(null, "ebayClassification", ebayClassGrp));
//        classificationRepository.save(new Classification(null, "amazonClassification", amazonClassGrp));
//
//        Attribute attribute = new Attribute(null, "int", false, "Color");
//        attribute.addClassificationGroup(ebayClassGrp.get(0));
//        attribute.addClassificationGroup(ebayClassGrp.get(1));
//        attribute.addClassificationGroup(ebayClassGrp.get(2));
//        attributeRepository.save(attribute);
//
//        Attribute attribute2 = new Attribute(null, "int", false, "Length");
//        attribute2.addClassificationGroup(amazonClassGrp.get(0));
//        attribute2.addClassificationGroup(amazonClassGrp.get(1));
//        attribute2.addClassificationGroup(amazonClassGrp.get(2));
//        attributeRepository.save(attribute2);
    }
}
