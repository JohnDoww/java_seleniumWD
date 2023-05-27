import helpers.CommonPageMethods;
import helpers.SearchPageMethods;
import helpers.UserPageMethods;
import org.junit.jupiter.api.DisplayName;

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

    @Test(priority = 1)
    @DisplayName("Check full process of user registration")
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

    @Test(priority = 4)
    @DisplayName("Check car maker in the home page and in the search results page")
    public void checkMakerOnHomeFilterAndSearchPages() {

        String makerTitleFromHomePage = commonPageMethods.searchOldCarHonda();
        String carTitleFromSearch = searchPageMethods.makerTitleInSearchedCar();

        assertThat(carTitleFromSearch).containsIgnoringWhitespaces(makerTitleFromHomePage);
    }

    @Test(priority = 5)
    @DisplayName("Check popAp where will display full mobile phone of seller")
    public void showSellerPhone() {
        commonPageMethods.findAnyCar();
        searchPageMethods.openFirstCarFromSearch();
        searchPageMethods.showSellerMobile();
        String phrase = searchPageElements.getBlockFullMobilePhone().getText();
        assertThat(phrase).containsAnyOf("Телефонуйте, продавець ");
    }

    @Test(priority = 3)
    @DisplayName("Be sure we we opened and appeared on the right article")
    public void compareArticleNameAndTitle() {
        commonPageElements.getNewsButton().click();

        String expectedArticleTitle = commonPageMethods.nameAdvertisedArticle();
        String actualArticleTitle = commonPageMethods.titleOfOpenedArticle();

        assertThat(expectedArticleTitle).containsIgnoringWhitespaces(actualArticleTitle);
    }

    @Test(priority = 2)
    @DisplayName("Add car to favorites page and check the added car in favorites")
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
