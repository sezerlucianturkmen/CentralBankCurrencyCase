package com.xinerji.controller;

import com.xinerji.dto.request.CurrencyRequestDto;
import com.xinerji.dto.response.CurrencyResponseDto;
import com.xinerji.exception.ErrorType;
import com.xinerji.exception.ManagerException;
import com.xinerji.service.CurrencyService;
import com.xinerji.service.DateStampService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.xinerji.constant.EndPoint.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CURRENCY)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final DateStampService dateStampService;

    @PostMapping(ASKCURRENCY)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<CurrencyResponseDto>> createCandidate(@RequestBody @Valid CurrencyRequestDto dto){
        if(dateStampService.findOptionalByDate(dto.getDate()).isPresent()){
            System.out.println("it is exist on database already.");
        }else {
            currencyService.saveCurrencies(dto);
        }
        return ResponseEntity.ok(currencyService.getCurrencies(dto));
    }


}
