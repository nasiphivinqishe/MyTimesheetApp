package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.mytimesheetapp.models.GetTimesheetRequest;
import com.example.mytimesheetapp.services.TimesheetService;

public class GetDownloadTimesheetRequestHandler implements RequestHandler<GetTimesheetRequest, String> {

    @Override
    public String handleRequest(GetTimesheetRequest getTimesheetRequest, Context context) {
        try {
            TimesheetService timesheetService = new TimesheetService();
            timesheetService.sendMessageToSqs(getTimesheetRequest);
            return "Successfully sent message to SQS";
//            timesheetService.downloadTimesheet(event);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Failed to send message to SQS";
        }
    }
}

