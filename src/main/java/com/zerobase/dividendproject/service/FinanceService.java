package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.domain.CompanyEntity;
import com.zerobase.dividendproject.domain.DividendEntity;
import com.zerobase.dividendproject.exception.impl.NoCompanyException;
import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.Dividend;
import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.repository.CompanyRepository;
import com.zerobase.dividendproject.repository.DividendRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FinanceService {

    private final DividendRepository dividendRepository;
    private final CompanyRepository companyRepository;

    @Cacheable(key = "#companyName", value = "finance")
    public ScrapedResult getDividend(String companyName){
        CompanyEntity companyEntity = companyRepository.findByCompanyName(companyName)
                .orElseThrow(NoCompanyException::new);
        List<DividendEntity> dividendEntities = dividendRepository.findByCompanyId(companyEntity.getId());
        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> new Dividend(e.getDate(), e.getDividend()))
                .toList();
        ScrapedResult scrapedResult = new ScrapedResult();
        scrapedResult.setCompany(new Company(companyEntity.getCompanyName(), companyEntity.getTicker()));
        scrapedResult.setDividends(dividends);
        return scrapedResult;
    }
}
