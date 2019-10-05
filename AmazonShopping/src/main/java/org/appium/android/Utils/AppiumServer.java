package org.appium.android.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.appium.android.Commons.Helper;

public class AppiumServer {

	// FOR SERVER TESTING 
	public static void main(String args[]) throws Exception {

		System.out.println(System.getProperty("user.home"));
		AppiumServer appiumServer = new AppiumServer();
		appiumServer.startAppiumServer();
	}

	/**
	 * This method invoke Appium server methods and all command parameter get from
	 * Application.properties file. StopAppium method stop appium server if run in
	 * background.
	 * 
	 * @throws Exception
	 *
	 */
	public void startAppiumServer() throws Exception {
		RuntimeExec appiumObj = new RuntimeExec();
		appiumObj.stopAppium("killall -9 node");
		// appiumObj.stopAppium("killall -9 $(lsof -t -i:4445)");
		System.out.println(
				Helper.getProjectProperties("nodeJsLocation") + " " + Helper.getProjectProperties("appiumLocation")
						+ " --address " + Helper.getProjectProperties("IpAddress") + " --port "
						+ Helper.getProjectProperties("appiumPortNumber") + " --no-reset --command-timeout "
						+ Helper.getProjectProperties("command-timeout"));
		appiumObj.startAppium(
				Helper.getProjectProperties("nodeJsLocation") + " " + Helper.getProjectProperties("appiumLocation")
						+ " --address " + Helper.getProjectProperties("IpAddress") + " --port "
						+ Helper.getProjectProperties("appiumPortNumber") + " --no-reset --command-timeout "
						+ Helper.getProjectProperties("command-timeout"));
	}
	
	

	/**
	 * This class contain two method
	 * 
	 * @method StartAppium : use for Start the Appium Server
	 * @method StopAppium : use for Stop the Appium Server
	 * @author shubhamverma
	 *
	 */

	private class RuntimeExec {

		public StreamWrapper getStreamWrapper(InputStream is, String type) {
			return new StreamWrapper(is, type);
		}

		private class StreamWrapper extends Thread {
			InputStream is = null;
			String type = null;
			String message = null;

			StreamWrapper(InputStream is, String type) {
				this.is = is;
				this.type = type;
			}

			public void run() {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuffer buffer = new StringBuffer();
					String line = null;
					while ((line = br.readLine()) != null) {
						buffer.append(line);// .append("\n");
					}
					message = buffer.toString();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}

		/**
		 * This Method use for Start Appium Server here we use Runtime class which is
		 * profile runtime Environment for Execution like Linux or windows.
		 *
		 * 
		 * @param command Command should contain All The Parameters which is required
		 *                for star the Appium Server EX :-
		 *                nodeJsLocation,appiumLocation,IpAddress,appiumPortNumber,command-timeout
		 *
		 */
		public void startAppium(String command) {
			Runtime rt = Runtime.getRuntime();
			RuntimeExec rte = new RuntimeExec();
			StreamWrapper error, output;

			try {
				Process proc = rt.exec(command);
				error = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
				output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
				// int exitVal = 0;

				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				String s;
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
					if (s.contains("Appium REST http")) {
						System.out.println("Started Appium Server");
						break;
					}
				}
				error.start();
				output.start();
				error.join(3000);
				output.join(3000);
				// exitVal = proc.waitFor();
				System.out.println("Output: " + output.message + "\nError: " + error.message);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/**
		 * This Method use for Start Appium Server here we use Runtime class which is
		 * profile runtime Environment for Execution like Linux or windows.
		 *
		 * 
		 * @param command 
		 */
		public void stopAppium(String command) {
			Runtime rt = Runtime.getRuntime();
			RuntimeExec rte = new RuntimeExec();
			StreamWrapper error, output;

			try {
				Process proc = rt.exec(command);
				error = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
				output = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
				error.start();
				output.start();
				error.join(3000);
				output.join(3000);
				if (error.message.equals("") && output.message.equals(""))
					System.out.println("Closed Appium Server");
				else if (error.message.contains("No matching processes belonging to you were found")) {
					// Display nothing as no instances of Appium Server were found running
				} else {
					System.out.println("Unable to Close Appium Server");
					System.out.println("Output: " + output.message + "\nError: " + error.message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
