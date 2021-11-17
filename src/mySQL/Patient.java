package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatientTable!");
			e.printStackTrace();
		}
	}
	
	public void addPatient(String name, String DOB, String Gender, String Username, String Password, int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET NAME =? SET DOB=? SET GENDER=? SET USERNAME=? SET PASSWORD=? SET PATIENTID=?");
			
			ps.setString(1, name);
			ps.setString(2, DOB);
			ps.setString(3, Gender);
			ps.setString(4, Username);
			ps.setString(5, Password);
			ps.setInt(6, PatientID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatient!");
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
	public boolean exists(String name, int PatientID)
	{
		return false;
	}
}
