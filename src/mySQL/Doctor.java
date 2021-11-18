package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor 
{
	private static Connection conn;
	
	public Doctor(Connection connection)
	{
		conn = connection;
	}
	
	public void createDoctorTable()
	{
		try
		{	
			PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Doctor (NAME VARCHAR(100), DOB VARCHAR(100), GENDER VARCHAR(100), "
					+ "USERNAME VARCHAR(100), PASSWORD VARCHAR(100), DOCTORID INT(100))");
			ps.executeUpdate();
			
			createDoctor("Joe Smith", 1000);
			addDoctor("05/06/1964", "Male", "JoeSmith64", "joeiscool", 1000);
		}
		catch(SQLException e)
		{
			System.out.println("Error with createDoctorTable!");
			e.printStackTrace();
		}
	}
	
	public void createDoctor(String name, int DoctorID)
	{
		try
		{
			if(!exists(DoctorID))
			{
				PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Doctor (NAME, DOCTORID) VALUES (?, ?)");
				ps.setString(1, name);
				ps.setInt(2, DoctorID);
				ps.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error with createDoctor");
			e.printStackTrace();
		}
	}
	
	public void addDoctor(String DOB, String Gender, String Username, String Password, int DoctorID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Doctor SET DOB=?, GENDER=?, USERNAME=?, PASSWORD=? WHERE DOCTORID=?");
			
			ps.setString(1, DOB);
			ps.setString(2, Gender);
			ps.setString(3, Username);
			ps.setString(4, Password);
			ps.setInt(5, DoctorID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with addDoctor!");
			e.printStackTrace();
		}
	}
	
	public void getDoctor(int DoctorID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME FROM Doctor WHERE DOCTORID=?");
			ps.setInt(1, DoctorID);
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Error with getDoctor");
			e.printStackTrace();
		}
				
	}
	
	public void removeDoctor(int DoctorID)
	{
		try 
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Doctor WHERE DoctorID=?");
		    ps.setInt(1, DoctorID);
		    ps.executeUpdate();
		} 
		catch(SQLException e) 	
		{
			System.out.println("Error with removeDoctor!");
			e.printStackTrace();
		} 
	}
	
	// Will be used later on to check for duplicates
	public boolean exists(int DoctorID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor WHERE DOCTORID=?");
			ps.setInt(1, DoctorID);
			
			ResultSet results = ps.executeQuery();
			
			if(results.next())
			{
				return true;
			}
			
			return false;
		}
		catch(SQLException e)
		{
			System.out.println("Error with Doctor Exists");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean checkUserPass(String username, String password)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Doctor WHERE USERNAME=? AND PASSWORD=?");
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
			System.out.print("Error with CheckUserPass for Doctor");
			e.printStackTrace();
		}
		
		return false;
	}
}
