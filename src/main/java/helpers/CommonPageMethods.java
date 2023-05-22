package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.CommonPageElements;

public class CommonPageMethods {

    public WebDriver driver;
    CommonPageElements commonPageElements;

    public CommonPageMethods(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
// here method which has to click on the element within frame
    public void fillRegistrationForm1(){
        commonPageElements = new CommonPageElements(this.driver);
        this.driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id]")));
        commonPageElements.getRegistrationButton().click();
    }

}
