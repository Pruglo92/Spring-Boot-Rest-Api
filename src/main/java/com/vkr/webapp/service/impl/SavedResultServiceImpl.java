package com.vkr.webapp.service.impl;

import com.vkr.webapp.entity.SavedResult;
import com.vkr.webapp.exception.SavedResultNotFoundException;
import com.vkr.webapp.exception.UserCreateException;
import com.vkr.webapp.exception.UserNotFoundException;
import com.vkr.webapp.mapper.SavedResultMapper;
import com.vkr.webapp.repository.SavedResultRepository;
import com.vkr.webapp.service.SavedResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class SavedResultServiceImpl implements SavedResultService {

    private final SavedResultRepository savedResultRepository;

    private final SavedResultMapper savedResultMapper;

    @Override
    public SavedResult getById(Long id) {
        return savedResultRepository.findById(id)
                .orElseThrow(() -> new SavedResultNotFoundException("Ops saved result not found"));
    }

    @Override
    public List<SavedResult> getAll() {
        var savedResults = savedResultRepository.findAll();
        log.info("Saved results repository has {} entries", savedResults.size());
        return savedResults;
    }

    @Override
    @Transactional
    public SavedResult update(SavedResult targetSavedResult, SavedResult sourceSavedResult) {
        var result = savedResultMapper.update(targetSavedResult, sourceSavedResult);
        log.info("Saved result id {} was updated", result.getId());
        return result;
    }

    @Override
    @Transactional
    public SavedResult save(SavedResult savedResult) {
        var result = savedResultRepository.save(savedResult);
        log.info("save result was saved with id: {}", savedResult.getId());
        return result;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        savedResultRepository.deleteById(id);
        log.info("was deleted saved result with id: {}", id);
    }
}
