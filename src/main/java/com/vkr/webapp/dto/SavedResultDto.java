package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import com.vkr.webapp.entity.InvestIndex;
import com.vkr.webapp.entity.User;

import javax.validation.constraints.Positive;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SavedResultDto(

        Long id,

        Integer periodNum,

        Integer interestRate,

        Integer initSum,

        Integer futureValue,

        Integer accrPerNum,

        Integer annualIncome,

        Integer totalAmount,

        InvestIndexDto investIndexDto,

        UserDto userDto

        ){

}
