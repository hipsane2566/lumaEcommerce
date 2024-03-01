package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import io.percy.selenium.Percy;

public class BaseClass {
	static public WebDriver driver;
	//public WebDriver driver;// parallel testing
	public Logger log;
	public Properties p;
	public static Percy percy;
	
	//"regression"
	@BeforeClass(groups= {"sanity"})
	@Parameters({"browser","OS"})
	public void setup(@Optional("chrome") String br , @Optional("Windows") String os ) throws IOException {
		//loading properties file
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		//loading logging file
		log = LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//setup Operating system based on requirement
			if(os.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching operating system.....");
				return;
			}
			
			//setup driver based on OS requirement
			switch(br) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser.....");
			return;
			}
			
			//create instance of remotewebdriver
			driver = new RemoteWebDriver(new URL("http://192.168.0.5:4444"), capabilities);
			
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			default: System.out.println("No matching browser"); return;
			}
		}
		percy = new Percy(driver);
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups= {"sanity","regression"})
	public void tearDown() {
		driver.quit();
	}
	
	public void implicitWaits() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String randomString() {
		String string = RandomStringUtils.randomAlphabetic(5);
		return string;
	}
	
	public String randomNumber() {
		String string = RandomStringUtils.randomNumeric(10);
		return string;
	}
	
	public String randomAlphaNumberic() {
		String string = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(10);
		return (string + "@" + num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
}
