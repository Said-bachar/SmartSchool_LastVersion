package com.ensa.SmartSchool.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Student {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int studentId;
	private Date birthDate;
	private String phoneNumber;
	private int maxAttempts;
	
	
	
}
