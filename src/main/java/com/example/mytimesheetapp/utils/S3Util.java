package com.example.mytimesheetapp.utils;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class S3Util {
    private static final String BUCKET_NAME = "my-timesheet-bucket";
    static UUID uuid = UUID.randomUUID();
    static LocalDateTime timestamp = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    static String formattedTimestamp = timestamp.format(formatter);
    private static final String OBJECT_KEY = "Timesheets/"+formattedTimestamp + "_" + uuid.toString()+".pdf";

    public S3Client createS3Client() {
        Region region = Region.US_EAST_1; // Replace with your desired region

        S3Client s3Client = S3Client.builder()
                .region(region)
                .build();

        return s3Client;
    }

    public void uploadObjectToS3(String base64String) {
        try {
            byte[] data = java.util.Base64.getDecoder().decode(base64String);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(OBJECT_KEY)
                    .build();

            this.createS3Client().putObject(request, RequestBody.fromBytes(data));

            System.out.println("Object uploaded successfully.");
        } catch (S3Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

