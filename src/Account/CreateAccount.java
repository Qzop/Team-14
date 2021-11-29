package Account;

import java.util.*;

public class CreateAccount extends Login
{
	private String birthday;
	private String firstname;
	private String lastname;
	private String email;
	private long phonenumber;
	
	public void createAccount(String birthday, String firstname, String lastname, String email, long phonenumber)
	{
		this.birthday = birthday;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
	}
	
}
