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

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.PropertiesFactory.getProperty;
import static utils.WebDriverFactory.getDriver;

public class HomePageTest extends MainTest {

    WebDriver driver;
    CommonPageElements commonPageElements;
    CommonPageMethods commonPageMethods;
    UserPageElements userPageElements;
    UserPageMethods userPageMethods;
    SearchPageElements searchPageElements;
    SearchPageMethods searchPageMethods;

    @BeforeClass
    public void setUp() {
    }

    @BeforeMethod
    public void setUp1() {

        driver = getDriver("chrome");
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
    public void showSellerPhone(){
        commonPageMethods.findAnyCar();
        searchPageMethods.openFirstCarFromSearch();
        searchPageMethods.showSellerMobile();
        String phrase = searchPageElements.getBlockFullMobilePhone().getText();
        assertThat(phrase).contains(" Телефонуйте, продавець ");
    }

    @Test(priority = 3)
    @DisplayName("Be sure we have the opportunity to make a donate")
    public void opportunityToMakeDonate() {

        commonPageElements.getPromotionTitle().click();
        String donatAmount = commonPageMethods.makeDonate();
        String existAmount = commonPageElements.getInputDonateArea().getText();

        assertThat(donatAmount).containsIgnoringWhitespaces(existAmount);
    }

    @Test (priority = 2)
    @DisplayName("Add car to favorites page and check the added car in favorites")
    public void addCarToFavoritesAndCheckFavorites() {

        commonPageMethods.logIn();
        String nameCarToFavorites = searchPageMethods.addBuCarToFavorites();
        String carFromFavorites = userPageMethods.titleFirstCarFromFavorites();

        assertThat(nameCarToFavorites).containsIgnoringWhitespaces(carFromFavorites);
    }

    @AfterClass
    public void treatDown() {
        driver.quit();
    }

    @AfterMethod
    public void treat1Down() {
//        this.driver.quit();
    }
    //HW
//- TestNG
//1 use annotation Paramitrized test for login test with wrong and correct data
//2 use @RepeatedTest("how many times we will run")

//3 use @DisplayName


}
