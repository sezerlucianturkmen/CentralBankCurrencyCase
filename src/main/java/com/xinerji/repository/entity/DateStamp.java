package com.xinerji.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbldatestamp")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DateStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String yyyyMM;
    private String ddMMyyyy;
    @OneToMany(mappedBy = "dateStamp",cascade = CascadeType.ALL)
    private List<Currency> currencies;
}
