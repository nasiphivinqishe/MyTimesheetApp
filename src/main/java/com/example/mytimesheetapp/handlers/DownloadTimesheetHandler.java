package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadTimesheetHandler implements RequestHandler<SQSEvent, String> {
    private final static Logger logger = LogManager.getLogger(GetTimesheetByIdHandler.class.getName());
    TimesheetService timesheetService = new TimesheetService();

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        try {
            TimesheetService timesheetService = new TimesheetService();
            timesheetService.downloadTimesheet(event);
            System.out.println(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
