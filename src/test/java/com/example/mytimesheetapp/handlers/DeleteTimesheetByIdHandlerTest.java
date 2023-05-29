package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DeleteTimesheetByIdHandlerTest {
    private static final Logger logger = LoggerFactory.getLogger(DeleteTimesheetByIdHandlerTest.class);
    DeleteTimesheetByIdHandler deleteTimesheetByIdHandler = new DeleteTimesheetByIdHandler();

    @Test
    public void downloadTimesheet() {
        Context context = new TestContext();
        String event ="4";

        String results = deleteTimesheetByIdHandler.handleRequestt(event, context);
        System.out.println(results);
    }

}