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
	
	private static String docUser;
	private static String docPass;
	private static String nurseUser;
	private static String nursePass;
	private static String patientPass;
	private static String patientUser;
	
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
	
	@FXML
    private Label errorNurse;
//patient login
	@FXML	
	private Label patientTitle;
	@FXML 	
	private TextField patientID;
	@FXML
	private	TextField patientPassword;
	@FXML
    private Label errorPatient;
//doctor login
	@FXML
	private Label doctorTitle;
	@FXML
	private TextField doctorID;
	@FXML
	private TextField doctorPassword;
	@FXML
    private Label errorDoctor;
	
	
	
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
		Parent root = FXMLLoader.load(getClass().getResource("/application/NurseLogin.fxml"));
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
			Parent root = FXMLLoader.load(getClass().getResource("/application/NurseLogin.fxml"));
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
		errorNurse.setText("");
		patientUser = nurseUsername.getText();
		patientPass = nursePassword.getText();
		
		if(!nurseUsername.getText().isEmpty() && !nursePassword.getText().isEmpty())
		{
			if(nurse.checkUserPass(patientUser, patientPass))
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
				errorNurse.setStyle("-fx-text-fill: red");
				errorNurse.setText("Username and password do not match.");
			} 
		}
		else
		{
			if(nurseUsername.getText().isEmpty())
			{
				nurseUsername.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				nurseUsername.setStyle("");
			}
			
			if(nursePassword.getText().isEmpty())
			{
				nursePassword.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				nursePassword.setStyle("");
			}
			
			errorNurse.setStyle("-fx-text-fill: red");
			errorNurse.setText("You must fill out the required fields!");
		}
	}
	
	public void createAccountButton(ActionEvent event) throws Exception
	{
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/CreateAccount.fxml"));
		Scene scene = new Scene(root,400,300);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void patientLoginButton(ActionEvent event) throws Exception 
	{
		errorPatient.setText("");
		patientUser = patientID.getText();
		patientPass = patientPassword.getText();
		
		if(!patientID.getText().isEmpty() && !patientPassword.getText().isEmpty())
		{
			if(pat.checkUserPass(patientUser, patientPass))
			{
				System.out.println("Login Successful!");
				
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/PatientView.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else 
			{
				errorPatient.setStyle("-fx-text-fill: red");
				errorPatient.setText("Username and password do not match.");
			} 
		}
		else
		{
			if(patientID.getText().isEmpty())
			{
				patientID.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				patientID.setStyle("");
			}
			
			if(patientPassword.getText().isEmpty())
			{
				patientPassword.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				patientPassword.setStyle("");
			}
			
			errorPatient.setStyle("-fx-text-fill: red");
			errorPatient.setText("You must fill out the required fields!");
		}
	}
	
	public void doctorLoginButton(ActionEvent event) throws Exception 
	{
		errorDoctor.setText("");
		docUser = doctorID.getText();
		docPass = doctorPassword.getText();
		
		if(!doctorID.getText().isEmpty() && !doctorPassword.getText().isEmpty())
		{
			if(doc.checkUserPass(docUser, docPass))
			{
				System.out.println("Login Successful!");
				
				Stage primaryStage = new Stage();
				Parent r = FXMLLoader.load(getClass().getResource("/application/DoctorView.fxml"));
				Scene s = new Scene(r,400,400);
				s.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(s);
				primaryStage.setHeight(520);
				primaryStage.setWidth(620);
				primaryStage.show();
			}
			else 
			{
				if(doctorID.getText().isEmpty())
				{
					doctorID.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				}
				else
				{
					doctorID.setStyle("");
				}
				
				if(doctorPassword.getText().isEmpty())
				{
					doctorPassword.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
				}
				else
				{
					doctorPassword.setStyle("");
				}
				
				errorDoctor.setStyle("-fx-text-fill: red");
				errorDoctor.setText("Username and password do not match.");
			}
		}
		else
		{
			errorDoctor.setStyle("-fx-text-fill: red");
			errorDoctor.setText("You must fill out the required fields!");
		}
	}
	
	public String getDocUser()
	{
		return docUser;
	}
	
	public String getDocPass()
	{
		return docPass;
	}
	
	public String getNurseUser()
	{
		return nurseUser;
	}
	
	public String getNursePass()
	{
		return nursePass;
	}
	
	public String getPatUser()
	{
		return patientUser;
	}
	
	public String getPatPass()
	{
		return patientPass;
	}
}
