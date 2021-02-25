package com.ensa.SmartSchool.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ensa.SmartSchool.config.StageManager;
import com.ensa.SmartSchool.entity.Professor;
import com.ensa.SmartSchool.service.ProfessorService;
import com.ensa.SmartSchool.validation.Validation;
import com.ensa.SmartSchool.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

@Component
public class ProfessorAdminController implements Initializable {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired @Lazy
    private StageManager stageManager;
	@FXML
	private TableView<Professor> professor_admin_table;
	@FXML
	private TableColumn<Professor, Integer> professor_id_col;
	@FXML
	private TableColumn<Professor, String> first_name_col;
	@FXML
	private TableColumn<Professor, String> last_name_col;
	@FXML
	private TableColumn<Professor, String> level_name_col;
	@FXML
	private TableColumn<Professor, String> password_col;
	@FXML
	private TextField professor_id;
	@FXML
	private TextField first_name;
	@FXML
	private TextField last_name;
	@FXML
	private TextField level_name;
	@FXML
	private TextField password;
	@FXML
	private Button insert;
	@FXML
	private Button update;
	@FXML
	private Button delete;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showProfessors();

	}

	private void showProfessors() {
		ObservableList<Professor> professorList = FXCollections.observableList(professorService.getProfessors());
		professor_id_col.setCellValueFactory(new PropertyValueFactory<Professor, Integer>("professorId"));
		first_name_col.setCellValueFactory(new PropertyValueFactory<Professor, String>("professorFirstName"));
		last_name_col.setCellValueFactory(new PropertyValueFactory<Professor, String>("professorLastName"));
		level_name_col.setCellValueFactory(new PropertyValueFactory<Professor, String>("levelName"));
		password_col.setCellValueFactory(new PropertyValueFactory<Professor, String>("password"));
		professor_admin_table.setItems(professorList);

	}

	@FXML
	private void insertProfessor() {
		Professor professor = getProfessorFromInput();

		if (Validation.isValidProfessor(professor)) {
			System.out.println("erreur");
			professorService.create(professor);
			showProfessors();
		}
	}

	@FXML
	public void updateProfessor() {
		Professor professor = getProfessorFromInputForUpdate();

		if (Validation.isValidProfessor(professor)) {
			professorService.updateProfessor(professor);
			
			showProfessors();
		}
	}

	@FXML
	public void deleteProfessor() {

		Professor professor = getProfessorFromInputForUpdate();
		professorService.delete(professor);
		showProfessors();
		clearFields();
	}
	private Professor getProfessorFromInput() {

		
		Professor professor = new Professor(first_name.getText(), last_name.getText(),
				0, level_name.getText(), password.getText());
		return professor;

	}

	private Professor getProfessorFromInputForUpdate() {

		
		Professor professor = new Professor(first_name.getText(), last_name.getText(),
				Integer.parseInt(professor_id.getText()), level_name.getText(), password.getText());
		return professor;

	}

	@FXML
	private void populateFields() {
		Professor professor = professor_admin_table.getSelectionModel().getSelectedItem();
		professor_id.setText(Integer.toString(professor.getProfessorId()));
		first_name.setText(professor.getProfessorFirstName());
		last_name.setText(professor.getProfessorLastName());
		level_name.setText(professor.getLevelName());
		password.setText(professor.getPassword());
	}
	
	private void clearFields() {
		professor_id.setText("");
		first_name.setText("");
		last_name.setText("");
		level_name.setText("");
		password.setText("");
	}

	@FXML
    void goHome(MouseEvent event) {
    	this.stageManager.switchScene(FxmlView.LOGINAS);
    }

    @FXML
    void goNotices(MouseEvent event) {
    	//this.stageManager.switchScene(FxmlView.NOTICESTUDENT);
    }
    

    @FXML
    void goCourses(MouseEvent event) {
    	//this.stageManager.switchScene(FxmlView.COURSESSTUDENT1);
    }
    
    
    
    @FXML
    void goForum(MouseEvent event) {
    	//this.stageManager.switchScene(FxmlView.FORUM);
    }

}
