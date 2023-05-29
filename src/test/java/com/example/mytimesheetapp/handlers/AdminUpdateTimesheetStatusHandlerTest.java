package com.example.mytimesheetapp.handlers;
import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.constants.TimesheetStatuses;
import com.example.mytimesheetapp.models.TimesheetStatusRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AdminUpdateTimesheetStatusHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(AdminUpdateTimesheetStatusHandlerTest.class);
    AdminUpdateTimesheetStatusHandler adminUpdateTimesheetStatusHandler = new AdminUpdateTimesheetStatusHandler();

    @Test
    public void updateTimesheetStatusToApproved (){
        Context context = new TestContext();
        TimesheetStatusRequest timesheetUpdateStatusEvent = new TimesheetStatusRequest();

        timesheetUpdateStatusEvent.setDateUpdated(new Date());
        timesheetUpdateStatusEvent.setStatus(TimesheetStatuses.APPROVED);
        timesheetUpdateStatusEvent.setTimesheet_id(2);
        timesheetUpdateStatusEvent.setComment("Cool");


        String results = adminUpdateTimesheetStatusHandler.handleRequest(timesheetUpdateStatusEvent, context);
        System.out.println(timesheetUpdateStatusEvent);
        System.out.println("Hello There ==> " + results);
    }
    @Test
    public void updateTimesheetStatusToDeclined (){
        Context context = new TestContext();
        TimesheetStatusRequest timesheetUpdateStatusEvent = new TimesheetStatusRequest();


        timesheetUpdateStatusEvent.setDateUpdated(new Date());
        timesheetUpdateStatusEvent.setStatus(TimesheetStatuses.DECLINED);
        timesheetUpdateStatusEvent.setTimesheet_id(2);
        timesheetUpdateStatusEvent.setComment("You did not specify hours worked");


        String results = adminUpdateTimesheetStatusHandler.handleRequest(timesheetUpdateStatusEvent, context);
        System.out.println(timesheetUpdateStatusEvent);
        System.out.println("Hello There ==> " + results);
    }
}
