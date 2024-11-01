package com.example.backend.domain.domain_repository;

import java.time.LocalDate;

import com.example.backend.domain.model.create_model.ActivityCreateModel;
import com.example.backend.domain.model.model.Activity;
import com.example.backend.domain.model.response_model.DayResponse;
import com.example.backend.domain.model.response_model.MonthResponse;
import com.example.backend.domain.model.search_model.ActivitySearchModel;
import com.example.backend.domain.model.update_model.ActivityUpdateModel;

public interface IActivityDomainRepository {
    DayResponse findAllByDateAndUserId(LocalDate date, Long id);

    Activity findById(Long id);

    Activity save(ActivityCreateModel timeSheetEntryCreateModel);

    Activity update(ActivityUpdateModel model);

    void deleteById(Long id);

    MonthResponse searchDateRange(ActivitySearchModel searchModel);
}
