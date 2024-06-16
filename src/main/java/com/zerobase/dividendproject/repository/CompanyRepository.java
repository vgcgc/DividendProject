package com.zerobase.dividendproject.repository;

import com.zerobase.dividendproject.domain.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByTicker(String ticker);

    Optional<CompanyEntity> findByCompanyName(String companyName);

    Optional<CompanyEntity> findByTicker(String ticker);
}
