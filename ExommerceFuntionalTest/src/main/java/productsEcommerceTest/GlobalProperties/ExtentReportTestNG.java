package productsEcommerceTest.GlobalProperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "//reports//index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("EcommerceApplicationTest");
		reporter.config().setReportName("WebApplcationTestReport");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Automation Tester", "Naveen");

		return extent;

	}

}
