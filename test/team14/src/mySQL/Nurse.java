package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nurse 
{
private Connection conn;
	
	public Nurse(Connection connection)
	{
		conn = connection;
	}
	
	public void createNurseTable()
	{
		try
		{	
			PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Nurse (NAME VARCHAR(100), DOB VARCHAR(100), GENDER VARCHAR(100), "
					+ "USERNAME VARCHAR(100), PASSWORD VARCHAR(100), NURSEID INT(100))");
			ps.executeUpdate();
			
			createNurse("Kate Patterson", 1000);
			addNurse("07/31/1999", "Female", "KPatt99", "kitkatPatt", 1000);
		}
		catch(SQLException e)
		{
			System.out.println("Error with createNurseTable!");
			e.printStackTrace();
		}
		
		
	}
	
	public void createNurse(String name, int NurseID)
	{
		try
		{
			if(!exists(NurseID))
			{
				PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Nurse (NAME, NURSEID) VALUES (?, ?)");
				ps.setString(1, name);
				ps.setInt(2, NurseID);
				ps.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error with createNurse");
			e.printStackTrace();
		}
	}
	
	public void addNurse(String DOB, String Gender, String Username, String Password, int NurseID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Nurse SET DOB=?, GENDER=?, USERNAME=?, PASSWORD=? WHERE NURSEID=?");
		
			ps.setString(1, DOB);
			ps.setString(2, Gender);
			ps.setString(3, Username);
			ps.setString(4, Password);
			ps.setInt(5, NurseID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with addNurse!");
			e.printStackTrace();
		}
	}
	
	public void getNurse(int NurseID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME FROM Nurse WHERE NURSEID=?");
			ps.setInt(1, NurseID);
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Error with getNurse");
			e.printStackTrace();
		}
				
	}
	
	public void removeNurse(int NurseID)
	{
		try 
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Nurse WHERE NURSEID=?");
		    ps.setInt(1, NurseID);
		    ps.executeUpdate();
		} 
		catch(SQLException e) 	
		{
			System.out.println("Error with removeNurse!");
			e.printStackTrace();
		} 
	}
	
	public boolean checkUserPass(String username, String password)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Nurse WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet results = ps.executeQuery();
			
			if(results.next())
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.print("Error with CheckUserPass for Nurse");
			e.printStackTrace();
		}
		
		return false;
	}
	
	// Will be used later on to check for duplicates
	public boolean exists(int NurseID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Nurse WHERE NURSEID=?");
			ps.setInt(1, NurseID);
			
			ResultSet results = ps.executeQuery();
			
			if(results.next())
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Error with nurse Exists");
			e.printStackTrace();
		}
		
		return false;
	}
}
