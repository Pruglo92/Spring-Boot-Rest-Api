package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.InvestIndexDto;
import com.vkr.webapp.entity.InvestIndex;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface InvestIndexMapper {

    InvestIndexDto toDto(InvestIndex investIndex);

    InvestIndex update(@MappingTarget InvestIndex targetInvestIndex, InvestIndex sourceInvestIndex);
}
