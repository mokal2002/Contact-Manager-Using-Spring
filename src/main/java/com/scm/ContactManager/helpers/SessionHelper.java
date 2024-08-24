package com.scm.ContactManager.helpers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Component
public class SessionHelper {
    public static void removeMessage(){
        // Implement session removal logic here
        try{
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        }catch(Exception e){
            System.out.println("Error removing session: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
