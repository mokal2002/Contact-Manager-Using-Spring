package com.scm.ContactManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.ContactManager.entities.Contact;
import com.scm.ContactManager.entities.User;
import com.scm.ContactManager.helpers.AppConstants;
import com.scm.ContactManager.helpers.Helper;
import com.scm.ContactManager.services.ContactService;
import com.scm.ContactManager.services.impl.UserServiceImpl;



@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/dashboard")
    public String userDashboard(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
            Authentication authentication) {
                String username = Helper.getEmailOfLoggedInUser(authentication);
                User user = userService.getUserByEmail(username);
                Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);
                model.addAttribute("pageContact", pageContact);
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Model model,Authentication authentication) {

        return "user/profile";
    }

}
