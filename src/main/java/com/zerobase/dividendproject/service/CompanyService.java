package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.domain.CompanyEntity;
import com.zerobase.dividendproject.domain.DividendEntity;
import com.zerobase.dividendproject.exception.impl.NoCompanyException;
import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.repository.CompanyRepository;
import com.zerobase.dividendproject.repository.DividendRepository;
import com.zerobase.dividendproject.scraping.YahooFinanceScrap;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final YahooFinanceScrap yahooFinanceScrap;
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Trie trie;


    public Company saveCompany(String ticker){
        Company company = yahooFinanceScrap.scrapCompanyByTicker(ticker);
        CompanyEntity companyEntity = companyRepository.save(new CompanyEntity(company));
        ScrapedResult scrapedResult = yahooFinanceScrap.scrapDividends(company);
        List<DividendEntity> dividendEntities = scrapedResult.getDividends().stream()
                .map(e -> new DividendEntity(companyEntity.getId(), e.getDate(), e.getDividend()))
                .toList();
        dividendRepository.saveAll(dividendEntities);
        return company;
    }

    public List<String> getAutoComplete(String keyword){
        List<String> companyNames = (List<String>) trie.prefixMap(keyword).keySet()
                .stream().limit(10).toList();
        return companyNames;
    }

    public void addAutocomplete(String keyword){
        trie.put(keyword, null);
    }

    public void deleteAutocomplete(String keyword){
        trie.remove(keyword);
    }

    public Page<CompanyEntity> getCompany(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public void deleteCompany(String ticker){
        CompanyEntity companyEntity = companyRepository.findByTicker(ticker)
                .orElseThrow(NoCompanyException::new);
        dividendRepository.deleteAllByCompanyId(companyEntity.getId());
        companyRepository.delete(companyEntity);
        deleteAutocomplete(ticker);
    }

}
