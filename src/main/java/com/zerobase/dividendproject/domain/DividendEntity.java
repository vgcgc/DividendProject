package com.zerobase.dividendproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "Dividend")
@Getter
@Setter
@NoArgsConstructor
public class DividendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private LocalDate date;
    private String dividend;

    public DividendEntity(Long companyId, LocalDate date, String dividend) {
        this.companyId = companyId;
        this.date = date;
        this.dividend = dividend;
    }

}