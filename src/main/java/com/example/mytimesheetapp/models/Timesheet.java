package com.example.mytimesheetapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetResults {
    private Date date_signed;
    private char status;
    private char task;
    private char hour_type;
    private char shift;
    private char comment;
    private char monday_hrs;
    private char tuesday_hrs;
    private char wednesday_hrs;
    private char thurday_hrs;
    private char friday_hrs;
    private char saturday_hrs;
    private char sunday_hrs;
    private int employee_id;
    private String timesheet_id;
}
