package GUI;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Account.CreateAccount;
import Account.Login;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application 
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{	
		loginGUI(primaryStage);
	}
	
	@SuppressWarnings("static-access")
	public void loginGUI(Stage primaryStage)
	{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setVgap(5);
		grid.setHgap(2);
		
		HBox hTitle = new HBox(20);
		Text title = new Text("Login");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL.BOLD, 20));
		title.setTextAlignment(TextAlignment.CENTER);
		hTitle.getChildren().add(title);
		hTitle.setAlignment(Pos.CENTER);
		grid.add(hTitle, 0,0);
		
		TextField username = new TextField();
		username.setPromptText("Username");
		grid.setConstraints(username, 0, 2);
		grid.getChildren().add(username);
		
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		grid.setConstraints(password, 0, 3);
		grid.getChildren().add(password);

		HBox hbtn = new HBox(75);
		Button loginbtn = new Button("Login");
		Button createacc = new Button("Create an Account");
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().addAll(loginbtn, createacc);

		Text errorLogin = new Text();
		
		grid.add(hbtn, 0, 5);
		grid.add(errorLogin, 0, 10);
		
		loginbtn.setText("Login");
		loginbtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						Login log = new Login();
						
						if(!username.getText().isEmpty() && !password.getText().isEmpty())
						{
							System.out.println(username.getText() + password.getText());
							
							if(!log.verifyLogin(username.getText(), password.getText()))
							{
								errorLogin.setFill(Color.RED);
								errorLogin.setText("Username or password is incorrect.");
								System.out.println("Failed");
							}
							else
							{
								
								System.out.println("Passed");
							}
						}
						else
						{
							errorLogin.setFill(Color.RED);
							errorLogin.setText("You must fill in the username and password!");
						}
					}
				});
		
		createacc.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						createAccountGUI(primaryStage);
					}
				});
		
		StackPane login = new StackPane();
		login.getChildren().add(grid);
		primaryStage.setScene(new Scene(login, 400, 400));
		primaryStage.show();
	}
	
	@SuppressWarnings("static-access")
	public void createAccountGUI(Stage createStage)
	{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setVgap(5);
		grid.setHgap(2);
		
		HBox hTitle = new HBox(20);
		Text title = new Text("Create Account");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL.BOLD, 20));
		title.setTextAlignment(TextAlignment.CENTER);
		hTitle.getChildren().add(title);
		hTitle.setAlignment(Pos.CENTER);
		grid.add(hTitle, 0,0);
		
		HBox firstlastname = new HBox(20);
		TextField firstname = new TextField();
		firstname.setPromptText("First name");
		TextField lastname = new TextField();
		lastname.setPromptText("Last name");
		firstlastname.getChildren().addAll(firstname, lastname);
		grid.add(firstlastname, 0, 1);
		
		HBox birthEmail = new HBox(20);
		TextField birthDate = new TextField();
		birthDate.setPromptText("BirthDate (MM/dd/yyyy)");
		TextField email = new TextField();
		email.setPromptText("Email Address");
		birthEmail.getChildren().addAll(birthDate, email);
		grid.add(birthEmail, 0, 2);
		
		HBox phone = new HBox(20);
		TextField phoneNum = new TextField();
		phoneNum.setPromptText("Phone Number (include area code)");
		phoneNum.setAlignment(Pos.CENTER);
		phone.getChildren().add(phoneNum);
		grid.add(phoneNum, 0, 3);
		
		HBox hbtn = new HBox(20);
		hbtn.setAlignment(Pos.CENTER);
		Button bckloginbtn = new Button("Back to Login");
		Button submit = new Button("Submit");
		hbtn.getChildren().addAll(bckloginbtn, submit);
		grid.add(hbtn, 0, 4);
		
		HBox hError = new HBox(20);
		Text errormsg = new Text();
		hError.getChildren().add(errormsg);
		hError.setAlignment(Pos.CENTER);
		grid.add(hError, 0, 5);
		
		bckloginbtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						loginGUI(createStage);
					}
				});
		
		submit.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
						Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-za-z]{2,4}");
						Matcher mat;
						
						if(firstname.getText().isEmpty())
						{
							firstname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
							
							errormsg.setFill(Color.RED);
							errormsg.setText("You must fill out the required information above!");
						}
						else
						{
							firstname.setStyle("");
						}
						
						if(lastname.getText().isEmpty())
						{
							lastname.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
							
							errormsg.setFill(Color.RED);
							errormsg.setText("You must fill out the required information above!");
						}
						else
						{
							lastname.setStyle("");
						}
						
						if(birthDate.getText().isEmpty())
						{
							birthDate.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
							
							errormsg.setFill(Color.RED);
							errormsg.setText("You must fill out the required information above!");
						}
						else
						{
							birthDate.setStyle("");
						}
						
						if(email.getText().isEmpty())
						{
							email.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
							
							errormsg.setFill(Color.RED);
							errormsg.setText("You must fill out the required information above!");
						}
						else
						{
							email.setStyle("");
						}
						
						if(phoneNum.getText().isEmpty())
						{
							phoneNum.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
							
							errormsg.setFill(Color.RED);
							errormsg.setText("You must fill out the required information above!");
						}
						else
						{
							phoneNum.setStyle("");
						}
						
						if(!firstname.getText().isEmpty() && !lastname.getText().isEmpty() && !birthDate.getText().isEmpty() && !email.getText().isEmpty() && !phoneNum.getText().isEmpty())
						{
							String dateofbirth, Email; 
							long phonenum = 0;
							
							boolean checkEmail = false, checkDate = false, checkPhone = false;
							
							dateofbirth = birthDate.getText();
							Email = email.getText();
							
							mat = pattern.matcher(Email);
							
							try
							{
								phonenum = Long.parseLong(phoneNum.getText());
							}
							catch(NumberFormatException e)
							{
								checkPhone = false;
								phoneNum.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
								errormsg.setFill(Color.RED);
								errormsg.setText("Your phone number is not in correct format.\nCorrect format: 4801234567");
							}
							
							try
							{
								dateformat.parse(dateofbirth);
								errormsg.setText("");
								checkDate = true;
							}
							catch(ParseException e)
							{
								checkDate = false;
								birthDate.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
								errormsg.setFill(Color.RED);
								errormsg.setText("You date format is incorrect.\nCorrect format: MM/dd/yyyy");
							}
							
							if(mat.matches())
							{
								errormsg.setText("");
								checkEmail = true;
							}
							else
							{
								email.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
								errormsg.setFill(Color.RED);
								errormsg.setText("You email format is incorrect.\nCorrect format: example@domain.com");
							}
							
							if(phoneNum.getLength() != 10)
							{
								phoneNum.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
								errormsg.setFill(Color.RED);
								errormsg.setText("Your phone number does not exist.\nCorrect format: 4801234567");
							}
							else
							{
								checkPhone = true;
							}
							
							if(checkPhone && checkEmail && checkDate)
							{
								CreateAccount create = new CreateAccount();
								
								create.createAccount(dateofbirth,firstname.getText(), lastname.getText(), Email, phonenum);
								
								errormsg.setFill(Color.GREEN);
								errormsg.setText("A confirmation email has been sent.\nFollow the instructions in the email.");
								
								//this is the code added for sending the email after setting up account
								try {
                           					 	Messaging.sendMail(Email);
                       					        } catch (Exception e) {
                          						  e.printStackTrace();
                        					}
							}
						}
						
						
					}
				});

		
		StackPane createAcc = new StackPane();
		createAcc.getChildren().add(grid);
		createStage.setScene(new Scene(createAcc, 400, 400));
	}
}
