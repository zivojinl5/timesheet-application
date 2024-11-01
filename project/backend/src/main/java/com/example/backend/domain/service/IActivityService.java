package com.example.backend.domain.service;

import java.time.LocalDate;

import com.example.backend.domain.model.create_model.ActivityCreateModel;
import com.example.backend.domain.model.model.Activity;
import com.example.backend.domain.model.response_model.DayResponse;
import com.example.backend.domain.model.response_model.MonthResponse;
import com.example.backend.domain.model.search_model.ActivitySearchModel;
import com.example.backend.domain.model.update_model.ActivityUpdateModel;

public interface IActivityService {

    Activity getActivityById(Long id);

    Activity createActivity(ActivityCreateModel activityCreateModel);

    Activity updateActivity(ActivityUpdateModel activityUpdateModel);

    void deleteActivityById(Long id);

    MonthResponse searchDateRange(ActivitySearchModel activitySearchModel);

    DayResponse getAllByDateAndUserId(LocalDate date, Long teamMemberId);

}
