package com.example.mytimesheetapp.services;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.example.mytimesheetapp.constants.TimesheetStatuses;
import com.example.mytimesheetapp.models.GetTimesheetRequest;
import com.example.mytimesheetapp.models.Timesheet;
import com.example.mytimesheetapp.models.TimesheetStatusRequest;
import com.example.mytimesheetapp.models.TimesheetUpdateRequest;
import com.example.mytimesheetapp.repositories.TimesheetRepository;
import com.example.mytimesheetapp.utils.S3Util;
import com.example.mytimesheetapp.utils.SESUtil;
import com.example.mytimesheetapp.utils.SQSUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.util.Base64;

public class TimesheetService {
    private final static Logger logger = LogManager.getLogger(TimesheetService.class.getName());
    TimesheetRepository timesheetRepository = new TimesheetRepository();
    Timesheet timesheetResults = new Timesheet();
    SQSUtil sqsUtil = new SQSUtil();

    public Timesheet getTimesheetById(int timesheetId) throws Exception {
        Timesheet timesheet = new Timesheet();
        ResultSet resultSet = timesheetRepository.getTimesheetById(timesheetId);

        try {
            while (resultSet.next()) {
                timesheet.setDateSigned(resultSet.getDate("date_signed"));
                timesheet.setStatus(TimesheetStatuses.valueOf(resultSet.getString("status")));
                timesheet.setShift(resultSet.getString("shift"));
                timesheet.setHourType(resultSet.getString("hour_type"));
                timesheet.setComment(resultSet.getString("comment"));
                timesheet.setTask(resultSet.getString("task"));
                timesheet.setEmployee_id(resultSet.getInt("employee_id"));
                timesheet.setMondayHrs(resultSet.getInt("monday_hrs"));
                timesheet.setTuesdayHrs(resultSet.getInt("tuesday_hrs"));
                timesheet.setWednesdayHrs(resultSet.getInt("wednesday_hrs"));
                timesheet.setWednesdayHrs(resultSet.getInt("thursday_hrs"));
                timesheet.setThursdayHrs(resultSet.getInt("friday_hrs"));
                timesheet.setSaturdayHrs(resultSet.getInt("saturday_hrs"));
                timesheet.setSundayHrs(resultSet.getInt("sunday_hrs"));

                System.out.println("Timesheed data on Service:: " + timesheet);

            }
            return timesheet;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public ResultSet deleteTimesheetById(String timesheetId) throws Exception {
        logger.info("Deleting the timesheet by Id." + timesheetResults);
        return timesheetRepository.deleteTimesheetById(timesheetId);

    }

    public ResultSet saveTimesheet(Timesheet timesheetEvent) throws Exception {

        logger.info("Saving " + timesheetEvent);
        return timesheetRepository.saveTimesheet(timesheetEvent);

    }

    public ResultSet updatingTimesheetStatus(TimesheetStatusRequest timesheetUpdateStatusEvent) throws Exception {

        logger.info("Updating timesheet: " + timesheetUpdateStatusEvent);
        return timesheetRepository.updateTimesheetStatus(timesheetUpdateStatusEvent);

    }


    public ResultSet employeeUpdateTimesheet(TimesheetUpdateRequest timesheetUpdateRequest) throws Exception {

        logger.info("Updating status for this timesheet: " + timesheetUpdateRequest);
        return timesheetRepository.employeeUpdateTimesheet(timesheetUpdateRequest);

    }

    public String downloadTimesheet(SQSEvent event) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = event.getRecords().get(0).getBody();
        Timesheet payloadObject = objectMapper.readValue(payload, Timesheet.class);

        GetTimesheetRequest newPayload = objectMapper.readValue(payload, GetTimesheetRequest.class);


        Timesheet timesheet = this.getTimesheetById(payloadObject.getTimesheet_id());



        System.out.println("Timesheet on Service" + timesheet);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(100, 700);
            contentStream.endText(); // End text block
            contentStream.beginText();
            contentStream.showText("Task: " + timesheet.getTask());
//            contentStream.beginText();
//            contentStream.newLine();
            contentStream.showText("Shift: " + timesheet.getShift());
//            contentStream.endText();
//            contentStream.beginText();
//            contentStream.newLine();
            contentStream.showText("Comment: " + timesheet.getComment());
//            contentStream.endText();
//            contentStream.beginText();
//            contentStream.newLine();
            contentStream.showText("Status: " + timesheet.getStatus());
            contentStream.endText();
            contentStream.close();

            document.save(baos);

            System.out.println(baos);

            document.close();

            byte[] bytes = baos.toByteArray();
            S3Util s3Util = new S3Util();

            String pdfAsBase64String = Base64.getEncoder().encodeToString(bytes);

            s3Util.uploadObjectToS3(pdfAsBase64String);

            SESUtil sesUtil = new SESUtil();

            String emailBody = "<html>" + "<head></head>" + "<body>" + "<h4>Hello!</h4>"
                    + "<p>Your timesheets for the current period are ready for download. Access them here: Timesheet Download Link.</p>" + "<p> If you need any assistance, please contact our support team.</p>" +"<p><h4> Best Regards,</h4> TMS</p>" + "</html>";
//Need to get email from event.
            String email = newPayload.getEmail();

            String recipient = email;
            System.out.println("****************************");
            System.out.println("****************************");

            System.out.println(recipient);
            System.out.println("****************************");

            System.out.println("****************************");

            sesUtil.send(emailBody, recipient);

//            for (SQSEvent.SQSMessage msg : event.getRecords()) {
//                String messageBody = msg.getBody();
//                System.out.println("Received message: " + messageBody);
//                this.sendMessageToSqs(new GetTimesheetRequest());
//            }

            return "success";

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public String sendMessageToSqs(GetTimesheetRequest getTimesheetRequest) throws Exception {
        try {

            sqsUtil.sendMessage(getTimesheetRequest);
            return "Successfully sent message";
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
            return "Error occurred while sending message";

        }
    }


}
