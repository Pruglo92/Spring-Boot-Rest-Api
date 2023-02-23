package com.vkr.webapp.service;

import com.vkr.webapp.dto.SavedResultDto;
import com.vkr.webapp.entity.SavedResult;

import java.util.List;
import java.util.Optional;

public interface SavedResultService {

    Optional<SavedResult> findById(Long id);

    List<SavedResult> getAll();

    SavedResultDto update(SavedResult entity, SavedResultDto dto);

    SavedResult save(SavedResult savedResult);

    void delete(Long id);
}
