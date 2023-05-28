package com.example.mytimesheetapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class TimesheetService {
    private final static Logger logger = LogManager.getLogger(TimesheetService.class.getName());

    TimesheetRepository timesheetRepository = new TimesheetRepository();

    public ResultSet getTimesheetById(String timesheetId) throws Exception {
        logger.info("Checking the timesheet by Id. Can do more other things e.g calling other services");
        return timesheetRepository.getTimesheetById(timesheetId);
    }
}
