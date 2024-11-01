package com.example.backend.data.adapter;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.backend.data.entity.CategoryEntity;
import com.example.backend.data.entity.ClientEntity;
import com.example.backend.data.entity.ProjectEntity;
import com.example.backend.data.entity.UserEntity;
import com.example.backend.data.entity.ActivityEntity;
import com.example.backend.data.repository.ICategoryJPARepository;
import com.example.backend.data.repository.IClientJPARepository;
import com.example.backend.data.repository.IProjectJPARepository;
import com.example.backend.data.repository.IUserJPARepository;
import com.example.backend.domain.domain_repository.IActivityDomainRepository;
import com.example.backend.domain.model.create_model.ActivityCreateModel;
import com.example.backend.domain.model.model.Activity;
import com.example.backend.domain.model.response_model.DayResponse;
import com.example.backend.domain.model.response_model.MonthResponse;
import com.example.backend.domain.model.search_model.ActivitySearchModel;
import com.example.backend.domain.model.update_model.ActivityUpdateModel;
import com.example.backend.data.repository.IActivityJPARepository;
import com.example.backend.mapper.GenericMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ActivityAdapter implements IActivityDomainRepository {

    private final IActivityJPARepository activityJPARepository;
    private final IClientJPARepository clientJPARepository;
    private final IProjectJPARepository projectJPARepository;
    private final ICategoryJPARepository categoryJPARepository;
    private final IUserJPARepository userJPARepository;
    private final GenericMapper genericMapper;

    @Override
    public MonthResponse searchDateRange(ActivitySearchModel searchModel) {
        Long userId = searchModel.getUserId();
        List<ActivityEntity> activityEntities = activityJPARepository.search(searchModel.getStarDate(),
                searchModel.getEndDate(),
                userId);
        List<Activity> activities = genericMapper.mapList(activityEntities, Activity.class);
        MonthResponse response = new MonthResponse(activities);
        return response;
    }

    @Override
    public DayResponse findAllByDateAndUserId(LocalDate date, Long id) {
        List<ActivityEntity> activityEntities = activityJPARepository.findAllByDateAndUserId(date, id);
        List<Activity> activities = genericMapper.mapList(activityEntities, Activity.class);
        DayResponse dayResponse = new DayResponse(date, activities);
        return dayResponse;
    }

    @Override
    public Activity findById(Long id) {
        ActivityEntity activityEntity = activityJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
        Activity activity = genericMapper.mapType(activityEntity, Activity.class);
        return activity;
    }

    @Override
    public Activity save(ActivityCreateModel activityCreateModel) {
        ActivityEntity activityEntity = genericMapper.mapType(activityCreateModel, ActivityEntity.class);
        ClientEntity clientEntity = clientJPARepository.findById(activityCreateModel.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        ProjectEntity projectEntity = projectJPARepository.findById(activityCreateModel.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        CategoryEntity categoryEntity = categoryJPARepository.findById(activityCreateModel.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        UserEntity userEntity = userJPARepository.findById(activityCreateModel.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        activityEntity.setClient(clientEntity);
        activityEntity.setProject(projectEntity);
        activityEntity.setCategory(categoryEntity);
        activityEntity.setUser(userEntity);
        ActivityEntity createdActivityEntity = activityJPARepository.save(activityEntity);
        Activity createdActivity = genericMapper.mapType(createdActivityEntity, Activity.class);
        return createdActivity;
    }

    @Override
    public Activity update(ActivityUpdateModel activityUpdateModel) {
        ActivityEntity existingActivityEntity = activityJPARepository.findById(activityUpdateModel.getId())
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
        System.out.println(existingActivityEntity);
        existingActivityEntity = genericMapper.mapTypeForPatch(activityUpdateModel, ActivityEntity.class);
        System.out.println(existingActivityEntity);

        if (activityUpdateModel.getUserId() != null) {
            Long userId = activityUpdateModel.getUserId();
            UserEntity userEntity = userJPARepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Activity patch:User not found"));
            existingActivityEntity.setUser(userEntity);
        }
        System.err.println(existingActivityEntity);

        if (activityUpdateModel.getClientId() != null) {
            Long clientId = activityUpdateModel.getClientId();
            ClientEntity clientEntity = clientJPARepository.findById(clientId)
                    .orElseThrow(() -> new IllegalArgumentException("Activity patch:Client not found"));
            existingActivityEntity.setClient(clientEntity);
        }
        System.out.println(existingActivityEntity);

        if (activityUpdateModel.getProjectId() != null) {
            Long projectId = activityUpdateModel.getProjectId();
            ProjectEntity projectEntity = projectJPARepository.findById(projectId)
                    .orElseThrow(() -> new IllegalArgumentException("Activity patch:Project not found"));
            existingActivityEntity.setProject(projectEntity);
        }
        System.out.println(existingActivityEntity);

        if (activityUpdateModel.getCategoryId() != null) {
            Long categoryId = activityUpdateModel.getCategoryId();
            CategoryEntity categoryEntity = categoryJPARepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Activity patch:Category not found"));
            existingActivityEntity.setCategory(categoryEntity);
        }
        System.out.println(existingActivityEntity);

        ActivityEntity updatedEntity = activityJPARepository.save(existingActivityEntity);
        System.out.println(existingActivityEntity);
        Activity updatedActivity = genericMapper.mapType(updatedEntity, Activity.class);
        System.out.println(existingActivityEntity);
        return updatedActivity;
    }

    @Override
    public void deleteById(Long id) {
        activityJPARepository.deleteById(id);
    }

}
