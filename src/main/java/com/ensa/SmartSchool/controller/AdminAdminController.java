package com.ensa.SmartSchool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ensa.SmartSchool.entity.Admin;

import com.ensa.SmartSchool.service.AdminService;

@Component
public class AdminAdminController implements Initializable{
    @Autowired
    private AdminService adminService;

    @FXML
    private TextField AdminId;

    @FXML
    private TextField Username;

    @FXML
    private TextField Password;

     @FXML
    private TableView<Admin> adminList;

    @FXML
    private TableColumn<Admin,Integer> colAdminId;

    @FXML
    private TableColumn<Admin,String> colUsername;

    @FXML
    private TableColumn<Admin,String> colPassword;

       @FXML
    private Button insertBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private Button deleteBtn;

	    @Override
	public void initialize(URL location, ResourceBundle resources) {
		showAdmins();
		
	}
	public void showAdmins() {
		List<Admin> admins = adminService.getAdmins();
		ObservableList<Admin> list = FXCollections.observableList(admins);
		colAdminId.setCellValueFactory(new PropertyValueFactory<Admin, Integer>("adminId"));
		colUsername.setCellValueFactory(new PropertyValueFactory<Admin, String>("username"));
		colPassword.setCellValueFactory(new PropertyValueFactory<Admin, String>("password"));
		adminList.setItems(list);
	}
	
    

    @FXML
    void deleteAdmin(ActionEvent event) {
	Admin admin = new Admin(Username.getText(), Password.getText(),Integer.parseInt(AdminId.getText()));
		adminService.delete(admin);
		showAdmins();
		this.AdminId.setText("");
		this.Password.setText("");
		this.Username.setText("");
    }

    @FXML
    void insertAdmin(ActionEvent event) {
		Admin admin = new Admin(Username.getText(),Password.getText(),0 );
		if(validAdmin(admin)) {
			adminService.create(admin);
			showAdmins();
			this.AdminId.setText("");
			this.Password.setText("");
			this.Username.setText("");
		}
    }

    @FXML
    void populateFields(MouseEvent event) {
	Admin selectedAdmin = adminList.getSelectionModel().getSelectedItem();
		AdminId.setText(Integer.toString(selectedAdmin.getAdminId()));
		Username.setText(selectedAdmin.getUsername());
		Password.setText(selectedAdmin.getPassword());
    }

    @FXML
    void updateAdmin(ActionEvent event) {
	Admin admin = new Admin(Username.getText(), Password.getText(), Integer.parseInt(AdminId.getText()) );
		adminService.updateAdmin(admin);
		showAdmins();
		this.AdminId.setText("");
		this.Password.setText("");
		this.Username.setText("");
		
    }
	private boolean validAdmin(Admin admin) {
		
		return (validate("username",admin.getUsername(),"^[a-zA-Z]+$") && 
				validate("id", Integer.toString(admin.getAdminId()), "^[0-9]+$")  );
	}
	
	private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		} 
	}
	
	private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
       
        	if(empty) {
        		alert.setContentText("Please Enter "+ field);
        	}
        	else {
        		alert.setContentText("Please Enter Valid "+ field);
        	}
        
        alert.showAndWait();
	}
	
	 @FXML
	    void goHomeAdmin(MouseEvent event) {

	    }

	    @FXML
	    void goMainNotice(MouseEvent event) {

	    }

	   

	    @FXML
	    void logout(ActionEvent event) {

	    }

	    @FXML
	    void logout1(MouseEvent event) {

	    }

	   

	

}

