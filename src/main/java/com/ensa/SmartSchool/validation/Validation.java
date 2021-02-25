package com.ensa.SmartSchool.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ensa.SmartSchool.entity.Professor;
import com.ensa.SmartSchool.entity.Student;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validation {
	
public static  boolean isValidStudent(Student student) {
		
		return (validateField("first name",student.getFirstName(),"^[a-zA-Z]+$") && validateField("last name",student.getLastName(),"^[a-zA-Z]+$") &&
				validateField("email",student.getEmail(),"[a-zA-Z0-9]+@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
				validateField("id", Integer.toString(student.getStudentId()), "^[0-9]+$") && validateField("phone number", student.getPhoneNumber(),"[0-9]+$") &&
				validateField("attempts", Integer.toString(student.getMaxAttempts()), "^[0|1|2]$"));
	}

	public static boolean isValidProfessor(Professor professor) {
		return (validateField("First Name", professor.getProfessorFirstName(), "^[a-zA-Z]+$")
				&& validateField("Last Name", professor.getProfessorLastName(), "^[a-zA-Z]+$")
				&& validateField("Professor Id", Integer.toString(professor.getProfessorId()), "^[0-9]+$")
				&& validateField("Level Name", professor.getLevelName(), "^[a-zA-Z0-9]+$")
				&& validateField("Password ", professor.getPassword(), "^[a-zA-Z0-9-_*]+$")

		);

	}

	private static boolean validateField(String field, String value, String pattern) {
		if (!value.isEmpty()) {
			Pattern p = Pattern.compile(pattern);
			Matcher matcher = p.matcher(value);
			if (matcher.find() && matcher.group().equals(value)) {
				return true;
			} else {
				validationAlert(field, false);
				return false;
			}
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private static void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		if (empty) {
			alert.setContentText("Please Enter " + field);
		} else {
			alert.setContentText("Please Enter Valid " + field);
		}
		
		alert.showAndWait();

	}
}
