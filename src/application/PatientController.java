package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mySQL.Doctor;
import mySQL.MySQLConnection;
import mySQL.Patient;

public class PatientController  implements Initializable{
	
	private static String user, pass;
	
	//Tab buttons
	@FXML
	private Button btn_Overview;
	@FXML
	private Button btn_EditInfo;
	@FXML
	private Button btn_Summary;
	
	//Patient Overview Labels
	@FXML
	private Label lbl_Pharmacy;
	@FXML
	private Label lbl_PatientID;
	@FXML
	private Label lbl_Health;
	@FXML
	private Label lbl_Prescriptions;
	@FXML
	private Label lbl_nxtAppointment;
	
	//Patient Overview Textfields
	@FXML
	private TextField txt_Pharmacy;
	
	
	@FXML
	private TextField txt_PatientID;
	@FXML
	private TextField txt_Health;
	@FXML
	private TextField txt_Prescriptions;
	@FXML
	private TextField txt_nxtApp;
	
	// Edit Info Tab labels
	@FXML
	private Label lbl_fname;
	@FXML
	private Label lbl_lname;
	@FXML
	private Label lbl_username;
	@FXML
	private Label lbl_email;
	@FXML
	private Label lbl_phone;
	@FXML
	private Label lbl_bday;
	
	// Edit Info Tab Textfield
	@FXML
	private TextField txt_fname;
	@FXML
	private TextField txt_lname;
	@FXML
	private TextField txt_username;
	@FXML
	private TextField txt_birthday;
	@FXML
	private TextField txt_email;
	@FXML
	private TextField txt_phone;
	
	// Edit Info Button
	@FXML
	private Button btn_SaveEdit;

	@FXML
	public void onClick_SaveEdit(ActionEvent e)
	{
		//code for searching for appointment summary
	}
	//
}
