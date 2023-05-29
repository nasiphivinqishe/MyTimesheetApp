package com.example.mytimesheetapp.utils;

import com.example.mytimesheetapp.models.GetTimesheetRequest;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

public class SQSUtil {
    Region region = Region.US_EAST_1;

    public SqsClient createSqsClient() {
        SqsClient sqsClient = SqsClient.builder().region(region).credentialsProvider(ProfileCredentialsProvider.create()).build();
        return sqsClient;
    }

    public String sendMessage(GetTimesheetRequest getTimesheetRequest) {
        String queueUrl = "https://sqs.us-east-1.amazonaws.com/916751233379/MyTimesheetQueue";
        String message= "Sending message along with 1,2,3";
        try {
            SendMessageRequest sendMsgRequest = SendMessageRequest.builder().queueUrl(queueUrl).messageBody(message).delaySeconds(5).build();

            this.createSqsClient().sendMessage(sendMsgRequest);
            System.out.println("Message sent __ QUEUE URL :: "+ queueUrl+ " && Message: "+ message);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "getTimesheetRequest";
    }

}
