package Account;

public class Login 
{
	private String password, username;
	private int userType;
	
	public void login(String username, String password)
	{
		this.password = username;
		this.username = password;
		
		System.out.println(this.username + " " + this.password);
	}
	
	public boolean verifyLogin(String username, String password)
	{
		login(username, password);
		
		return false;
	}
}
