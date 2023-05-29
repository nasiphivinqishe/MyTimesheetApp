package com.example.mytimesheetapp.utils;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import com.example.mytimesheetapp.models.GetTimesheetRequest;
import org.junit.jupiter.api.Test;


class SQSUtilTest {

    SQSUtil sqsUtil = new SQSUtil();
    @Test
    public void myFunction(){
        Context context = new TestContext();
        GetTimesheetRequest getTimesheetRequest = new GetTimesheetRequest();

        getTimesheetRequest.setTimesheet_id(2);
        getTimesheetRequest.setEmail("nazavinqishe@gmail.com");

        String results = String.valueOf(sqsUtil.sendMessage(getTimesheetRequest));
        System.out.println("----Results from test---- "+results);
    }
}