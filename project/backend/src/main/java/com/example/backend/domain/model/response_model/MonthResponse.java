package com.example.backend.domain.model.response_model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.example.backend.domain.model.model.Activity;

import lombok.Data;

@Data
public class MonthResponse {
    private List<Activity> activities;
    private double totalHours = 0.0;
    private HashMap<LocalDate, Double> dateHoursMap = new HashMap<>();

    public MonthResponse(List<Activity> activities) {
        this.activities = activities;
        setTotalHours();
        setDateHoursMap();
    }

    private void setTotalHours() {
        for (Activity activity : activities) {
            totalHours += activity.getHours();
            totalHours += activity.getOvertimeHours();
        }       
    }

    private void setDateHoursMap(){
        for (Activity activity : activities) {
            LocalDate date = activity.getDate();
            double hours = activity.getHours();
            double overtimeHours = activity.getOvertimeHours();
            double total = hours + overtimeHours;
    if (this.dateHoursMap.containsKey(date)) {
        double currentTotal = this.dateHoursMap.get(date);
        this.dateHoursMap.put(date, currentTotal + total);
    } else {
        this.dateHoursMap.put(date, total);
    }
        }
    }
}
