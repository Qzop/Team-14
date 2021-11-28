package mySQL;

import java.sql.*;

public class MySQLConnection 
{
	private static Connection connection;	
	private Doctor doc;
	private Nurse nurse;
	private Patient patient;
	
	public void SQLSetup()
	{
		try
		{
			synchronized(this)
			{
				if(getConnection() != null && !getConnection().isClosed())
				{
					return;
				}
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/team15", "root", "team15"));
				
				if(connection != null)
				{
					System.out.println("Connection successful!");
					patient = new Patient(connection);
					doc = new Doctor(connection);
					nurse = new Nurse(connection);
					patient.createPatientTable();
					doc.createDoctorTable();
					nurse.createNurseTable();
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("Database Not Connected");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class not found");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
}
