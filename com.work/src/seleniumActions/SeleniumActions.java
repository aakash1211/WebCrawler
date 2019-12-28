package seleniumActions;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumActions {
	public SeleniumActions() {
		super();
		
	}
	
	
	
	static WebDriver driver;
	
	
	
	
	public  boolean openBrowser(String url) {
		try {
			
			
			System.setProperty("webdriver.gecko.driver", "/WebCrawler/com.work/WebContent/WEB-INF/driver/geckodriver");
			
			//DesiredCapabilities ds=DesiredCapabilities.htmlUnit();
			driver=new FirefoxDriver();
			
			System.out.println("driver: "+driver);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(url);
			System.out.println(url);
			return true;
			
		}catch(Exception ex) {
			System.out.println("error while opening browser: "+ex);
			ex.printStackTrace();
			return false;
			
		}
	}
	
	
	public By locator(String locatoryType,String locatorValue) {
		By by=null;
		try {
			switch(locatoryType) {
			case "id":
				by=By.id(locatorValue);
				System.out.println("element captured by id: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "class":
				by=By.className(locatorValue);
				System.out.println("element captured by class: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "name":
				by=By.name(locatorValue);
				System.out.println("element captured by name: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "tag":
				by=By.tagName(locatorValue);
				System.out.println("element captured by tag: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "link":
				by=By.linkText(locatorValue);
				System.out.println("element captured by link: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "partialLink":
				by=By.partialLinkText(locatorValue);
				System.out.println("element captured by partialLink: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "css":
				by=By.cssSelector(locatorValue);
				System.out.println("element captured by id: "+locatorValue);
				break;
				
			}
			switch(locatoryType) {
			case "xpath":
				by=By.xpath(locatorValue);
				System.out.println("element captured by xpath: "+locatorValue);
				break;
				
			}
			
		}catch(Exception ex) {
			System.out.println("error while getting element: "+ex);
		}
		
		return by;
		
	}
	
	
	public boolean enterText(String locatorType,String locatoryValue,String text) {
		try {
			
			By locator;
			locator=locator(locatorType,locatoryValue);
			WebElement element=driver.findElement(locator);
			element.clear();
			System.out.println("element is cleared");
			element.sendKeys(Keys.chord(Keys.CONTROL,"a"),text);
			return true;
		}catch(Exception ex) {
			System.out.println("error while entering text: "+ex);
			return false;
		}
		
		
		
	}
	
	public boolean closeBrowser(String args) {
		try {
			driver.close();
			return true;
		}catch(Exception  ex) {
			System.out.println("error while closing browser: "+ex);
			return false;
		}
	}
	
	public boolean click(String locatorType,String locatorValue) {
		try {
			WebDriverWait wait=new WebDriverWait(driver,10);
			By locator;
			locator=locator(locatorType,locatorValue);
			WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(locator));
			String eleName=ele.getText();
			ele.click();
			return true;
		}catch(Exception ex) {
			System.out.println("error while click: "+ex);
			return false;
		
		}
		
	}
	public boolean clicks(String locatorType,String locatorValue,int index) {
		try {
			
			int count=1;
			WebDriverWait wait=new WebDriverWait(driver,10);
			By locator;
			locator=locator(locatorType,locatorValue);
			List<WebElement> element=driver.findElements(locator);
			int size=element.size();
			for(WebElement web:element) {
				if(size!=0) {
					if(count==index) {
						web.click();
						break;
					}
				}
				count++;
			}
			return true;
		}catch(Exception ex) {
			System.out.println("error while clicks: "+ex);
			return false;
		}
	}
	
	public static void main(String[] args) {
		SeleniumActions s=new SeleniumActions();
		s.openBrowser("http://www.flipkart.com");
		s.click("xpath", "/html/body/div[2]/div/div/button");
		s.click("xpath", "//*[@id=\"container\"]/div/div[3]/div[3]/div/div/div[2]/div[1]/a/div[1]/div/img");
		s.click("xpath", "/html/body/div[1]/div/div[3]/div[3]/div/div/div[2]/div[1]/a/div[1]/div/img");
		s.closeBrowser("close");
	}

}
