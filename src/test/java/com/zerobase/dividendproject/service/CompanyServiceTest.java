package com.zerobase.dividendproject.service;

import com.zerobase.dividendproject.model.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompanyServiceTest {

    private final CompanyService companyService;

    @Autowired
    CompanyServiceTest(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Test
    void saveCompanyTest() {
        // given
        String ticker = "MMM";
        // when
        Company company = this.companyService.saveCompany(ticker);
        // then
        Company newCompany = new Company("3M Company", ticker);
        Assertions.assertEquals(newCompany, company);
    }

    @Test
    void deleteCompanyTest() {
        // given
        String ticker = "MMM";
        companyService.saveCompany(ticker);
        // when
        this.companyService.deleteCompany(ticker);
        // then

    }
}
