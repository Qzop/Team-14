package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient 
{	
	private Connection conn;
	
	public Patient(Connection connection)
	{
		conn = connection;
	}
	
	public void createPatientTable()
	{
		try
		{	
			PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Patient (NAME VARCHAR(100), DOB VARCHAR(100), GENDER VARCHAR(100), "
					+ "USERNAME VARCHAR(100), PASSWORD VARCHAR(100), PATIENTID INT(100))");
			ps.executeUpdate();
			
			createPatient("John Doe", 1000);
			addPatient("12/25/1989", "Male", "JohnDoe89", "johnDOE!", 1000);
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatientTable!");
			e.printStackTrace();
		}
	}
	
	public void createPatient(String name, int DoctorID)
	{
		try
		{
			if(!exists(DoctorID))
			{
				PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Patient (NAME, PATIENTID) VALUES (?, ?)");
				ps.setString(1, name);
				ps.setInt(2, DoctorID);
				ps.executeUpdate();
				
				return;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatient");
			e.printStackTrace();
		}
	}
	
	public void addPatient(String DOB, String Gender, String Username, String Password, int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET DOB=?, GENDER=?, USERNAME=?, PASSWORD=? WHERE PATIENTID=?");
			
			ps.setString(1, DOB);
			ps.setString(2, Gender);
			ps.setString(3, Username);
			ps.setString(4, Password);
			ps.setInt(5, PatientID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with addPatient!");
			e.printStackTrace();
		}
	}
	
	public void getPatient(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPatient");
			e.printStackTrace();
		}
				
	}
	
	public void removePatient(int PatientID)
	{
		try 
		{
			PreparedStatement ps = conn.prepareStatement("DELETE FROM Patient WHERE PATIENTID=?");
		    ps.setInt(1, PatientID);
		    ps.executeUpdate();
		} 
		catch(SQLException e) 	
		{
			System.out.println("Error with removePatient!");
			e.printStackTrace();
		} 
	}
	
	// Will be used later on to check for duplicates
	public boolean exists(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
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
	
	public boolean checkUserPass(String username, String password)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient WHERE USERNAME=? AND PASSWORD=?");
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
			System.out.print("Error with CheckUserPass for Patient");
			e.printStackTrace();
		}
		
		return false;
	}
}
