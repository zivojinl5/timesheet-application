
package com.example.backend.domain.service_Implementation;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.backend.domain.domain_repository.IActivityDomainRepository;
import com.example.backend.domain.model.create_model.ActivityCreateModel;
import com.example.backend.domain.model.model.Activity;
import com.example.backend.domain.model.response_model.DayResponse;
import com.example.backend.domain.model.response_model.MonthResponse;
import com.example.backend.domain.model.search_model.ActivitySearchModel;
import com.example.backend.domain.model.update_model.ActivityUpdateModel;
import com.example.backend.domain.service.IActivityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private final IActivityDomainRepository activityDomainRepository;

    @Override
    public Activity getActivityById(Long id) {
        return activityDomainRepository.findById(id);
    }

    @Override
    public Activity createActivity(ActivityCreateModel activityCreateModel) {
        return activityDomainRepository.save(activityCreateModel);

    }

    @Override
    public Activity updateActivity(ActivityUpdateModel activityUpdateModel) {
        return activityDomainRepository.update(activityUpdateModel);
    }

    @Override
    public void deleteActivityById(Long id) {
        activityDomainRepository.deleteById(id);

    }

    @Override
    public DayResponse getAllByDateAndUserId(LocalDate date, Long id) {
        return activityDomainRepository.findAllByDateAndUserId(date, id);

    }

    @Override
    public MonthResponse searchDateRange(ActivitySearchModel searchModel) {
        return activityDomainRepository.searchDateRange(searchModel);
    }

}
