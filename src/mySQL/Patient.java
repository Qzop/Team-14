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
			PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Patient (NAME VARCHAR(100), DOB VARCHAR(100), EMAIL VARCHAR(100),"
					+ " GENDER VARCHAR(100), USERNAME VARCHAR(100), PASSWORD VARCHAR(100), PATIENTID INT(100), DOCTORID INT(100), NURSEID INT(100), "
					+ "APPOINTMENT VARCHAR(100), PHARMACY VARCHAR(100), HEALTH VARCHAR(100), PHONE VARCHAR(100))");
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatientTable!");
			e.printStackTrace();
		}
	}
	
	public void createPatient(String firstname, String lastname, String username, String password, String dob, String email, String phonenum)
	{
		int PatientID = 0;
		
		for(int i = 0; i < firstname.length(); i++)
		{
			PatientID += (int) firstname.charAt(i);
		}
		
		for(int i = 0; i < lastname.length(); i++)
		{
			PatientID += (int) lastname.charAt(i);
		}
		
		for(int i = 0; i < username.length(); i++)
		{
			PatientID += (int) username.charAt(i);
		}
		
		for(int i = 0; i < password.length(); i++)
		{
			PatientID += (int) password.charAt(i);
		}
		
		if(exists(PatientID))
		{
			while(exists(PatientID))
			{
				PatientID++;
			}
		}
		
		String name = firstname + lastname;
		
		try
		{
			PreparedStatement ps = conn.prepareStatement("INSERT IGNORE INTO Patient (NAME, DOB, EMAIL, PHONE, USERNAME, PASSWORD, PATIENTID) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, dob);
			ps.setString(3, email);
			ps.setString(4, phonenum);
			ps.setString(5, username);
			ps.setString(6, password);
			ps.setInt(7, PatientID);
			ps.executeUpdate();
			
			return;
		}
		catch(SQLException e)
		{
			System.out.println("Error with createPatient!");
			e.printStackTrace();
		}
	}
	
	public void setGender(String user, String password, String Gender)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET GENDER=? WHERE USERNAME=? AND PASSWORD=?");
			ps.setString(1, Gender);
			ps.setString(2, user);
			ps.setString(3, password);
			
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with setPharmacy!");
			e.printStackTrace();
		}
	}
	
	public boolean checkUsernames(String username)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient WHERE USERNAME=?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return true; 
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with checkPassword");
			e.printStackTrace();
		}
		
		return false;
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
	
	public void setPharmacy(int PatientID, String pharmacy)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET PHARMACY=? WHERE PATIENTID=?");
			ps.setString(1, pharmacy);
			ps.setInt(2, PatientID);
			
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with setPharmacy!");
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getPatientNames(int DoctorID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME FROM Patient WHERE DOCTORID=?");
			ps.setInt(1, DoctorID);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<String> names = new ArrayList<String>();
			
			while(rs.next())
			{
				names.add(rs.getString("NAME"));
				
				return names; 
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPatientNames");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPharmacy(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT PHARMACY FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String pharmacy;
			
			if(rs.next())
			{
				pharmacy = rs.getString("PHARMACY");
				
				return pharmacy; 
			}
			
			
			return "";
		}
		catch(SQLException e)
		{
			System.out.println("Error with getPharmacy");
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void setHealth(int PatientID, String health)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET HEALTH=? WHERE PATIENTID=?");
			ps.setString(1, health);
			ps.setInt(2, PatientID);
			
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with setHealth!");
			e.printStackTrace();
		}
	}
	
	public String getHealth(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT HEALTH FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String health;
			
			if(rs.next())
			{
				health = rs.getString("HEALTH");
				
				return health; 
			}
			
			
			return "";
		}
		catch(SQLException e)
		{
			System.out.println("Error with getHealth");
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void setAppointment(int PatientID, String date)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET APPOINTMENT=? WHERE PATIENTID=?");
			ps.setString(1, date);
			ps.setInt(2, PatientID);
			
			ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with setAppointment!");
			e.printStackTrace();
		}
	}
	
	public String getAppointment(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT APPOINTMENT FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String appointment;
			
			if(rs.next())
			{
				appointment = rs.getString("APPOINTMENT");
				
				return appointment; 
			}
			
			
			return "";
		}
		catch(SQLException e)
		{
			System.out.println("Error with getAppointment");
			e.printStackTrace();
		}
		
		return "";
	}
	
	public ArrayList<Integer> getAppointments(String date)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT PATIENTID FROM Patient WHERE APPOINTMENT=?");
			ps.setString(1, date);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Integer> appointment = new ArrayList<Integer>();
			
			while(rs.next())
			{
				appointment.add(rs.getInt("PATIENTID"));
				return appointment;
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Error with getAppointments");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Integer> getAllPatients()
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Patient");
			
			ResultSet rs = ps.executeQuery();
			ArrayList<Integer> patientids = new ArrayList<Integer>();
			
			while(rs.next())
			{
				patientids.add(rs.getInt("PATIENTID"));
			}
			
			if(patientids != null)
			{
				return patientids;
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error with getAllPatients");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPatientList(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME, GENDER, PHARMACY, HEALTH FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String name = "", gender = "", pharmacy  = "", health = "";
			
			if(rs.next())
			{
				name = rs.getString("NAME");
				gender = rs.getString("GENDER");
				pharmacy = rs.getString("PHARMACY");
				health = rs.getString("HEALTH");
				
				if(pharmacy == null)
				{
					pharmacy = "not set";
				}
				
				if(health == null)
				{
					health = "not set";
				}
				
				if(gender == null)
				{
					gender = "not set";
				}
				
				return "Name: " + name + " | Gender: " + gender + " | Pharmacy: " + pharmacy + " | Health: " + health; 
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
	
	public String getPatient(int PatientID)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement("SELECT NAME, DOB, GENDER, APPOINTMENT FROM Patient WHERE PATIENTID=?");
			ps.setInt(1, PatientID);
			
			ResultSet rs = ps.executeQuery();
			String appointment = "", name = "", dob = "", gender = "";
			
			if(rs.next())
			{
				name = rs.getString("NAME");
				dob = rs.getString("DOB");
				gender = rs.getString("GENDER");
				appointment = rs.getString("APPOINTMENT");
				
				if(appointment == null)
				{
					appointment = "not set";
				}
				
				if(gender == null)
				{
					gender = "not set";
				}
				
				return "Name: " + name + " | DOB: " + dob + " | Gender: " + gender + " | Appointment: " + appointment; 
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
			System.out.println("Error with getDoctor");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void changeDoctor(int PatientID, int DoctorID)
	{
		try 
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET DOCTORID=? WHERE PATIENTID=?");
		    ps.setInt(1, DoctorID);
		    ps.setInt(2, PatientID);
		    ps.executeUpdate();
		} 
		catch(SQLException e) 	
		{
			System.out.println("Error with changeDoctor!");
			e.printStackTrace();
		} 
	}
	
	public void removePatient(int PatientID)
	{
		try 
		{
			PreparedStatement ps = conn.prepareStatement("UPDATE Patient SET DOCTORID=? WHERE PATIENTID=?");
		    ps.setInt(1, 0);
		    ps.setInt(2, PatientID);
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
