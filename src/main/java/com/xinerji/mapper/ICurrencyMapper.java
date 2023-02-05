package com.xinerji.mapper;

import com.xinerji.dto.response.CurrencyResponseDto;
import com.xinerji.repository.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ICurrencyMapper {
    ICurrencyMapper INSTANCE = Mappers.getMapper(ICurrencyMapper.class);
    CurrencyResponseDto toCurrencyResponseDto (final Currency currency);

}
