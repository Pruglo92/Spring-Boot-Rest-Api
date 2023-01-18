package com.vkr.webapp.service;

import com.vkr.webapp.entity.SavedResult;

import java.util.List;
import java.util.Optional;

public interface SavedResultService {

    SavedResult getById(Long id);

    List<SavedResult> getAll();

    SavedResult update(SavedResult targetSavedResult, SavedResult sourceSavedResult);

    SavedResult save(SavedResult savedResult);

    void delete(Long id);
}
