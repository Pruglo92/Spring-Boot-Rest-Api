package com.vkr.webapp.repository;

import com.vkr.webapp.entity.SavedResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavedResultRepository extends JpaRepository<SavedResult, Long> {

}
