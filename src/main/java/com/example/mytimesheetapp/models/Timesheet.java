package com.example.mytimesheetapp.models;

import com.example.mytimesheetapp.constants.TimesheetStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timesheet implements Serializable {
    private Date dateSigned;
    private TimesheetStatuses status;
    private String task;
    private String hourType;
    private String shift;
    private String comment;
    private int mondayHrs;
    private int tuesdayHrs;
    private int wednesdayHrs;
    private int thursdayHrs;
    private int fridayHrs;
    private int saturdayHrs;
    private int sundayHrs;
    private int employee_id;
    private int timesheet_id;
    private  String email;
}
