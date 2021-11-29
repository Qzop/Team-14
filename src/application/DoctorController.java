package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import mySQL.Doctor;
import mySQL.MySQLConnection;
import mySQL.Patient;

public class DoctorController implements Initializable
{
	private static String user, pass;
    
	@FXML
    private Label errorMSG;
	
	@FXML
	private Button addBtn;
	
	@FXML
    private ListView<String> patientList;
	
	@FXML
    private Button removeBtn;

    @FXML
    private Button search;
    
    @FXML
    private TextField patientIDText;
    
    @FXML
    private TextField searchField;
	
    @FXML
    private DatePicker date;

    @FXML
    private ListView<String> listview;

    @FXML
    private Button viewbtn;

    ObservableList<String> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        loadData();
    }
    
    public void loadData()
    {
    	ArrayList<Integer> patientids;
    	ArrayList<String> patientlist = new ArrayList<String>();
    	MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	Doctor doc = new Doctor(conn.getConnection());
    	MainController cont = new MainController();
    	user = cont.getDocUser();
    	pass = cont.getDocPass();
    	
    	int docid = doc.getDoctorID(user, pass);
    	
    	list.removeAll(list);
    	
    	if(date.getValue() == null)
    	{
    		listview.getItems().clear();
    		patientids = pat.getPatientIDs(docid);
    		
    		for(int i = 0; i < patientids.size(); i++)
    		{
    			patientlist.add(pat.getPatient(patientids.get(i)));
    			list.add(patientlist.get(i));
    		}
    	}
    	
    	listview.getItems().addAll(list);
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
    		
    		String appDate = date.getValue().toString();
    		patientids = pat.getAppointments(appDate);
    		
    		if(patientids != null)
    		{
    			for(int i = 0; i < patientids.size(); i++)
        		{
        			patientlist.add(pat.getPatient(patientids.get(i)));
        			listview.getItems().add(patientlist.get(i));
        		}
    		}
    		else
    		{
    			listview.getItems().add("No Appointments");
    		}
    	}
    }

    @FXML
    void addButton(ActionEvent event) 
    {
    	ArrayList<Integer> patientlist = new ArrayList<Integer>();
    	MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	Doctor doc = new Doctor(conn.getConnection());
    	int docid = doc.getDoctorID(user, pass);
    	
    	if(patientIDText.getText() == "")
    	{
    		errorMSG.setStyle("-fx-text-fill: red");
    		errorMSG.setText("         Add Error\nEnter an integer ID!");
    	}
    	else
    	{
    		errorMSG.setText("");
    		patientlist = pat.getAllPatients();
    		
    		int ID = 0;
    		
    		try
    		{
    			ID = Integer.parseInt(patientIDText.getText());
    			
    			if(patientlist.contains(ID))
        		{
        			if(pat.getDoctor(ID) == docid)
        			{
        				errorMSG.setStyle("-fx-text-fill: red");
        	    		errorMSG.setText("              Add Error\nThat patient is already your patient!");
        			}
        			else
        			{
        				pat.changeDoctor(ID, docid);
        				
        				errorMSG.setStyle("-fx-text-fill: green");
        	    		errorMSG.setText("Patient successfully added!");
        			}
        		}
        		else
        		{
        			errorMSG.setStyle("-fx-text-fill: red");
    	    		errorMSG.setText("           Add Error\nThat patient does not exist!");
        		}
    		}
    		catch(NumberFormatException e)
    		{
    			errorMSG.setStyle("-fx-text-fill: red");
        		errorMSG.setText("          Add Error\nEnter an integer ID!");
    		}
    	}
    }

    @FXML
    void removeButton(ActionEvent event) 
    {
    	ArrayList<Integer> patientlist = new ArrayList<Integer>();
    	MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	Doctor doc = new Doctor(conn.getConnection());
    	int docid = doc.getDoctorID(user, pass);
    	
    	if(patientIDText.getText() == "")
    	{
    		errorMSG.setStyle("-fx-text-fill: red");
    		errorMSG.setText("     Remove Error\nEnter an integer ID!");
    	}
    	else
    	{
    		errorMSG.setText("");
    		patientlist = pat.getPatientIDs(docid);
    		
    		int ID = 0;
    		
    		try
    		{
    			ID = Integer.parseInt(patientIDText.getText());
    			
    			int index = -1;
        		
        		for(int i = 0; i < patientlist.size(); i++)
        		{
        			if(patientlist.get(i) == ID)
            		{
            			index = i;
            		}
        		}
        		
        		if(index != -1)
        		{
        			patientlist.remove(index);
        			pat.removePatient(ID);
        			
        			errorMSG.setStyle("-fx-text-fill: green");
    	    		errorMSG.setText("Patient successfully removed!");
        		}
        		else
        		{
        			errorMSG.setStyle("-fx-text-fill: red");
            		errorMSG.setText("     Remove Error\nThat PatientID does not exist.");
        		}
    		}
    		catch(NumberFormatException e)
    		{
    			errorMSG.setStyle("-fx-text-fill: red");
        		errorMSG.setText("     Remove Error\nEnter an integer ID!");
    		}
    	}
    }

    @FXML
    void searchButton(ActionEvent event) 
    {
    	ArrayList<Integer> patientlist = new ArrayList<Integer>();
    	MySQLConnection conn = new MySQLConnection();
    	Patient pat = new Patient(conn.getConnection());
    	Doctor doc = new Doctor(conn.getConnection());
    	int docid = doc.getDoctorID(user, pass);
    	
    	patientList.getItems().clear();
    	
    	if(searchField.getText() == "")
    	{
    		errorMSG.setStyle("-fx-text-fill: red");
    		errorMSG.setText("       Search Error\nEnter an integer ID!");
    	}
    	else
    	{
    		patientlist = pat.getPatientIDs(docid);
    		
    		int ID = 0;
    		
    		try
    		{
    			ID = Integer.parseInt(searchField.getText());
    			
    			int index = -1;
        		
        		for(int i = 0; i < patientlist.size(); i++)
        		{
        			if(patientlist.get(i) == ID)
            		{
            			index = i;
            		}
        		}
        		
        		if(index != -1)
        		{
        			patientList.getItems().add(pat.getPatientList(patientlist.get(index)));
        		}
        		else
        		{
        			errorMSG.setStyle("-fx-text-fill: red");
            		errorMSG.setText("       Search Error\nThat PatientID does not exist.");
        		}
    		}
    		catch(NumberFormatException e)
    		{
    			errorMSG.setStyle("-fx-text-fill: red");
        		errorMSG.setText("       Search Error\nEnter an integer ID!");
    		}
    	}
    }

}

