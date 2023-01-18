package com.vkr.webapp.repository;

import com.vkr.webapp.entity.InvestIndex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestIndexRepository extends JpaRepository<InvestIndex, Long> {

    InvestIndex findByInvestIndexName(String investIndexName);

    void deleteInvestIndexByInvestName(String investIndexName);
}
