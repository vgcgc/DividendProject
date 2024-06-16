package com.zerobase.dividendproject.domain;

import com.zerobase.dividendproject.model.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "COMPANY")
@Getter
@Setter
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String ticker;

    public CompanyEntity(Company company) {
        this.companyName = company.getCompanyName();
        this.ticker = company.getTicker();
    }

}