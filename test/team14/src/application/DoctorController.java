package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mySQL.Doctor;
import mySQL.MySQLConnection;
import mySQL.Patient;

public class DoctorController 
{
	private static String user, pass;
    
    @FXML
    private DatePicker date;

    @FXML
    private ListView<String> listview;

    @FXML
    private Button viewbtn;

    public void setUserAndPass(String username, String password)
    {
    	user = username;
    	pass = password;
    }
    
    @FXML
    void viewClicked(MouseEvent event) 
    {
    	ArrayList<Integer> patientids;
    	ArrayList<String> patientlist = new ArrayList<String>();
    	MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	Doctor doc = new Doctor(conn.getConnection());
    	
    	int docid = doc.getDoctorID(user, pass);
    	
    	System.out.println("DocID: " + docid);
    	
    	if(date.getValue() == null)
    	{
    		listview.getItems().clear();
    		patientids = pat.getPatientIDs(docid);
    		
    		for(int i = 0; i < patientids.size(); i++)
    		{
    			patientlist.add(pat.getPatient(patientids.get(i)));
    			listview.getItems().add(patientlist.get(i));
    		}
    	}
    	else
    	{
    		listview.getItems().clear();
    	}

    	
    }

}

