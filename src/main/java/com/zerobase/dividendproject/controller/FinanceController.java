package com.zerobase.dividendproject.controller;

import com.zerobase.dividendproject.model.ScrapedResult;
import com.zerobase.dividendproject.service.FinanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<ScrapedResult> getDividend(@PathVariable String companyName) {
        return ResponseEntity.ok(financeService.getDividend(companyName));
    }

}
