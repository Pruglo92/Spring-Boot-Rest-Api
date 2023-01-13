package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.SavedResultDto;
import com.vkr.webapp.entity.SavedResult;
import org.mapstruct.Mapper;

@Mapper
public interface SavedResultMapper {

    SavedResultDto toDto(SavedResult savedResult);
}
