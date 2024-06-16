package com.zerobase.dividendproject.repository;

import com.zerobase.dividendproject.domain.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {

    List<DividendEntity> findByCompanyId(Long companyId);

    @Transactional
    void deleteAllByCompanyId(Long companyId);
}
