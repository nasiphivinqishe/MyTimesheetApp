package com.example.mytimesheetapp.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.mytimesheetapp.models.GetTimesheetRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyLambdaFunction implements RequestHandler<SQSEvent, Void> {

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        try {
            // Extract the payload from the SQSEvent
            String payload = event.getRecords().get(0).getBody();

            // Deserialize the payload to GetTimesheetRequest object
            ObjectMapper objectMapper = new ObjectMapper();
            GetTimesheetRequest request = objectMapper.readValue(payload, GetTimesheetRequest.class);

            // Log the payload
            System.out.println("Payload: " + payload);
            System.out.println("ID: " + request.getTimesheet_id());
            System.out.println("SomeOtherField: " + request.getEmployee_id());

        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }

        return null;
    }
}

