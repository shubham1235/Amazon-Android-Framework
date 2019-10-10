package org.appium.android.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class DeviceLogThread extends Thread{
	
	private static String path = System.getProperty("user.home")+"/Library/Android/sdk";
	public String deviceId;
	public OutputStream os = null;
	public String appName ="";
	
	public DeviceLogThread(String deviceId,OutputStream os,String appName){
		super(deviceId);
		this.deviceId = deviceId;
		this.os = os;
		this.appName = appName;
	}

	
	public void run() {
		 try {	 
			 
			 
			   String command =path+"/platform-tools/adb -s "+deviceId+" shell logcat | grep -i "+appName;

			   
			   Process process = Runtime.getRuntime().exec(command);
			   BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			   
			   String deviceLog;		
			   while (( deviceLog = reader.readLine()) != null) {
				   write(deviceId +" : "+deviceLog+System.getProperty( "line.separator" ),os);   
			   }
			   
			  } catch (IOException e) {
			  }		
	}

	
	public static void write(String data,OutputStream os){
	      try {
	          os.write(data.getBytes() ,0, data.length());
	        }  catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	

}
