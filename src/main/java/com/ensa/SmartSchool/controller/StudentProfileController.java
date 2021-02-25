package com.ensa.SmartSchool.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ensa.SmartSchool.config.StageManager;
import com.ensa.SmartSchool.view.FxmlView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

@Component
public class StudentProfileController implements Initializable {
	
	@Autowired @Lazy
    private StageManager stageManager;
	 @FXML
	 private Label studentLogin;
	 @FXML
	 private Pane loginPane;
	 @FXML
	 private Label studentLogin1;
	 @FXML
	 private Label firstName;
	 @FXML
	 private Label lastName;
	 @FXML
	 private Label phoneNumber;
	 @FXML
	 private Label id;
	 @FXML
	 private Label email;
	 @FXML
	 private Label birthday;

	 
	 @FXML
	 void goHome(MouseEvent event) {
		 this.stageManager.switchScene(FxmlView.HOMESTUDENT);
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

	 }
	 @FXML   
	 void goProfile(MouseEvent event) {

	 } 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		studentLogin.setText(stageManager.getStudent().getFirstName() + " " + stageManager.getStudent().getLastName()+" Profil");
		firstName.setText(stageManager.getStudent().getFirstName());
		lastName.setText(stageManager.getStudent().getLastName());
		phoneNumber.setText(stageManager.getStudent().getPhoneNumber());
		id.setText(String.valueOf(stageManager.getStudent().getStudentId()));
		email.setText(stageManager.getStudent().getEmail());
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		birthday.setText(sdf.format(stageManager.getStudent().getBirthDate()));
	}

}
