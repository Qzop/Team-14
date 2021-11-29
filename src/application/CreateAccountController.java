package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Account.Messaging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import mySQL.Doctor;
import mySQL.MySQLConnection;
import mySQL.Patient;

public class CreateAccountController 
{
	@FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private TextField xBirthday;

    @FXML
    private TextField xEmail;

    @FXML
    private TextField xPhoneNumber;
    
    @FXML
    private TextField password;

    @FXML
    private TextField username;
    
    @FXML
    private Label errormsg;

    @FXML
    void AccountSuccess(ActionEvent event) 
    {
    	fName.setStyle("");
    	lName.setStyle("");
    	username.setStyle("");
    	password.setStyle("");
    	xBirthday.setStyle("");
    	xEmail.setStyle("");
    	xPhoneNumber.setStyle("");
    	errormsg.setText("");
    	SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-za-z]{2,4}");
		Matcher mat;
		
		MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	
    	if(!fName.getText().isEmpty() && !lName.getText().isEmpty() && !xBirthday.getText().isEmpty() && !xEmail.getText().isEmpty() && !xPhoneNumber.getText().isEmpty() && !username.getText().isEmpty() && !password.getText().isEmpty())
    	{
    		String dob, email;
    		long phonenum = 0;
    		
    		boolean checkEmail = false, checkDate = false, checkPhone = false, checkUser = false;
    		
    		dob = xBirthday.getText();
    		email = xEmail.getText();
    		
    		mat = pattern.matcher(email);
    		
    		try
    		{
    			phonenum = Long.parseLong(xPhoneNumber.getText());
    			checkPhone = true;
    		}
    		catch(NumberFormatException e)
			{
				checkPhone = false;
				
				errormsg.setStyle("-fx-text-fill: red");
				errormsg.setText("Your phone number format is incorrect.\nCorrect format: 4801234567");
			}
    		
    		try
    		{
    			dateformat.parse(dob);
    			checkDate = true;
    		}
    		catch(ParseException e)
    		{
    			checkDate = false;
    			
    			errormsg.setStyle("-fx-text-fill: red");
				errormsg.setText("Your date format is incorrect.\nCorrect format: mm/dd/yyyy");
    		}
    		
    		if(mat.matches())
    		{
    			checkEmail = true;
    		}
    		else
    		{
    			errormsg.setStyle("-fx-text-fill: red");
				errormsg.setText("Your email format is incorrect.\nCorrect format: example@domain.com");
    		}
    		
    		if(xPhoneNumber.getLength() != 10)
    		{
    			errormsg.setStyle("-fx-text-fill: red");
				errormsg.setText("Your phone number does not exist.\nCorrect format: 4801234567");
				checkPhone = false;
    		}
    		else
    		{
    			checkPhone = true;
    		}
    		
    		if(pat.checkUsernames(username.getText()))
    		{
    			username.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
    			
    			errormsg.setStyle("-fx-text-fill: red");
				errormsg.setText("The username you entered already exists!");
    		}
    		else
    		{
    			checkUser = true;
    		}
    		
    		if(checkPhone && checkEmail && checkDate && checkUser)
    		{
    			errormsg.setStyle("-fx-text-fill: green");
				errormsg.setText("A confirmation email has been sent.\nFollow the instructions in the email.");
				
				try
				{
					Messaging.sendMail(email);
					
					pat.createPatient(fName.getText(), lName.getText(), username.getText() , password.getText(), xBirthday.getText(), xEmail.getText(), xPhoneNumber.getText());
				}
				catch(Exception e)
				{
					errormsg.setStyle("-fx-text-fill: red");
					errormsg.setText("Your email does not exist.\nCorrect format: example@domain.com");
				}
    		}
    	}
    	else
    	{
    		errormsg.setStyle("-fx-text-fill: red");
			errormsg.setText("You must fill out the required fields!");
			
			if(fName.getText().isEmpty())
			{
				fName.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				fName.setStyle("");
			}
			
			if(lName.getText().isEmpty())
			{
				lName.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				lName.setStyle("");
			}
			
			if(username.getText().isEmpty())
			{
				username.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				username.setStyle("");
			}
			
			if(password.getText().isEmpty())
			{
				password.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				password.setStyle("");
			}
			
			if(xBirthday.getText().isEmpty())
			{
				xBirthday.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				xBirthday.setStyle("");
			}
			
			if(xEmail.getText().isEmpty())
			{
				xEmail.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				xEmail.setStyle("");
			}
			
			if(xPhoneNumber.getText().isEmpty())
			{
				xPhoneNumber.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
			}
			else
			{
				xPhoneNumber.setStyle("");
			}
    	}
    }
}
