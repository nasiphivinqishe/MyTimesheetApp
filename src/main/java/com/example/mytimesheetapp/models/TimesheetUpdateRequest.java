package com.example.mytimesheetapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetUpdateRequest implements Serializable {
    private int timesheetId;
    private Timesheet timesheetUpdate;
}
