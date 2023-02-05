package com.xinerji.repository;

import com.xinerji.repository.entity.DateStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDateStampRepository extends JpaRepository<DateStamp,Long> {

    Optional<DateStamp> findOptionalByDate(String date);
}
