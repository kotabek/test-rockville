package com.kotabek.services;

import com.kotabek.to.AttachmentTO;

import java.util.List;

/**
 * Created by kotabek on 6/2/17.
 */
public interface EmailService {
    void sendEmailAboutLastUploads(List<AttachmentTO> list);
}
