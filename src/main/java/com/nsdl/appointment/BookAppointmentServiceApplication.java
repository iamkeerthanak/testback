package com.nsdl.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.nsdl.telemedicine.gateway", "com.nsdl.appointment" })public class BookAppointmentServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BookAppointmentServiceApplication.class, args);
		//System.out.println("hi");
	}


}
