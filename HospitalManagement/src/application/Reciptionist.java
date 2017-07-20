package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Reciptionist {
@FXML 
static TextField DM;
	@FXML
	public TextField name;
	@FXML
	public TextField age;
	
	@FXML
	public TextField seriousness;
	@FXML
	public TextField ailment;
	@FXML
	public Label status;
	
	@FXML
	public ListView Name;
	
	@FXML
	public ListView Age;
	@FXML
	public ListView ID;
	@FXML
	public ListView Seriousness;
	@FXML
	public ListView Ailment;
	
	public int count =0,realCount=0;
	Socket s;
	public int[] ser,idArr,nameI,ageI,ailI,serI;
	String[] ailArr,nameArr,ageArr,patientDetails;
	String dm;
	int patNum =0;
	//Random r = new Random();
	int id;
	ObservableList<String> iname = FXCollections.observableArrayList();
	ObservableList<String> iage = FXCollections.observableArrayList();
	ObservableList<String> iailment = FXCollections.observableArrayList();
	ObservableList<String> iseriousness = FXCollections.observableArrayList();
	ObservableList<String> iid = FXCollections.observableArrayList();
	ObservableList<String> looper = FXCollections.observableArrayList();
	ObservableList<String> patDetails = FXCollections.observableArrayList();
	
	String ip = "127.0.0.1";
	int port = 9999;
	
	ObjectOutputStream os;
	
	public void addPatient(ActionEvent event){
		if(age.getText()=="")
			return;
		if(name.getText()=="")
			return;
		if(ailment.getText()=="")
			return;
		if(seriousness.getText()=="")
			return;
		int serlevel = Integer.parseInt(seriousness.getText());
		if(serlevel<0 || serlevel>3){
			status.setText("Please enter proper seriousness level");
			return;
		}
		if(count>5){
			status.setText("Only 6 patients allowed in the waiting hall");
			
			return;
		}
		
		
		
		count++;
		
		
		id = count;
		iname.add(name.getText());
		iage.add(age.getText());
		iid.add(id + "");
		iailment.add(ailment.getText());
		
		iseriousness.add(seriousness.getText());
		
		//add to listview
		
		
		
		
		ser = new int[count];
		idArr = new int[count];
		ailArr = new String[count];
		ageArr = new String[count];
		nameArr = new String[count];
		ailI = new int[count];
		nameI = new int[count];
		ageI = new int[count];
		int i=0;
		for(String looper:iseriousness){
			ser[i] = Integer.parseInt(looper);
			
			
			i++;
			
			
			
		}
		 i=0;
		for(String looper:iailment){
			ailArr[i] = looper;
			
			
			i++;
			
			
			
		}
		i=0;
		for(String looper:iid){
			idArr[i] = Integer.parseInt(looper);
			
			i++;
			
			
			
		}
		i=0;
		for(String looper:iage){
			ageArr[i] = looper;
			
			i++;
			
			
			
		}
		i=0;
		for(String looper:iname){
			nameArr[i] = looper;
			
			i++;
			
			
			
		}
		
		
		sortPatients(idArr,ser);
		
		
	}
	
	public void selectPatient(ActionEvent event) throws UnknownHostException, IOException{
		 
		 Main.os = new ObjectOutputStream(Main.s.getOutputStream());
		
		 if(realCount==0){
			status.setText("Patient list is empty");
			iid.removeAll(iid);
			iseriousness.removeAll(iseriousness);
			
			iage.removeAll(iage);
			iailment.removeAll(iailment);
			iname.removeAll(iname);
			patNum=0;
			count=0;
			
		}
		else{
			
		patientDetails = new String[10];
			//add patientdetails to Array
			int i=0;
			for(String looper:iname){
				if(i==1)
					break;
				patientDetails[0]=looper;
				i++;
				
			}
			
			i=0;
			for(String looper:iage){
				if(i==1)
					break;
				patientDetails[1]=looper;
				i++;
				
			}
			
			i=0;
			for(String looper:iailment){
				if(i==1)
					break;
				patientDetails[2]=looper;
				i++;
				
			}
		
			Main.os.writeObject(patientDetails);
			Main.os.flush();
			
			
		//	System.out.println(patientDetails[0] + " " + patientDetails[1] + " " + patientDetails[2]);
		iid.remove(0);
		iage.remove(0);
		iailment.remove(0);
		iseriousness.remove(0);
		iname.remove(0);
		realCount--;
		count--;
		patNum++;
		
		}
		
		
		
		
	}

	private void sortPatients(int[] idArr, int[] ser) {
		// TODO Auto-generated method stub
		for(int i=0;i<ser.length;i++){
			for(int j=i+1;j<ser.length;j++){
				if(ser[i]<ser[j]){
					int temp = ser[i];
					ser[i] =ser[j];
					ser[j]=temp;
					//swap ids
					temp = idArr[i];
					idArr[i]= idArr[j];
					idArr[j]=temp;
					
					String tempStr =ailArr[i];
					ailArr[i] = ailArr[j];
					ailArr[j]=tempStr;
					
					tempStr = nameArr[i];
					nameArr[i] = nameArr[j];
					nameArr[j]= tempStr;
					
					tempStr = ageArr[i];
					ageArr[i] = ageArr[j];
					ageArr[j]=tempStr;
					
				}
			}
		}
		
		iid.removeAll(iid);
		iseriousness.removeAll(iseriousness);
		
		iage.removeAll(iage);
		iailment.removeAll(iailment);
		iname.removeAll(iname);
		
		for(int i=0;i<count;i++){
			iid.add(idArr[i]+"");
			iseriousness.add(ser[i] + "");
			iname.add(nameArr[i]);
		    iage.add(ageArr[i]);
		    iailment.add(ailArr[i]);
		    
			
			
		}
		realCount = idArr.length;
	
		
		
		ID.setItems(iid);
		Name.setItems(iname);
		Seriousness.setItems(iseriousness);
		Ailment.setItems(iailment);
		Age.setItems(iage);
		
		
	}
	
	
	public void checkAvailability(ActionEvent event) throws IOException{
	            
		
		
		
	}
	
}
