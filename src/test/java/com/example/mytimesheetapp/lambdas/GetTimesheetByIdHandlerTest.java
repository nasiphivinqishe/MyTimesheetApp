package com.example.mytimesheetapp.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.handlers.GetTimesheetByIdHandler;
import com.example.mytimesheetapp.models.Timesheet;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetTimesheetByIdHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(GetTimesheetByIdHandlerTest.class);
    GetTimesheetByIdHandler getTimesheetByIdHandler = new GetTimesheetByIdHandler();

    @Test
    public void success_getTimesheetById() {
        Context context = new TestContext();
        int event = 3;

        Timesheet results = getTimesheetByIdHandler.handleRequest(event, context);
        System.out.println(results);
    }
}
