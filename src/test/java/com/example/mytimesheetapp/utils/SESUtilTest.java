package com.example.mytimesheetapp.utils;

import com.amazonaws.services.lambda.runtime.Context;
import com.example.mytimesheetapp.commons.TestContext;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SESUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(SESUtil.class);
    @Test
    public void sendEmail() {
        Context context = new TestContext();
        SESUtil sesUtil = new SESUtil();

    String bodyText = "Hello,\r\n" + "Time sheet received!. ";
    String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
                + "<p> Time sheet received!.</p>" + "</body>" + "</html>";

    String sender = "nasiphivinqishe@gmail.com";
    String recipient = "nazavinqishe@gmail.com";
    String subject = "Email Subject";


//        String results = sesUtil.send(sender,recipient,subject, bodyHTML);

//        System.out.println("Hello There ==> " + results);
    }

}