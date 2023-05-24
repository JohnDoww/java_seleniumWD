package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPageElements extends BasePageElement {

    public SearchPageElements(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-search-position=1]//*[@class='ticket-photo loaded']")
    private WebElement openCarFromSearchResults;
    public WebElement getOpenCarFromSearchResults() {
        return openCarFromSearchResults;
    }

    @FindBy(xpath = "//h1[@class='head']")
    private WebElement openCarTitle;
    public WebElement getOpenCarTitle() {
        return openCarTitle;
    }

    @FindBy(xpath = "//*[@data-search-position=1] //*[@class='item unlink']")
    private WebElement firstCarToFavorites;
    public WebElement getFirstCarToFavorites() {
        return firstCarToFavorites;
    }

    @FindBy(xpath = "//h1[@class='head']")
    private WebElement nameOfCarToFavorites;
    public WebElement getNameOfCarToFavorites() {
        return nameOfCarToFavorites;
    }

    @FindBy(xpath = "//*[@class='nav-list_item']/*[@data-type='bu']")
    private WebElement usedCarsList;
    public WebElement getUsedCarsList() {
        return usedCarsList;
    }

    @FindBy(className = "size14 phone_show_link link-dotted mhide")
    private WebElement buttonShowSellerNumber;
    public WebElement getButtonShowSellerNumber() {
        return buttonShowSellerNumber;
    }

    @FindBy(xpath = "//*[@class='list-phone']/div[@class='bold mb-10']")
    private WebElement blockFullMobilePhone;
    public WebElement getBlockFullMobilePhone() {
        return blockFullMobilePhone;
    }










}
