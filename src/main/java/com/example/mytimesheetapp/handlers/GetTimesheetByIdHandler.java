package com.example.mytimesheetapp;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

public class GetTimesheetByIdHandler implements RequestHandler<String, String> {
    private final static Logger logger = LogManager.getLogger(GetTimesheetByIdHandler.class.getName());
    TimesheetService timesheetService = new TimesheetService();

    @Override
    public String handleRequest(String timesheetId, Context context) {
        try {
            ResultSet resultSet = timesheetService.getTimesheetById(timesheetId);
            while (resultSet.next()) {
                String t = resultSet.getString("timesheet_id");
                logger.info("Have found result..."+t);
            }
            return "Successfully got results.";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Error occured on process";
        }
    }
}
