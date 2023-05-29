package com.example.mytimesheetapp.utils;


import com.example.mytimesheetapp.services.TimesheetService;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PutObject {
    private static final Region REGION = Region.US_EAST_1; // Change this to your desired region
    private static final String BUCKET_NAME = "my-timesheet-bucket"; // Change this to your S3 bucket name
    static UUID uuid = UUID.randomUUID();
    static LocalDateTime timestamp = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    static String formattedTimestamp = timestamp.format(formatter);
    private static final String uniqueFilename = formattedTimestamp + "_" + uuid.toString();
    public static S3Client getS3Client() {
        return S3Client.builder()
                .region(REGION)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public static void uploadS3Object(String base64String) {
        try {
//            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64String);

            ByteBuffer byteBuffer = ByteBuffer.wrap(decodedBytes);

            S3Client s3Client = getS3Client();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(uniqueFilename) // Provide a key for the object
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromByteBuffer(byteBuffer));
            System.out.println("Object uploaded. ETag: " + response.eTag());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TimesheetService timesheetService = new TimesheetService();

        String base64String = "timesheetService.downloadTimesheet(baos)"; // Replace with your actual base64 string
        uploadS3Object(base64String);
    }
}