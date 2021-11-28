package mySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
					+ "USERNAME VARCHAR(100), PASSWORD VARCHAR(100), PATIENTID INT(100), DOCTORID INT(100), NURSEID INT(100))");
			ps.executeUpdate();
			
			createPatient("John Doe", 1000);
			addPatient("12/25/1989", "Male", "JohnDoe89", "johnDOE!", 1000, 1000, 1000);
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
	
	public void addPatient(String DOB, String Gender, String Username, String Password, int PatientID, int DoctorID, int NurseID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET DOB=?, GENDER=?, USERNAME=?, PASSWORD=?, DOCTORID=?, NURSEID=? WHERE PATIENTID=?");
			
			ps.setString(1, DOB);
			ps.setString(2, Gender);
			ps.setString(3, Username);
			ps.setString(4, Password);
			ps.setInt(5, DoctorID);
			ps.setInt(6, NurseID);
			ps.setInt(7, PatientID);
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with addPatient!");
			e.printStackTrace();
		}
	}
	
	public String getPatient(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME, DOB, GENDER, DOCTORID, NURSEID FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String name = "", dob = "", gender = "";
			int doctorid, nurseid;
			
			if(rs.next())
			{
				name = rs.getString("NAME");
				dob = rs.getString("DOB");
				gender = rs.getString("GENDER");
				doctorid = rs.getInt("DOCTORID");
				nurseid = rs.getInt("NURSEID");	
				
				return "Name: " + name + " | DOB: " + dob + " | Gender: " + gender + " | DoctorID: " + doctorid + " | NurseID: " + nurseid; 
			}
			
			
			return "";
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPatient");
			e.printStackTrace();
		}
				
		return "";
	}
	
	public ArrayList<Integer> getPatientIDs(int doctorid)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT PATIENTID FROM Patient WHERE DOCTORID=?");
			ps.setInt(1, doctorid);

			ResultSet rs = ps.executeQuery();
			ArrayList<Integer> patids = new ArrayList<Integer>();
			
			while(rs.next())
			{
				patids.add(rs.getInt("PATIENTID"));
			}
			
			if(patids != null)
			{
				return patids;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPatientIDs");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int getDoctor(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT DOCTORID FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			int doctorid;
			
			if(rs.next())
			{
				doctorid = rs.getInt("DOCTORID");
				
				return doctorid; 
			}
			
			
			return 0;
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPatient");
			e.printStackTrace();
		}
		
		return 0;
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
