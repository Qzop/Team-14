package Account;

import java.util.*;

public class CreateAccount extends Login
{
	private String birthday;
	private String firstname;
	private String lastname;
	private String email;
	private String uniqueID;
	private long phonenumber;
	
	public void createAccount(String birthday, String firstname, String lastname, String email, long phonenumber)
	{
		this.birthday = birthday;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.uniqueID = createID(firstname, lastname, birthday);
	}
		public String createID(String firstname, String lastname, String bday)
	{
		this.firstname = firstname;
		this.lastname  = lastname;
		this.birthday  = bday; 
			
		String temp = firstname.substring(0, 3);
		String templast = lastname.substring(0);
		String tempbday = bday.substring(bday.length() - 2); //get last two numbers for the year
		
		String uniqueID = temp.concat(templast).concat(tempbday);
		return uniqueID;
	}
}
