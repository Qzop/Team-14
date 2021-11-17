package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Doctor 
{
private Connection conn;
	
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
		}
		catch(SQLException e)
		{
			System.out.println("Error with createDoctorTable!");
			e.printStackTrace();
		}
	}
	
	public void addPatient(String name, String DOB, String Gender, String Username, String Password, int DoctorID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Doctor SET NAME =? SET DOB=? SET GENDER=? SET USERNAME=? SET PASSWORD=? DOCTORID=?");
			
			ps.setString(1, name);
			ps.setString(2, DOB);
			ps.setString(3, Gender);
			ps.setString(4, Username);
			ps.setString(5, Password);
			ps.setInt(6, DoctorID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with createDoctor!");
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
	public boolean exists(String name, int DoctorID)
	{
		return false;
	}
}
