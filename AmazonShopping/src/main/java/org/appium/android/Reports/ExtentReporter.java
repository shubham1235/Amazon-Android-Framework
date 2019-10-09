package org.appium.android.Reports;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporter implements IReporter {

	private ExtentReports extent;

	static DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");

	static String data = df.format(new Date());

	public static int testcasesCount;
	public static int passedTestcasesCount = 0;
	public static int failedTestcasesCount = 0;
	public static int skippedTestcasesCount = 0;

	static boolean exceptionPresent = false;

	public static void main(String args[]) throws Exception {
		ExtentReporter e = new ExtentReporter();
	}

	public static String getPropertyFromConfig(String property) {

		return property;
	}

	@SuppressWarnings("unchecked")
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		extent = new ExtentReports("./TestReports/finalReport_" + data + "/TestExecutionReport.html", true);
		Map sysInfo = new HashMap();
		String projectName = ExtentReporter.getPropertyFromConfig("testProject");
		String environment = ExtentReporter.getPropertyFromConfig("TestorLive");
		sysInfo.put("Project", projectName);
		sysInfo.put("Environment", environment);
		extent.addSystemInfo(sysInfo);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {

				ITestContext context = r.getTestContext();
				System.out.println("EXTENT REPORT : " + context.getName());
				context.getAllTestMethods()[0].getDescription();
				try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS, projectName, environment);
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL, projectName, environment);
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP, projectName, environment);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status, String projectName, String environment)
			throws MalformedURLException {

		ExtentTest test;

		if (tests.size() > 0) {

			for (ITestResult result : tests.getAllResults()) {

				String packageName = result.getMethod().getTestClass().getName();
				String methodName = result.getMethod().getMethodName();
				String className = result.getMethod().getTestClass().getName();

				Date date = new Date();

				Timestamp ts_now = new Timestamp(date.getTime());

				String testClassName = result.getMethod().getTestClass().getName();
				String testMethodName = result.getMethod().getMethodName();

				test = extent.startTest(testMethodName);

				int len = testClassName.split("\\.").length;

				if (len > 0)
					test.assignCategory(testClassName.split("\\.")[len - 1]);
				else
					test.assignCategory(testClassName);

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getMethod().getDescription() == null) {

				}

				else {
					String testcase[] = result.getMethod().getDescription().split(",");
					for (int i = 0; i < testcase.length; i++) {
						testcasesCount++;
					}

					extent.addSystemInfo("Test Cases Count", String.valueOf(testcasesCount));

					if (status.equals(LogStatus.PASS)) {
						for (int i = 0; i < testcase.length; i++) {
							passedTestcasesCount++;
						}
					}

					if (status.equals(LogStatus.FAIL)) {
						for (int i = 0; i < testcase.length; i++) {
							failedTestcasesCount++;
						}

					}

					if (status.equals(LogStatus.SKIP)) {
						for (int i = 0; i < testcase.length; i++) {
							skippedTestcasesCount++;
						}

					}

					extent.addSystemInfo("Passed Test Cases Count", String.valueOf(passedTestcasesCount));
					extent.addSystemInfo("Failed Test Cases Count", String.valueOf(failedTestcasesCount));
					extent.addSystemInfo("Skipped Test Cases Count", String.valueOf(skippedTestcasesCount));

					test.log(LogStatus.INFO, "Manual Test Cases", result.getMethod().getDescription());
				}

				if (result.getThrowable() != null) {
					if (result.getThrowable().toString().contains("java.lang.AssertionError")) {
						test.log(LogStatus.INFO, result.toString().split("output=")[1]);
						test.log(status, result.getThrowable());

					} else {
						test.log(LogStatus.INFO, result.toString().split("output=")[1]);
						test.log(status, result.getThrowable());
					}
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}
				extent.endTest(test);

			}

		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	public void openReportTakeScreenshot(String url) {
		try {
			String screenshootLocation = url.replace("file://", "");
			Process p = Runtime.getRuntime()
					.exec("/usr/local/bin/node " + System.getProperty("user.dir") + "/src/main/resources/Puppeteer.js "
							+ url + " "
							+ screenshootLocation.replace("TestExecutionReport.html", "ReportScreenShot.jpg"));

			System.err.println(
					"/usr/local/bin/node " + System.getProperty("user.dir") + "/src/main/resources/Puppeteer.js " + url
							+ " " + url.replace("TestExecutionReport.html", "ReportScreenShot.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
			StringSelection stringSelection = new StringSelection(url);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);

		}
	}

}
