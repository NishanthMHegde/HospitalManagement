package application;
	
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static ServerSocket ss,ss2;
	public static Socket s,s2;
	public static ObjectInputStream oi;
	public static OutputStreamWriter out;
	public static ObjectOutputStream oo;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Doctor.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		 ss = new ServerSocket(9992);
		 ss2 = new ServerSocket(9993);
		 s = ss.accept();
		 s2=ss2.accept();
		 Main.oo = new ObjectOutputStream(Main.s2.getOutputStream());
		
		
		 
		launch(args);
	}
}
