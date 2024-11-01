
package com.example.backend.domain.model.response_model;

import java.time.LocalDate;
import java.util.List;

import com.example.backend.domain.model.model.Activity;

import lombok.Data;

@Data
public class DayResponse {
    private LocalDate date;
    private List<Activity> activities;
    private double totalHours= 0.0;

    public DayResponse(LocalDate date, List<Activity> activities) {
        this.date = date;
        this.activities = activities;
        setTotalHours();
    }

    private void setTotalHours() {
        for (Activity activity : activities) {
            this.totalHours += activity.getHours() + activity.getOvertimeHours();
        }
    }
   

}
