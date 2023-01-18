package com.vkr.webapp.service;

import com.vkr.webapp.entity.InvestIndex;

import java.util.Optional;

public interface InvestIndexService {

    InvestIndex getById(Long id);

    InvestIndex findByInvestIndexName(String investIndexName);

    InvestIndex save(InvestIndex investIndex);

    InvestIndex update(InvestIndex targetInvestIndex, InvestIndex sourceInvestIndex);

    void deleteById(Long id);

    void deleteByName(String investIndexName);
}
