package pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	    WebDriver driver;

	    @FindBy(xpath="//div[@id='mainContainer']//div[@class='dtabs']/button[contains(@class,'selected')]")
	    WebElement hpContainerTab;    
	    
	    @FindBy(xpath="//div[@id='imageFilterList']//input[@type='checkbox']")
	    List<WebElement> hpImageFilters;  
	    
	    @FindBy(xpath="//div[@id='imageFilterList']//label//span[not(contains(@class,'tooltip'))]")
	    List<WebElement> hpImageFilterLabels;  
	    
	    @FindBy(xpath="//div[@id='categoriesFilterList']//input[@type='checkbox']/following-sibling::div//label")
	    List<WebElement> hpCategoriesFilterLabels; 
	    
	    @FindBy(xpath="//div[@id='categoriesFilterList']//input[@type='checkbox']/following-sibling::div//label")
	    List<WebElement> hpCategoriesFilters; 
	    
	    @FindBy(xpath="//div[@id='mainContainer']//div[contains(@class,'currentFilterGroup')]/div")
	    List<WebElement> currentFilters; 
	    
	    @FindBy(xpath="//div[@id='mainContainer']//input[@type='checkbox']")
	    List<WebElement> allFiltersChkBoxes;  

	    public HomePage(WebDriver driver){
	        this.driver = driver;	        
	        PageFactory.initElements(driver, this);
	    }   

	    public String getSelectedTabName(){
	         return    hpContainerTab.getText();
        }
	    
	    public int getImageFilterCount(){	    	
	         return    hpImageFilters.size();
       }
	    
	    public String getImageFilterLabels(int index){	    	
	         return    hpImageFilterLabels.get(index).getText();
      }
	    

	    public boolean categoriesFilterExists(String category){
	    	 for (WebElement objLabel : hpCategoriesFilterLabels){ 
	             if (objLabel.getText().equals(category)){ 
	            	 return true; 
	             } 
	         } 
	         return false;
     }  
	
	    
	    public void clickOnFilter(String inputVal){
	    	 for (WebElement objChkbox : allFiltersChkBoxes){ 
	             if (objChkbox.getAttribute("value").equals(inputVal)){
	            	 objChkbox.click();
	             } 
	         } 
	         return;
   }

	    public boolean isAppliedFilterVisible(String filter){
	    	 for (WebElement objDiv : currentFilters){ 
	             if (objDiv.getText().equals(filter)){ 
	            	 return true; 
	             } 
	         } 
	         return false;
    }  
	    
	    public void clickOnCurrentFilterTabs(String filter){
	    	 for (WebElement objDiv : currentFilters){ 
	             if (objDiv.getText().equals(filter)){ 
	            	 objDiv.click();	            	 
	            	 return; 
	             } 
	         } 
	         return;
   }
	    
	    public boolean isFilterChecked(String inputVal){
	    	 for (WebElement objChkbox : allFiltersChkBoxes){ 
	             if (objChkbox.getAttribute("value").equals(inputVal)){ 
	            	 return objChkbox.isSelected(); 
	             } 
	         } 
	         return false;
   }  
	    
}


