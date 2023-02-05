package com.xinerji.mapper;

import com.xinerji.dto.request.CurrencyRequestDto;
import com.xinerji.repository.entity.DateStamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IDateStampMapper {

    IDateStampMapper INSTANCE = Mappers.getMapper(IDateStampMapper.class);

    DateStamp toDateStamp (final CurrencyRequestDto dto);

}
