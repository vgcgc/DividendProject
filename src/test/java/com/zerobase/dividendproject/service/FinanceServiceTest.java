package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.model.ScrapedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinanceServiceTest {

    @Autowired
    private final CompanyService companyService;
    private final FinanceService financeService;

    @Autowired
    FinanceServiceTest(FinanceService financeService, CompanyService companyService) {
        this.financeService = financeService;
        this.companyService = companyService;
    }

    @Test
    void getDividendTest() {
        // given
        String ticker = "MMM";
        Company company = this.companyService.saveCompany(ticker);
        // when
        ScrapedResult scrapedResult = financeService.getDividend(company.getCompanyName());
        // then
        System.out.println(scrapedResult);
    }
}