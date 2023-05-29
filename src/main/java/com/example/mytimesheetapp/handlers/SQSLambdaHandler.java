package com.example.mytimesheetapp.handlers;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

public class SQSLambdaHandler implements RequestHandler<SQSEvent, Void>{

    public Void handleRequest(SQSEvent message, Context context) {
        for (SQSEvent.SQSMessage messages : message.getRecords()) {
            String messageBody = messages.getBody();
            // Process the message as needed
            System.out.println("Received message: " + messageBody);
        }
        return null;
    }
}




