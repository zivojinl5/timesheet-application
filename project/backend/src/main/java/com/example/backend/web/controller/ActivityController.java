package com.example.backend.web.controller;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.domain.model.create_model.ActivityCreateModel;
import com.example.backend.domain.model.model.Activity;
import com.example.backend.domain.model.response_model.DayResponse;
import com.example.backend.domain.model.response_model.MonthResponse;
import com.example.backend.domain.model.search_model.ActivitySearchModel;
import com.example.backend.domain.model.update_model.ActivityUpdateModel;
import com.example.backend.domain.service.IActivityService;
import com.example.backend.mapper.GenericMapper;
import com.example.backend.web.dto.create_dto.ActivityCreateDTO;
import com.example.backend.web.dto.dto.ActivityDTO;
import com.example.backend.web.dto.response_dto.MonthResponseDTO;
import com.example.backend.web.dto.update_dto.ActivityUpdateDTO;

@AllArgsConstructor
@RestController
@RequestMapping("/api/activities")
@CrossOrigin
public class ActivityController {
        private final IActivityService activityService;
        private final GenericMapper genericMapper;

        @GetMapping("/search")
        public ResponseEntity<MonthResponseDTO> searchActivities(
                        @RequestParam Long userId,
                        @RequestParam LocalDate startDate,
                        @RequestParam LocalDate endDate) {

                ActivitySearchModel activitySearchModel = new ActivitySearchModel(userId, startDate, endDate);

                MonthResponse monthResponse = activityService.searchDateRange(activitySearchModel);
                MonthResponseDTO monthResponseDTO = genericMapper.mapType(monthResponse, MonthResponseDTO.class);
                System.out.println(monthResponseDTO.getDateHoursMap());
                return ResponseEntity.ok(monthResponseDTO);
        }

        @GetMapping("/day")
        public ResponseEntity<DayResponse> getAllByDateAndUserId(
                        @RequestParam("date") LocalDate date,
                        @RequestParam("userId") Long userId) {
                DayResponse response = activityService.getAllByDateAndUserId(date, userId);
                return ResponseEntity.ok(response);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ActivityDTO> getActivityById(@PathVariable("id") Long id) {
                Activity foundActivity = activityService.getActivityById(id);
                ActivityDTO foundActivityDTO = genericMapper.mapType(foundActivity, ActivityDTO.class);
                ResponseEntity<ActivityDTO> responseEntity = ResponseEntity.ok(foundActivityDTO);
                return responseEntity;
        }

        @PostMapping
        public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityCreateDTO activityCreateDTO) {
                ActivityCreateModel activityCreateModel = genericMapper.mapType(activityCreateDTO,
                                ActivityCreateModel.class);
                Activity createdActivity = activityService.createActivity(activityCreateModel);
                ActivityDTO createdActivityDTO = genericMapper.mapType(createdActivity, ActivityDTO.class);
                ResponseEntity<ActivityDTO> responseEntity = ResponseEntity.ok(createdActivityDTO);
                return responseEntity;
        }

        @PatchMapping("/{id}")
        public ResponseEntity<ActivityDTO> updateActivity(@PathVariable("id") Long id,
                        @RequestBody ActivityUpdateDTO details) {
                details.setId(id);
                ActivityUpdateModel activityUpdateModel = genericMapper.mapTypeForPatch(details,
                                ActivityUpdateModel.class);
                Activity updatedActivity = activityService.updateActivity(activityUpdateModel);

                ActivityDTO updatedActivityDTO = genericMapper.mapType(updatedActivity, ActivityDTO.class);
                ResponseEntity<ActivityDTO> responseEntity = ResponseEntity.ok(updatedActivityDTO);
                return responseEntity;
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteActivityById(@PathVariable("id") Long id) {
                activityService.deleteActivityById(id);
                ResponseEntity<String> responseEntity = ResponseEntity.ok("Activity deleted");
                return responseEntity;
        }

}
