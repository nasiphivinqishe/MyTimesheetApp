package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.models.GetTimesheetRequest;
import org.testng.annotations.Test;

public class GetDownloadTimesheetRequestHandlerTest {

   @Test
    public void getDownloadTimesheet() {
       Context context = new TestContext();
       GetTimesheetRequest getTimesheetRequest = new GetTimesheetRequest();
       GetDownloadTimesheetRequestHandler getDownloadTimesheetRequestHandler = new GetDownloadTimesheetRequestHandler();

       getTimesheetRequest.setTimesheet_id(2);
       getTimesheetRequest.setEmail("nazavinqishe@gmail.com");

       String results = getDownloadTimesheetRequestHandler.handleRequest(getTimesheetRequest, context);
       System.out.println(results);
    }
}
