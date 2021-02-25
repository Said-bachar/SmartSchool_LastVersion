package com.ensa.SmartSchool.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ensa.SmartSchool.config.StageManager;
import com.ensa.SmartSchool.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

@Component
public class ProfessorProfileController implements Initializable{

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label id;

    @FXML
    private Label levelName;

    @FXML
    private Label professorLogin;

    @FXML
    private Pane loginPane;

    @FXML
    private Label studentLogin1;
    
    @Autowired @Lazy
    private StageManager stageManager;

    @FXML
    void goForum(MouseEvent event) {

    }

    @FXML
    void goHome(MouseEvent event) {
    	stageManager.switchScene(FxmlView.HOMEPROFESSOR);
    }

    @FXML
    void logout(ActionEvent event) {
    	stageManager.switchScene(FxmlView.LOGINAS);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		professorLogin.setText(stageManager.getProfessor().getProfessorFirstName() + " " + stageManager.getProfessor().getProfessorLastName()+" Profil");
		firstName.setText(stageManager.getProfessor().getProfessorFirstName());
		lastName.setText(stageManager.getProfessor().getProfessorLastName());
		id.setText(String.valueOf(stageManager.getProfessor().getProfessorId()));
		levelName.setText(stageManager.getProfessor().getLevelName());
		
	}

}
