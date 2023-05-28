package com.example.mytimesheetapp;
import com.amazonaws.services.lambda.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
public class GetTimesheetByIdHandlerTest{
    private static final Logger logger = LoggerFactory.getLogger(GetTimesheetByIdHandlerTest.class);
    GetTimesheetByIdHandler getTimesheetByIdHandler = new GetTimesheetByIdHandler();

    @Test
    public void success_getTimesheetById() {
        Context context = new TestContext();
        String event ="1002";

        String results = getTimesheetByIdHandler.handleRequest(event, context);
        System.out.println(results);
    }
}
