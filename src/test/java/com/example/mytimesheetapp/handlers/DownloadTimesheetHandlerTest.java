package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DownloadTimesheetHandlerTest {
    DownloadTimesheetHandler downloadTimesheetHandler = new DownloadTimesheetHandler();

    @Test
    public void downloadTimesheetBy() {
//        Context context = new TestContext();
//        int timesheetId = 3;
//
//        String results = downloadTimesheetHandler.handleRequest(timesheetId, context);
//        System.out.println("Results from test:: "+results);

        // Create a mock SQSEvent
        SQSEvent sqsEvent = mock(SQSEvent.class);
        SQSEvent.SQSMessage message = mock(SQSEvent.SQSMessage.class);
        when(sqsEvent.getRecords()).thenReturn(Collections.singletonList(message));
        when(message.getBody()).thenReturn("{\"timesheet_id\": 3, \"email\": \"nazavinqishe@gmail.com\"}");

        // Create a mock Context
        Context context = mock(Context.class);

        // Create an instance of MyLambdaFunction and invoke the handler
//        GetDownloadTimesheetRequestHandler lambdaFunction = new GetDownloadTimesheetRequestHandler();
        DownloadTimesheetHandler secondLambdaFunction = new DownloadTimesheetHandler();
//        lambdaFunction.handleRequest(sqsEvent, context);
        secondLambdaFunction.handleRequest(sqsEvent, context);
        // Verify that the payload is logged or check other assertions as needed
    }

}