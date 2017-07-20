package application;


import java.sql.*;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DoctorController {
	@FXML
	private TextField Medicine1;
	@FXML
	private TextField Medicine2;
	@FXML
	private TextField Medicine3;
	@FXML
	private TextField Medicine4;
	
	//patient details
	@FXML
	private TextField Name;
	@FXML
	private TextField TokenNum;
	@FXML
	private TextField Ailment;
	
	@FXML
	private TextArea Diagnosis;
	Socket s;
	String patientDetails[] = new String[3];
	ObjectInputStream oi;
	long c1,c2,c3,c4,c5;
	String[] diagDetails = new String[12];
	String diag,med1,med2,med3,med4,tok,name;
	
	 public static long crc32(String input) {
	        byte[] bytes = input.getBytes();
	        Checksum checksum = new CRC32(); // java.util.zip.CRC32
	        checksum.update(bytes, 0, bytes.length);

	        return checksum.getValue();
	    }
	public void callPatient(ActionEvent event) throws IOException, ClassNotFoundException{
		
		
		
		try{
			Main.oi= new ObjectInputStream(Main.s.getInputStream());
		patientDetails = (String[]) Main.oi.readObject();
		Name.setText(patientDetails[0] + "");
		TokenNum.setText(patientDetails[1] + "");
		Ailment.setText(patientDetails[2] + "");
		
		
		}catch(Exception e){
			
		}
		
	
		
	}
	
	public void inform(ActionEvent event) throws IOException{
		
	}
	
public void submitDiag(ActionEvent event) throws IOException, ClassNotFoundException, SQLException{
	
     
	med1=(Medicine1.getText());
	med2=(Medicine2.getText());
	
	diag = Diagnosis.getText();
	tok = TokenNum.getText();
	name = Name.getText();
	c1 = crc32(med1);
	c2 = crc32(med2);
	
	c5 = crc32(diag);
	diagDetails[0] = c1+"";
	diagDetails[1] = c2+"";
	
	diagDetails[2] = c5+"";
	diagDetails[3] = med1;
	diagDetails[4] = med2;
	
	diagDetails[5] = diag;
	diagDetails[6] = tok;
	diagDetails[7] = name;
	Class.forName("com.mysql.jdbc.Driver");
	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diagnostics","root","");
	Statement stmt = myConn.createStatement();
	String query = "INSERT INTO patientinfo(Name,TokenNum,Date,Medicine1,Medicine2"
			+ ",Diagnostic) VALUES(?,?,?,?,?,?)";
	
	PreparedStatement preparedStatement = myConn.prepareStatement(query);
	preparedStatement.setString(1,name);
	preparedStatement.setString(2,tok);
	preparedStatement.setString(3,"23");
	preparedStatement.setString(4,med1);
	preparedStatement.setString(5,med2);
	
	preparedStatement.setString(6,diag);
			preparedStatement.executeUpdate();
	
	
	System.out.println(c1 + " " + c2+ " "+ c3 +" "+ c4 + " " + c5);
	Main.oo.writeObject(diagDetails);
	Main.oo.flush();
     
	Main.oo.reset();
	
     
	}
	public void initialize() throws IOException, ClassNotFoundException{
		
	}
	

}
