package com.kotabek.schedule;

import com.kotabek.services.EmailService;
import com.kotabek.services.StorageService;
import com.kotabek.to.AttachmentFilterTO;
import com.kotabek.to.AttachmentTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kotabek on 6/2/17.
 */
@Component
public class JobLastUploads {
    @Autowired
    private EmailService emailService;
    @Autowired
    private StorageService storageService;

    //    @Scheduled(fixedRate = 1000 * 60 * 30)
    @Scheduled(cron = "0 0/30 * * * *")
//    @Scheduled(cron = "0 0/1 * * * *")
    public void reportCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -30);
        AttachmentFilterTO filterTO = new AttachmentFilterTO();
        filterTO.setAfterTime(calendar.getTimeInMillis());
        List<AttachmentTO> list = storageService.search(filterTO);
        emailService.sendEmailAboutLastUploads(list);
    }
}
