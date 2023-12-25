import helpers.CommonPageMethods;
import helpers.SearchPageMethods;
import helpers.UserPageMethods;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WindowType;

import org.testng.annotations.*;
import pages.CommonPageElements;
import pages.SearchPageElements;
import pages.UserPageElements;
import utils.WebDriverFactory;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.PropertiesFactory.getProperty;


public class HomePageTest {

    private WebDriver driver;
    private CommonPageElements commonPageElements;
    private CommonPageMethods commonPageMethods;
    private UserPageElements userPageElements;
    private UserPageMethods userPageMethods;
    private SearchPageElements searchPageElements;
    private SearchPageMethods searchPageMethods;
    private final WebDriverFactory webDriverFactory = WebDriverFactory.getInstance();


    @BeforeMethod
    public void setUp1() {

        driver = webDriverFactory.openBrowser("chrome");
        driver.get(getProperty("auto_ria_url"));
        commonPageElements = new CommonPageElements(driver);
        commonPageMethods = new CommonPageMethods(driver, commonPageElements);
        userPageElements = new UserPageElements(driver);
        userPageMethods = new UserPageMethods(driver, userPageElements);
        searchPageElements = new SearchPageElements(driver);
        searchPageMethods = new SearchPageMethods(driver, searchPageElements);
    }

    @Test(priority = 1, description = "Pass registration flow")
    public void checkRegistrationFlow() {

        commonPageMethods.createTestEmail();
        driver.switchTo().newWindow(WindowType.TAB);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        commonPageMethods.fillRegistrationForm1();
        driver.switchTo().window(tabs.get(0));
        String codeFromEmail = commonPageMethods.receiveRegistrationCode();

        driver.switchTo().window(tabs.get(1));

        commonPageMethods.fillRegistrationForm2(codeFromEmail);

        String registrationFullName = commonPageMethods.userFullName;
        String fullNamInProfile = userPageElements.getUserInfo().getText();

        assertThat(registrationFullName).containsIgnoringWhitespaces(fullNamInProfile);
    }

    @Test(priority = 4, description = "The Maker displayed correctly into search results when using home page filter")
    public void checkMakerOnHomeFilterAndSearchPages() {

        String makerTitleFromHomePage = commonPageMethods.searchOldCarHonda();
        String carTitleFromSearch = searchPageMethods.makerTitleInSearchedCar();

        assertThat(carTitleFromSearch).containsIgnoringWhitespaces(makerTitleFromHomePage);
    }

    @Test(priority = 5, description = "Seller telephone is displayed")
    public void showSellerPhone() {
        commonPageMethods.findAnyCar();
        searchPageMethods.openFirstCarFromSearch();
        searchPageMethods.showSellerMobile();
        String phrase = searchPageElements.getBlockFullMobilePhone().getText();
        assertThat(phrase).containsAnyOf("Телефонуйте, продавець ");
    }

    @Test(priority = 3, description = "Article title inside and outside of article are the same")
    public void compareArticleNameAndTitle() {
        commonPageElements.getNewsButton().click();

        String expectedArticleTitle = commonPageMethods.nameAdvertisedArticle();
        String actualArticleTitle = commonPageMethods.titleOfOpenedArticle();

        assertThat(expectedArticleTitle).containsIgnoringWhitespaces(actualArticleTitle);
    }

    @Test(priority = 2, description = "The Car added to the favorites list")
    public void addCarToFavoritesAndCheckFavorites() {
        commonPageElements.getButtonBuCar().click();
        String nameCarToFavorites = searchPageMethods.addBuCarToFavorites();
        String carFromFavorites = userPageMethods.titleFirstCarFromFavorites();

        assertThat(nameCarToFavorites).containsIgnoringWhitespaces(carFromFavorites);
    }

    @AfterMethod
    public void treat1Down() {
        driver.quit();
    }
}