package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.InvestIndexDto;
import com.vkr.webapp.entity.InvestIndex;
import org.mapstruct.Mapper;

@Mapper
public interface InvestIndexMapper {

    InvestIndexDto toDto(InvestIndex investIndex);
}
