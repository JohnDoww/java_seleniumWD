package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CommonPageObjects extends Pages {

    public CommonPageObjects(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@aria-label=\"AUTO.RIA\"]")
    private WebElement headerLogo;

    public WebElement getHeaderLogo() {
        return headerLogo;
    }
}

