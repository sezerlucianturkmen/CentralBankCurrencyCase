package com.xinerji.repository;

import com.xinerji.repository.entity.Currency;
import com.xinerji.repository.entity.DateStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICurrencyRepository extends JpaRepository<Currency,Long> {
    Optional<List<Currency>> findAllOptionalByDateStamp(DateStamp dateStamp);

}
