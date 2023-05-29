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
public class TimesheetStatusRequest implements Serializable {
    private TimesheetStatuses status;
    private String comment;
    private int timesheet_id;
    private Date dateUpdated;
}
