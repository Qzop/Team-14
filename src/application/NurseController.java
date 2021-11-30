package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mySQL.Nurse;
import mySQL.MySQLConnection;
import mySQL.Patient;
import mySQL.Doctor;

public class NurseController 
{
	private static String user, pass;
    
	// Appointments tab
    @FXML
    private DatePicker date;
    @FXML
    private ListView<String> appt_listview;
    @FXML
    private Button btn_view;
    
    // Patient List tab 
    @FXML
    private ListView<String> patientList;
    @FXML
    private Button btn_SearchPatient;
    @FXML
    private TextField txt_PatientID;
    @FXML
    private TextField txt_PatientID2;
    @FXML
    private Button btn_PatientAdd;
    @FXML
    private Button btn_PatientRemove;
    
    // Doctor List tab 
    @FXML
    private ListView<String> doctorList;
    @FXML
    private Button btn_SearchDoctor;
    @FXML
    private TextField txt_DoctoID;
    
    // Patient Vitals Tab
    @FXML
    private Label lbl_PatientID;
    @FXML
    private Label lbl_Weight;
    @FXML
    private Label lbl_Age;
    @FXML
    private Label lbl_Height;
    @FXML
    private Label lbl_Temperature;
    @FXML
    private Label lbl_BloodPressure;
    @FXML
    private TextField txt_PatientID3;
    @FXML
    private TextField txt_Weight;
    @FXML
    private TextField txt_Age;
    @FXML
    private TextField txt_Height;
    @FXML
    private TextField txt_Temperature;
    @FXML
    private TextField txt_BloodP;
    @FXML
    private Button btn_PatientVitals;
    
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
    	Nurse nur = new Nurse(conn.getConnection());
    	
    	int nurseID = nur.getNurseID(user, pass);
    	
    	System.out.println("NurseID: " + nurseID);
    	
    	if(date.getValue() == null)
    	{
    		patientList.getItems().clear();
    		patientids = pat.getPatientIDs(nurseID);
    		
    		for(int i = 0; i < patientids.size(); i++)
    		{
    			patientlist.add(pat.getPatient(patientids.get(i)));
    			patientList.getItems().add(patientlist.get(i));
    		}
    	}
    	else
    	{
    		patientList.getItems().clear();
    	}

    }
    public void onClick_PatientVitals(ActionEvent e)
    {
    	
    }
    public void onClick_PatientSearch(ActionEvent e)
    {
    	
    }
    public void onClick_addPatient(ActionEvent e)
    {
    	
    }
    public void onClick_RemovePatient(ActionEvent e)
    {
    	
    }
    public void onClick_SearchDoctor(ActionEvent e)
    {
    	
    }
}
