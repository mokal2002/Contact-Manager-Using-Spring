package com.scm.ContactManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.ContactManager.entities.Contact;
import com.scm.ContactManager.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    public Contact getContact(@PathVariable String id) {
        // Return contact data
        return contactService.getById(id); // Use the path variable `id` here
    }
}
