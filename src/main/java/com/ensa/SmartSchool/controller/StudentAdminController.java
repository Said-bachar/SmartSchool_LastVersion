package com.ensa.SmartSchool.controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ensa.SmartSchool.config.StageManager;
import com.ensa.SmartSchool.entity.Student;
import com.ensa.SmartSchool.service.StudentService;
import com.ensa.SmartSchool.validation.Validation;
import com.ensa.SmartSchool.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

@Component
public class StudentAdminController implements Initializable{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired @Lazy
    private StageManager stageManager;
	@FXML
	private TextField studentId;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML 
	private DatePicker dateOfBirth;
	@FXML
	private TextField email;
	@FXML
	private TextField password;
	@FXML
	private TextField phoneNumber;
	@FXML
	private TextField attempts;
	@FXML
	private TableView<Student> studentsList;
	@FXML
	private TableColumn<Student, Integer> colStudentId;
	@FXML
	private TableColumn<Student, String> colFirstName;
	@FXML
	private TableColumn<Student, String> colLastName;
	@FXML
	private TableColumn<Student, Date> colDateOfBirth;
	@FXML
	private TableColumn<Student, String> colEmail;
	@FXML
	private TableColumn<Student, String> colPassword;
	@FXML
	private TableColumn<Student, String> colPhoneNumber;
	@FXML
	private TableColumn<Student, Integer> colAttempts;
	

	@FXML
    private Button insertBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		showStudents();
		
	}
	public void showStudents() {
		List<Student> students = studentService.getStudents();
		ObservableList<Student> list = FXCollections.observableList(students);
		colStudentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		colPassword.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
		colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
		colAttempts.setCellValueFactory(new PropertyValueFactory<Student, Integer>("maxAttempts"));
		colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Student, Date>("birthDate"));
		
		studentsList.setItems(list);
	}
	
	
	
	@FXML
	private void insertStudent(ActionEvent event) {
		
		Student student = getStudentFromInput();
		if(Validation.isValidStudent(student)) {
			studentService.create(student);
			showStudents();
		}
		
	}
	
	@FXML
	public void populateFields(MouseEvent event) {

		
		Student selectedStudent = studentsList.getSelectionModel().getSelectedItem();
		studentId.setText(Integer.toString(selectedStudent.getStudentId()));
		firstName.setText(selectedStudent.getFirstName());
		lastName.setText(selectedStudent.getLastName());
		dateOfBirth.setValue(selectedStudent.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		email.setText(selectedStudent.getEmail());
		password.setText(selectedStudent.getPassword());
		phoneNumber.setText(selectedStudent.getPhoneNumber());
		attempts.setText(Integer.toString(selectedStudent.getMaxAttempts()));
		
	}
	@FXML 
	private void updateStudent(ActionEvent event) {
		
		
		Student student = getStudentFromInput();
		
		studentService.updateStudent(student);
		showStudents();
		
	}
	
	@FXML 
	private void deleteStudent() {

		Student student = getStudentFromInput();
		
		studentService.delete(student);
		showStudents();
		clearFields();
		
	}
	
	private Student getStudentFromInput() {
		Student student = new Student(firstName.getText(), lastName.getText(), email.getText(),password.getText(),
				Integer.parseInt(studentId.getText()), Date.from(dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				phoneNumber.getText(), Integer.parseInt(attempts.getText()) );
		
		return student;
	}
	
	private void clearFields() {
		studentId.setText("");
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		password.setText("");
		phoneNumber.setText("");
		attempts.setText("");
	}
	
	 @FXML
	    void goHome(MouseEvent event) {
	    	this.stageManager.switchScene(FxmlView.LOGINAS);
	    }

	    @FXML
	    void goNotices(MouseEvent event) {
	    	this.stageManager.switchScene(FxmlView.NOTICESTUDENT);
	    }
	    

	    @FXML
	    void goCourses(MouseEvent event) {
	    	this.stageManager.switchScene(FxmlView.COURSESSTUDENT1);
	    }
	    
	    @FXML
	    void goDocuments(MouseEvent event) {
	    	this.stageManager.switchScene(FxmlView.STUDENTDOCUMENTS);
	    }
	    
	    @FXML
	    void goForum(MouseEvent event) {
	    	this.stageManager.switchScene(FxmlView.FORUM);
	    }
	
	
	
	
}
