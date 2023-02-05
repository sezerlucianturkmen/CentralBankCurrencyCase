package com.xinerji.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRequestDto {
    @NotEmpty(message = "Section may not be empty")
    private String date;
}
