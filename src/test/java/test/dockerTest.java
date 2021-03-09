package test;

import org.testng.annotations.Test;

import org.testng.AssertJUnit;
import pageFactory.HomePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class dockerTest {
	
	String driverPath = "webDriver\\geckodriver.exe";    
    WebDriver driver;
    HomePage objHomePage;
    
    
	@BeforeTest
	public void beforeTest() throws InterruptedException {
	  
	  System.setProperty("webdriver.gecko.driver", driverPath);      
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.get("https://hub.docker.com/search");
	    Thread.sleep(5000);
	    objHomePage = new HomePage(driver);
	}
	
  @Test(description="Verify Landing Page",priority=0)
  public void testLandingPage() {	  	
	    AssertJUnit.assertTrue(objHomePage.getSelectedTabName().equals("Containers"));
  }
  
  @Test(description="Verify Image Filter",dependsOnMethods={"testLandingPage"})  
  public void verifyImageFilter() {	  	  	
	    AssertJUnit.assertTrue(objHomePage.getImageFilterCount()==2);
	    AssertJUnit.assertTrue(objHomePage.getImageFilterLabels(0).equals("Verified Publisher"));
	    AssertJUnit.assertTrue(objHomePage.getImageFilterLabels(1).equals("Official Images"));
  }
  
  @Test(description="Verify Category Filter",dependsOnMethods={"verifyImageFilter"})  
  public void verifyCategoryFilter() {	  		  	
	    AssertJUnit.assertTrue(objHomePage.categoriesFilterExists("Analytics"));
	    AssertJUnit.assertTrue(objHomePage.categoriesFilterExists("Base Images"));
	    AssertJUnit.assertTrue(objHomePage.categoriesFilterExists("Databases"));
	    AssertJUnit.assertTrue(objHomePage.categoriesFilterExists("Storage"));
  }
  
  @Test(description="Verify applied Filter",dependsOnMethods={"verifyCategoryFilter"})  
  public void verifyAppliedFilter() {	 
	  	objHomePage.clickOnFilter("store");	  	
	    AssertJUnit.assertTrue(objHomePage.isAppliedFilterVisible("Publisher Content"));	   
  } 
  
  @Test(description="Verify additional Filter",dependsOnMethods={"verifyAppliedFilter"})  
  public void verifyAdditionalFilter() {	 
	  	objHomePage.clickOnFilter("base");
	 	objHomePage.clickOnFilter("database");
	    AssertJUnit.assertTrue(objHomePage.isAppliedFilterVisible("Base Images"));	   
	    AssertJUnit.assertTrue(objHomePage.isAppliedFilterVisible("Databases"));
  } 
  
  @Test(description="Verify additional Filter",dependsOnMethods={"verifyAdditionalFilter"})  
  public void verifyClosedFilter() {	 
	  	objHomePage.clickOnCurrentFilterTabs("Base Images");	 	
	    AssertJUnit.assertFalse(objHomePage.isFilterChecked("base")); 
 } 

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
