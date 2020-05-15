package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.Product;
import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.CurrencyAccessService;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.ProductDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyAccessService currencyAccessService;

    @Autowired
    public CurrencyService(CurrencyAccessService currencyAccessService) {
        this.currencyAccessService = currencyAccessService;
    }
    public List<Currency> getAllCurrency() {
        return currencyAccessService.getAllCurrency();
    }
}
