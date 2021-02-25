package com.ensa.SmartSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

	private String professorFirstName;
	private String professorLastName;
	private int professorId;
	private String levelName;
	private String password;
}
