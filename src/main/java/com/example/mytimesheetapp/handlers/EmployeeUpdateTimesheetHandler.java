package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.mytimesheetapp.models.TimesheetUpdateRequest;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeUpdateTimesheetHandler implements RequestHandler<TimesheetUpdateRequest, String>{
    TimesheetService timesheetService = new TimesheetService();
    private final static Logger logger = LogManager.getLogger(EmployeeUpdateTimesheetHandler.class.getName());

    @Override
    public String handleRequest(TimesheetUpdateRequest timesheetUpdateRequest, Context context) {
        try {
            timesheetService.employeeUpdateTimesheet(timesheetUpdateRequest);
            return "Successfully updated timesheet status.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Error occurred on process";
        }
    }

}
