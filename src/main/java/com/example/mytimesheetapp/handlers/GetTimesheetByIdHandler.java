package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.mytimesheetapp.models.Timesheet;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetTimesheetByIdHandler implements RequestHandler<Integer, Timesheet> {
    private final static Logger logger = LogManager.getLogger(GetTimesheetByIdHandler.class.getName());
    TimesheetService timesheetService = new TimesheetService();

    @Override
    public Timesheet handleRequest(Integer timesheetId, Context context) {
        try {
            Timesheet timesheet = timesheetService.getTimesheetById(timesheetId);

            System.out.println("-----" + timesheet + "------");

            return timesheet;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
