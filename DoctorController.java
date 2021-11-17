package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DoctorController {
	
	//patient list
	@FXML
	private Label lb1;
	@FXML
	private ListView<String> list1;		// change to account object later
	
	@FXML
	private Button removeButton;
	
	
	public void click() {
		
	}
}
