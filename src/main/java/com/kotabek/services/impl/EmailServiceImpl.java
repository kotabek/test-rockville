package com.kotabek.services.impl;

import com.kotabek.services.EmailService;
import com.kotabek.to.AttachmentTO;
import com.kotabek.utils.DG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by kotabek on 6/2/17.
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailAboutLastUploads(List<AttachmentTO> list) {
        String body;
        if (list == null
            || list.isEmpty()) {
            body = "<p>No uploades</p>";
        } else {
            StringBuilder sb = new StringBuilder()
                    .append("<p>Last uploads<p>")
                    .append("<ul>");
            for (AttachmentTO to : list) {
                sb.append("<li>")
                  .append(to.getName())
                  .append("</li>");
            }
            sb.append("</ul>");
            body = sb.toString();
        }
        final String subject = "Last upload in 30 minutes";
        this.sendEmail(subject, body);
    }

    private void sendEmail(String subject, String body) {
        if(true){
            //todo please set to and from email addresses and your account access in 'application.properties'
            //todo then need commend this block
            System.out.println("Need configuration of email settings");
            return;
        }
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
//            helper.setTo("to_email@gmail.com");
//            helper.setFrom("from_email@gmail.com");
            helper.setSubject(DG.getString(subject, "Hi"));
            mail.setContent(body, "text/html");
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
