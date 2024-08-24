package com.scm.ContactManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.ContactManager.services.EmailServices;

@SpringBootTest
class ContactManagerApplicationTests {

	@Autowired
	private EmailServices service;
	@Test
	void contextLoads() {
	}

	@Test
	void sendEmailTest(){
		service.sendEmail(
			"mokalaniket29@gmail.com",
			"Just like",
			"test"
		);
	}

}
