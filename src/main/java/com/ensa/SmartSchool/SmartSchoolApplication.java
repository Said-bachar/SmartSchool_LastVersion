package com.ensa.SmartSchool;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;


@SpringBootApplication
public class SmartSchoolApplication {
	
	public static void main(String[] args) {
		
		Application.launch(SmartSchoolJavaFX.class, args);			
	}
	
}
