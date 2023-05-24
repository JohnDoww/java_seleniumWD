package helpers;

import org.openqa.selenium.WebDriver;
import pages.UserPageElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PropertiesFactory.getProperty;


public class UserPageMethods extends BasePageMethods {

    WebDriver driver;
    UserPageElements userPageElements;

    public UserPageMethods (WebDriver driver, UserPageElements userPageElements) {
        super(driver);
        this.driver = driver;
        this.userPageElements = userPageElements;
    }

    /**
     * This method returns the name of car from favorites list
     */
    public String titleFirstCarFromFavorites(){
        userPageElements.getOpenFavorites().click();
        return userPageElements.getLastCarAddedToFavorites().getAttribute("title");
    }
}
