package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.SavedResultDto;
import com.vkr.webapp.entity.SavedResult;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface SavedResultMapper {

    SavedResultDto toDto(SavedResult savedResult);

    SavedResult update(@MappingTarget SavedResult targetSavedResult, SavedResult sourceSavedResult);
}
