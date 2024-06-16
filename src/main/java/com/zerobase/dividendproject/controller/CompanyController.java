package com.zerobase.dividendproject.controller;

import com.zerobase.dividendproject.domain.CompanyEntity;
import com.zerobase.dividendproject.model.Company;
import com.zerobase.dividendproject.repository.CompanyRepository;
import com.zerobase.dividendproject.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    public CompanyController(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }


    @GetMapping("/autocomplete")
    public ResponseEntity<?> getAutoComplete(@RequestParam String keyword){
        List<String> list = companyService.getAutoComplete(keyword);
        return ResponseEntity.ok(list);
    }

    @GetMapping
    @PreAuthorize("hasRole('READ')")
    public ResponseEntity<?> getCompany(final Pageable pageable){
        Page<CompanyEntity> companyPage = companyService.getCompany(pageable);
        return ResponseEntity.ok(companyPage);
    }

    @PostMapping
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> postCompany(@RequestBody Company company){
        if(companyRepository.existsByTicker(company.getTicker())){
            // 회사 있음
            System.out.println("회사 있음");
            return null;
        }
        Company companyWithName = companyService.saveCompany(company.getTicker());
        companyService.addAutocomplete(companyWithName.getCompanyName());
        return ResponseEntity.ok(companyWithName);
    }

    @DeleteMapping("/{ticker}")
    public void deleteCompany(@PathVariable String ticker){
        companyService.deleteCompany(ticker);
    }


}
