package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.constants.TimesheetStatuses;
import com.example.mytimesheetapp.models.Timesheet;
import com.example.mytimesheetapp.models.TimesheetUpdateRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

class EmployeeUpdateTimesheetHandlerTest {
    EmployeeUpdateTimesheetHandler employeeUpdateTimesheetHandler = new EmployeeUpdateTimesheetHandler();

    @Test
    public void employeeUpdateTimesheet (){
        Context context = new TestContext();
        TimesheetUpdateRequest timesheetUpdateRequest = new TimesheetUpdateRequest();
        Timesheet timesheet = new Timesheet();

        timesheet.setDateSigned(new Date());
        timesheet.setStatus(TimesheetStatuses.APPROVED);
        timesheet.setTask("Database Design");
        timesheet.setHourType("8 hours a day");
        timesheet.setShift("normal shift");
        timesheet.setComment("Easy");
        timesheet.setMondayHrs(8);
        timesheet.setTuesdayHrs(8);
        timesheet.setWednesdayHrs(8);
        timesheet.setThursdayHrs(8);
        timesheet.setFridayHrs(8);
        timesheet.setSaturdayHrs(0);
        timesheet.setSundayHrs(0);

        timesheetUpdateRequest.setTimesheetId(4);
        timesheetUpdateRequest.setTimesheetUpdate(timesheet);

        String results = employeeUpdateTimesheetHandler.handleRequest(timesheetUpdateRequest, context);
        System.out.println("Hello There ==> " + results);

    }
}