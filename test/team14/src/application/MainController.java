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
import mySQL.Doctor;
import mySQL.MySQLConnection;
import mySQL.Nurse;
import mySQL.Patient;
public class MainController {
	
	private static MySQLConnection conn = new MySQLConnection();
	private Doctor doc = new Doctor(conn.getConnection());
	private Patient pat = new Patient(conn.getConnection());
	private Nurse nurse = new Nurse(conn.getConnection());
	
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/DoctorLogin.fxml"));
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
	public void Login(ActionEvent event) throws Exception 
	{
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
	
	public void nurseLoginButton(ActionEvent event) throws Exception 
	{
		String username = nurseUsername.getText();
		String pass = nursePassword.getText();
		
		if(nurse.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			Stage primaryStage = new Stage();
			Parent r = FXMLLoader.load(getClass().getResource("/application/NurseView.fxml"));
			Scene s = new Scene(r,400,400);
			s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(s);
			primaryStage.setHeight(520);
			primaryStage.setWidth(500);
			primaryStage.show();
		}
		else 
		{
			System.out.println("Login Failed.");
			//lblPatientStat.setText("Login Failed.");
		} 
	}
	
	public void patientLoginButton(ActionEvent event) throws Exception 
	{
		String username = patientID.getText();
		String pass = patientPassword.getText();
		
		if(pat.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/PatientView.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		else 
		{
			System.out.println("Login Failed.");
			//lblPatientStat.setText("Login Failed.");
		} 
	}
	
	public void doctorLoginButton(ActionEvent event) throws Exception 
	{
		String username = doctorID.getText();
		String pass = doctorPassword.getText();
		DoctorController cont = new DoctorController();
		
		if(doc.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			Stage primaryStage = new Stage();
			Parent r = FXMLLoader.load(getClass().getResource("/application/DoctorView.fxml"));
			Scene s = new Scene(r,400,400);
			s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(s);
			primaryStage.setHeight(520);
			primaryStage.setWidth(620);
			cont.setUserAndPass(username, pass);
			primaryStage.show();
		}
		else 
		{
			//lblDoctorStat.setText("Login Failed.");
			
			System.out.println("Login Failed.");
		}
	}
}
