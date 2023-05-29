package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.constants.TimesheetStatuses;
import com.example.mytimesheetapp.lambdas.GetTimesheetByIdHandlerTest;
import com.example.mytimesheetapp.models.Timesheet;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

class SaveTimesheetHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(GetTimesheetByIdHandlerTest.class);
    SaveTimesheetHandler saveTimesheetHandler = new SaveTimesheetHandler();

    @Test
    public void success_saveTimesheet() {
        Context context = new TestContext();
        Timesheet timesheetEvent = new Timesheet();

        timesheetEvent.setDateSigned(new Date());
        timesheetEvent.setStatus(TimesheetStatuses.PENDING);
        timesheetEvent.setTask("APIs");
        timesheetEvent.setHourType("normal hours");
        timesheetEvent.setShift("Day");
        timesheetEvent.setComment("Easy to sign");
        timesheetEvent.setMondayHrs(8);
        timesheetEvent.setTuesdayHrs(8);
        timesheetEvent.setWednesdayHrs(8);
        timesheetEvent.setThursdayHrs(8);
        timesheetEvent.setFridayHrs(8);
        timesheetEvent.setSaturdayHrs(0);
        timesheetEvent.setSundayHrs(0);


        String results = saveTimesheetHandler.handleRequest(timesheetEvent, context);
        System.out.println("Hello There ==> " + results);
    }
}