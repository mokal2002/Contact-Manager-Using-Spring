package com.scm.ContactManager.services;

public interface EmailServices {
    void sendEmail(String to,String subject,String body);
    void sendEmailWithHtml();
    void sendEmailWithAttachments();
}
