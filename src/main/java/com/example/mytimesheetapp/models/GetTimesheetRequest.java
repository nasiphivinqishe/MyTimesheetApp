package com.example.mytimesheetapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetTimesheetRequest implements Serializable {
    private int timesheet_id;
    private String email;
}
