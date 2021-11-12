package application;

import java.io.File;
import java.util.Scanner;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.*;
public class MainController {
	@FXML
	private Label txtMenu; 
	@FXML
	private	Label lblStatus;
	
	@FXML
	private	TextField txtUsername;
	
	@FXML
	private	TextField txtPassword;
	
// Nurse Login
	@FXML
	private Label lblNurse;
	
	@FXML
	private	TextField nurseUsername;
	
	@FXML
	private	TextField nursePassword;
//patient login
	@FXML	
	private Label patientTitle;
	@FXML
	private Label lblPatientStat;
	@FXML 	
	private TextField patientID;
	@FXML
	private	TextField patientPassword;
//doctor login
	@FXML
	private Label doctorTitle;
	@FXML
	private Label lblDoctorStat;
	@FXML
	private TextField doctorID;
	@FXML
	private TextField doctorPassword;
	
	
	
	public void  DoctorLogin(ActionEvent event) throws Exception {
		//write code... for doctor
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginFXML.fxml"));
		Scene scene = new Scene(root,350,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void PatientLogin(ActionEvent event) throws Exception{
		 //write code... for patient
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/PatientLoginFXML.fxml"));
		Scene scene = new Scene(root,350,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void NurseLogin(ActionEvent event) throws Exception{
		//write code... for nurses
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
		Scene scene = new Scene(root,350,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//main login button
	public void Login(ActionEvent event) throws Exception {
		if(txtUsername.getText().equals("user") && txtPassword.getText().equals("password"))
		{
			lblStatus.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else {
			lblStatus.setText("Login Failed.");
		}
	}
	public void patientLoginButton(ActionEvent event) throws Exception {
		String filepath = "patientFile.txt";
		boolean login = false; 
	
		
		/*
		if(patientID.getText().equals("patient") && patientPassword.getText().equals("sick"))
		{
			lblPatientStat.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else {
			lblPatientStat.setText("Login Failed.");
		} */
	}
	public void doctorLoginButton(ActionEvent event) throws Exception {
		if(patientID.getText().equals("doctor") && patientPassword.getText().equals("doc"))
		{
			lblPatientStat.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/DoctorAccount.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else {
			lblPatientStat.setText("Login Failed.");
		}
	}
	//Verify login function from youtube
	private static Scanner x;
	public static void verifyLogin(String userName, String password, String filepath)
	{
		boolean found = false; //initialize
		String tempUsername = "";
		String tempPassword = "";
		
		try {
			x = new Scanner(new File(filepath)); //scan file
			x.useDelimiter("[,\n]");			//each field separated by a comma. ex: read and then stop at the "," in the textfile
			
			while(x.hasNext() && !found) //read file 
			{
				tempUsername = x.next();
				tempPassword = x.next();
				
				if(tempUsername.trim().equals(userName.trim()) && tempPassword.trim().equals(password.trim()))
				{
					found = true; //username and password found in textfile
				}
			}
			x.close();
		}
		catch(Exception e)
		{
			System.out.println("Error");
		}
	}
}
