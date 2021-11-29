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
// Create Account
	@FXML
	private Label lblCreateAccount;
	@FXML
	private TextField fName;
	@FXML
	private TextField lName;
	@FXML
	private TextField xBirthday;
	@FXML
	private TextField xPhoneNumber;
	@FXML
	private TextField xEmail;
	
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
	public void CreateAccount(ActionEvent event) throws Exception{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CreateAccount.fxml"));
		Scene scene = new Scene(root,350,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void AccountSuccess(ActionEvent event) throws Exception{
		if (!lName.getText().isEmpty() && !fName.getText().isEmpty() && !xBirthday.getText().isEmpty()
				&& !xEmail.getText().isEmpty() && !xPhoneNumber.getText().isEmpty())
		{
			lblCreateAccount.setText("Success, an email has been sent with instructions!");
		}
		else
		{
			lblCreateAccount.setText("Create Account Failed!");
		}
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
		
		Nurse nurse = new Nurse(conn.getConnection());
		
		if(nurse.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			/*lblPatientStat.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
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
		
		Patient pat = new Patient(conn.getConnection());
		
		if(pat.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			/*lblPatientStat.setText("Login Success");
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainFXML.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
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
		
		Doctor doc = new Doctor(conn.getConnection());
		
		
		if(doc.checkUserPass(username, pass))
		{
			System.out.println("Login Successful!");
			
			/*lblDoctorStat.setText("Login Success"); 
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/DoctorAccount.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
		}
		else 
		{
			//lblDoctorStat.setText("Login Failed.");
			
			System.out.println("Login Failed.");
		}
	}
}
