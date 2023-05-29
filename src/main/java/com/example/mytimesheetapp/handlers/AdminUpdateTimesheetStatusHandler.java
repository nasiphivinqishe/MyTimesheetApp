package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.mytimesheetapp.models.TimesheetStatusRequest;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminUpdateTimesheetStatusHandler implements RequestHandler<TimesheetStatusRequest, String> {
    TimesheetService timesheetService = new TimesheetService();
    private final static Logger logger = LogManager.getLogger(AdminUpdateTimesheetStatusHandler.class.getName());


    public  String handleRequest(TimesheetStatusRequest timesheetUpdateStatusEvent, Context context) {
        try {

            System.out.println(timesheetUpdateStatusEvent);

            timesheetService.updatingTimesheetStatus(timesheetUpdateStatusEvent);

            return "Successfully updated timesheet status.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Error occured on process";
        }
    }
}
