package com.vkr.webapp.service.impl;

import com.vkr.webapp.entity.InvestIndex;
import com.vkr.webapp.exception.InvestIndexNotFoundException;
import com.vkr.webapp.mapper.InvestIndexMapper;
import com.vkr.webapp.repository.InvestIndexRepository;
import com.vkr.webapp.service.InvestIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestIndexServiceImpl implements InvestIndexService {

    private final InvestIndexRepository investIndexRepository;

    private final InvestIndexMapper investIndexMapper;


    @Override
    public InvestIndex getById(Long id) {
        return investIndexRepository.findById(id)
                .orElseThrow(() -> new InvestIndexNotFoundException("Ops invest index not found"));
    }

    @Override
    public InvestIndex findByInvestIndexName(String investIndexName) {
        return investIndexRepository.findByInvestIndexName(investIndexName);
    }

    @Override
    @Transactional
    public InvestIndex save(InvestIndex investIndex) {
        InvestIndex result = investIndexRepository.save(investIndex);
        log.info("Invest index was saved with id: {}", result.getId());
        return result;
    }

    @Override
    @Transactional
    public InvestIndex update(InvestIndex targetInvestIndex, InvestIndex sourceInvestIndex) {
        InvestIndex result = investIndexMapper.update(targetInvestIndex, sourceInvestIndex);
        log.info("Invest index id {} was updated", result.getId());
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        investIndexRepository.deleteById(id);
        log.info("was deleted invest index with id: {}", id);
    }

    @Override
    @Transactional
    public void deleteByName(String investIndexName) {
        investIndexRepository.deleteInvestIndexByInvestName(investIndexName);
    }
}
