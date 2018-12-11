package com.w2a.zoho.rough;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
	
	private RemoteWebDriver driver;	
	public static ThreadLocal<RemoteWebDriver> dr= new ThreadLocal<RemoteWebDriver>();
	
	public void setDriver(RemoteWebDriver driver) {
		dr.set(driver);
	}
	public WebDriver getDriver() {
		return dr.get();
	}
	
	public void openBrowser(String browser) {
		/*if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe");
			 driver=new ChromeDriver();			
		}else if(browser.equals("firefox")) {			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\geckodriver.exe");
			 driver=new FirefoxDriver();	
		}*/
		DesiredCapabilities cap =null;
		if(browser.equals("firefox")) {
			cap=DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.ANY);
		}
		else if(browser.equals("chrome")) {
			cap=DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
		}
		else if(browser.equals("ie")) {
			cap=DesiredCapabilities.internetExplorer();
			cap.setBrowserName("iexplorer");
			cap.setPlatform(Platform.WIN10);
		}
		
		try {
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().get("https://www.zoho.com/");
	}
	
	public void closeBrowser() {
		getDriver().quit();
	}



}
