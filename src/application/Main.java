package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mySQL.MySQLConnection;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.*;

public class Main extends Application {
	
	private static MySQLConnection conn = new MySQLConnection();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/DoctorNursePatient.fxml"));
			Scene scene = new Scene(root,350,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		conn.SQLSetup();
		launch(args);
	}
}
