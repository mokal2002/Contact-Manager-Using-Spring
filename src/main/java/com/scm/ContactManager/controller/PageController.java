package com.scm.ContactManager.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.scm.ContactManager.entities.User;
import com.scm.ContactManager.forms.UserForm;
import com.scm.ContactManager.helpers.Message;
import com.scm.ContactManager.helpers.MessageType;
import com.scm.ContactManager.services.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;


@Controller
public class PageController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/thanks")
    public String dothanks() {
        return "thanks";
    }


    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value="/do-register" , method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {


        if(rBindingResult.hasErrors()){
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://en.wikipedia.org/wiki/User_(computing)#/media/File:User_icon_2.svg");

        @SuppressWarnings("unused")
        User savedUser = userService.saveUser(user);
        // System.out.println("user save");

        Message message = Message.builder().content("Registration successful").type(MessageType.green).build();

        session.setAttribute("message", message);


        return "redirect:/register";
    }



}
