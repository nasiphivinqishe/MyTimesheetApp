package com.example.mytimesheetapp.utils;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.*;

@Configuration
public class SESUtil {

    Region region = Region.US_EAST_1;


    public SesV2Client createSesClient() {
        SesV2Client sesv2Client = SesV2Client.builder().region(region).credentialsProvider(ProfileCredentialsProvider.create()).build();
        return sesv2Client;
    }

//    public String send(String sender, String recipient, String subject, String bodyHTML) {
//        Destination destination = Destination.builder().toAddresses(recipient).build();
//        Content content = Content.builder().data(bodyHTML).build();
//
//        Content sub = Content.builder().data(subject).build();
//        Body body = Body.builder().html(content).build();
//
//        Message msg = Message.builder().subject(sub).body(body).build();
//
//        EmailContent emailContent = EmailContent.builder().simple(msg).build();
//
//        SendEmailRequest emailRequest = SendEmailRequest.builder().destination(destination).content(emailContent).fromEmailAddress(sender).build();
//
//        try {
//            this.createSesClient().sendEmail(emailRequest);
//            System.out.println("email was sent");
//
//        } catch (SesV2Exception e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//        return sender;
//    }

    public void send(String emailBody, String recipient) {
        String bodyText = "Hello,\r\n" + "Time sheet received!. ";
        String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
                + "<p> We hope this message finds you well. As part of our commitment to providing you with a seamless experience, we would like to inform you that your timesheets for the current period are now available for download.</p>" + "<p> To access your timesheet, simply click on the following link: Timesheet Download Link</p>" +"<p> By clicking the link, you will be directed to a secure location where you can retrieve your timesheets effortlessly. Please note that the link is unique to your account and should not be shared with anyone else.</p>" +"</b+\"<p> Should you encounter any difficulties or have any questions regarding the timesheet download process, please do not hesitate to reach out to our support team. They are ready to assist you in any way possible.</p>\"ody>"+"</b+\"<p> Thank you for your cooperation and timely submission of your timesheets. We appreciate your dedication and contribution to our organization.</p>\"ody>"+"</b+\"<p> Best Regards, TMS</p>\"ody>" + "</html>";

        String sender = "nasiphivinqishe@gmail.com";
//        String recipient = "nazavinqishe@gmail.com";
        String subject = "Email Subject";
        Destination destination = Destination.builder().toAddresses(recipient).build();
        Content content = Content.builder().data(emailBody).build();

        Content sub = Content.builder().data(subject).build();
        Body body = Body.builder().html(content).build();

        Message msg = Message.builder().subject(sub).body(body).build();

        EmailContent emailContent = EmailContent.builder().simple(msg).build();

        SendEmailRequest emailRequest = SendEmailRequest.builder().destination(destination).content(emailContent).fromEmailAddress(sender).build();
        this.createSesClient().sendEmail(emailRequest);
    }
}
