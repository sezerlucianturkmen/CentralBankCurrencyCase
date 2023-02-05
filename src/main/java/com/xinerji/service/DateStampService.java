package com.xinerji.service;

import com.xinerji.repository.IDateStampRepository;
import com.xinerji.repository.entity.DateStamp;
import com.xinerji.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DateStampService extends ServiceManager<DateStamp,Long> {
    private final IDateStampRepository repository;

    public DateStampService(IDateStampRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Optional<DateStamp> findOptionalByDate(String date) {
        return repository.findOptionalByDate(date);
    }
}
