package org.appium.android.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class IOUtils {
	
	public static OutputStream os = null;
	DeviceLogThread logThread ;
	
	public void startAppLogsThread(String deviceId,String appName) {			
		try {
			os = new FileOutputStream(new File(System.getProperty("user.dir")+"/logs/device.log"));
		} catch (FileNotFoundException e1) {
		}
		
			logThread = new DeviceLogThread(deviceId, os ,appName);
			new Thread(logThread).start();

	}
	
	
	public void  stopAppLogsThread()
	{
		logThread.interrupted();
	}
	
	
}
