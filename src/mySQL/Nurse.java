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
		}
		catch(SQLException e)
		{
			System.out.println("Error with createNurseTable!");
			e.printStackTrace();
		}
		
		createNurse("Ben", 1001);
		createNurse("Test", 1002);
		createNurse("duplicate", 1002);
		addNurse("040701", "Male", "bwpeter5", "bigben1234", 1001);
		addNurse("05/07/01", "Female", "urmom", "urdad", 1002);
	}
	
	public void createNurse(String name, int NurseID)
	{
		try
		{
			if(!exists(NurseID))
			{
				PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Nurse (Name, NurseID) VALUES (?, ?)");
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
