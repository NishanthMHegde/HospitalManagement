package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PharmaController {
	
	@FXML
	private  ListView Med1;
	@FXML
	private  ListView Med2;
	@FXML
	private  ListView Med3;
	@FXML
	private  ListView Med4;
	@FXML
	private  ListView Diag;
	@FXML
	private ListView TokenNum;
	@FXML
	private  ListView Name;
	@FXML
	private  Label status;
	
	String[] diagDetails = new String[12];
	
	ObservableList<String> tn = FXCollections.observableArrayList();
	ObservableList<String> m1 = FXCollections.observableArrayList();
	ObservableList<String> m2 = FXCollections.observableArrayList();
	ObservableList<String> m3 = FXCollections.observableArrayList();
	ObservableList<String> m4 = FXCollections.observableArrayList();
	ObservableList<String> iname = FXCollections.observableArrayList();
	ObservableList<String> d = FXCollections.observableArrayList();
	
	long c1,c2,c3,c4,c5;

	 public  long crc32(String input) {
	        byte[] bytes = input.getBytes();
	        Checksum checksum = new CRC32(); // java.util.zip.CRC32
	        checksum.update(bytes, 0, bytes.length);

	        return checksum.getValue();
	    }
	 public  void receivePatient(ActionEvent event) throws ClassNotFoundException, IOException{
		 try{
		 
		 diagDetails = (String[]) Main.oi.readObject();
		 
		 c1 = crc32(diagDetails[3]);
		 c2 = crc32(diagDetails[4]);
		 c3 = crc32(diagDetails[5]);
		
		 System.out.println(c1);
		
		 if((c1+"").equals(diagDetails[0])&&(c2+"").equals(diagDetails[1])&&(c3+"").equals(diagDetails[2])
				 ){
			 
			 String med1 =(diagDetails[3]);
			 System.out.println(med1);
			 m1.addAll(med1);
			 m2.add(diagDetails[4]+"");
			 
			 d.add(diagDetails[5]+"");
			 tn.add(diagDetails[6]);
			 iname.add(diagDetails[7]);
			 
			 
			 
			 TokenNum.setItems(tn);
			 Med1.setItems(m1);
			 Med2.setItems(m2);
			
			 Diag.setItems(d);
			Name.setItems(iname);
		 }
			 
		 }catch(Exception e){
			 
		 }
		 
	 }
	 public void provideMedicine(ActionEvent event){
		 tn.remove(0);
		 d.remove(0);
		 m1.remove(0);
		 m2.remove(0);
		 iname.remove(0);
		 TokenNum.setItems(tn);
		 Med1.setItems(m1);
		 Med2.setItems(m2);
		
		 Diag.setItems(d);
		Name.setItems(iname);
		 
		 
	 }

}
