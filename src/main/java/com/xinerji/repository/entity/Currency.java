package com.xinerji.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "tblcurrency")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DateStamp dateStamp;

    private String currencyCode;
    private Integer unit;
    private String currencyName;
    private Double forexBuying;
    private Double forexSelling;
    private Double banknoteBuying;
    private Double banknoteSelling;




}
