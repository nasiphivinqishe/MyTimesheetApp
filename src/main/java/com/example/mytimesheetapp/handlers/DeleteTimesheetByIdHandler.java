package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.mytimesheetapp.services.TimesheetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class DeleteTimesheetByIdHandler implements RequestHandler<String, String> {
    private final static Logger logger = LogManager.getLogger(DeleteTimesheetByIdHandler.class.getName());
    TimesheetService timesheetService = new TimesheetService();

    public String handleRequestt(String timesheetId, Context context) {
        try {
            ResultSet resultSet = timesheetService.deleteTimesheetById(timesheetId);
            System.out.println("timesheetId");
            System.out.println(timesheetId);
            System.out.println("timesheetId");
            while (resultSet.next()) {
                String t = resultSet.getString("timesheet_id");
                logger.info("Deleted..."+t);
            }
            return "Successfully deleted timesheet.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Error occured on process";
        }
    }


    @Override
    public String handleRequest(String s, Context context) {
        return null;
    }
}
