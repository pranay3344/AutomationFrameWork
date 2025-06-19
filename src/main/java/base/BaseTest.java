package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.lang.reflect.Method;

public class BaseTest {
    public static WebDriver driver;
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeTest
    public void beforeTestMethod(){
sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir"))+ File.separator+"reports"+File.separator+"report.html");
extent = new ExtentReports();
extent.attachReporter(sparkReporter);
sparkReporter.config().setTheme(Theme.DARK);
extent.setSystemInfo("HostName","Prana");
extent.setSystemInfo("UserName","root");
sparkReporter.config().setDocumentTitle("Automation Report");
sparkReporter.config().setReportName("Automation Test Resuts");

    }
    @BeforeMethod

    @Parameters("browser")
    public void beforeMethodMethod(String browser, Method testMethod){
        logger = extent.createTest(testMethod.getName());
        setupDriver(browser);
    }
    public void setupDriver(String browser){
if(browser.equalsIgnoreCase("chrome")) {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
}
        if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
}
}

