package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.models.Timesheet;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminUpdateTimesheetStatus {
    TimesheetService timesheetService = new TimesheetService();
    private final static Logger logger = LogManager.getLogger(SaveTimesheetHandler.class.getName());


    public String handleRequest(Timesheet timesheetEvent, Context context) {
        try {

            System.out.println(timesheetEvent);

            timesheetService.saveTimesheet(timesheetEvent);

            return "Successfully updated timesheet status.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Error occured on process";
        }
    }
}
