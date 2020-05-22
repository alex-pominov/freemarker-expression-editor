package com.editor.expression.freemarkerexpressioneditor.service;

import com.editor.expression.freemarkerexpressioneditor.domain.price.Currency;
import com.editor.expression.freemarkerexpressioneditor.repository.DataAccessService.CurrencyAccessService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Getter private final CurrencyAccessService currencyAccessService;

    public List<Currency> getAllCurrency() {
        return currencyAccessService.getAllCurrency();
    }
}
