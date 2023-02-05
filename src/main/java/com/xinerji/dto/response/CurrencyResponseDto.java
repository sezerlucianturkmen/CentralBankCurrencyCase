package com.xinerji.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyResponseDto {

    private String currencyCode;
    private Integer unit;
    private String currencyName;
    private Double forexBuying;
    private Double forexSelling;
    private Double banknoteBuying;
    private Double banknoteSelling;

}
