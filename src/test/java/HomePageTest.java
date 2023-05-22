import helpers.CommonPageMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CommonPageElements;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;
import static utils.WebDriverFactory.getDriver;

public class HomePageTest extends MainTest {

    WebDriver driver;
    CommonPageElements commonPageElements;
    CommonPageMethods commonPageMethods;


    public void clickOn(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    @BeforeClass
    public void setUp() {
    }

    @BeforeMethod
    public void setUp1() {
        driver = getDriver("chrome");
        commonPageElements = new CommonPageElements(driver);
        commonPageMethods = new CommonPageMethods(driver);
        driver.get("https://auto.ria.com/uk/");
    }

    @Test(priority = 1)
    public void test() {
//        assertTrue(CommonPageObjects.get.isDisplayed(), "we have one"); // test NG assert
        WebElement nameLogo = driver.findElement(By.xpath("//a[@aria-label=\"AUTO.RIA\"]"));
        String name = nameLogo.getCssValue("aria-label");
//        assertThat(nameLogo).isEqualTo(name);        // there we use assert J


        SoftAssert checkButtons = new SoftAssert();
        checkButtons.assertEquals("buy car", "buy car", "we can't find buy car");
    }

    @Test
    public void registration() throws InterruptedException {
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp");
//        WebElement createEmail = driver.findElement(By.className("primary-btn"));
        String generateMailName = "sahopaho" + (int) (Math.random() * 10000);
        String testMail = generateMailName + "@mailinator.com";
        commonPageElements.getEmailInputMailinator().click();
        commonPageElements.getEmailInputMailinator().sendKeys(generateMailName);
        commonPageElements.getButtonCreateEmail().click();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://auto.ria.com/uk/");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//        WebElement logInSignIn = driver.findElement(By.linkText("Увійти в кабінет"));
        commonPageElements.getLogInSignIn().click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");


        // trouble place where I try to use method where I provided driver.switchToFrame but it doesn't work
//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id]")));
//        WebElement registrationButton = driver.findElement(By.xpath("//a[contains(text(), 'Зареєструватися')]"));
//        registrationButton.click();
        commonPageMethods.fillRegistrationForm1();
        ///////////////


        String userName = "Serho";
        String userSecondName = "Allho";
//        WebElement nameRegistration = driver.findElement(By.id("registrationform-name"));
//        CommonPageElements.driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id]")));
        commonPageElements.getNameRegistration().sendKeys(userName);
//        WebElement secondNameRegistration = driver.findElement(By.id("registrationform-second_name"));
        commonPageElements.getSecondNameRegistration().sendKeys(userSecondName);
//        WebElement setEmailRegistration = driver.findElement(By.id("registrationform-email"));
        commonPageElements.getInputEmailRegistration().sendKeys(testMail);
//        WebElement registrationFormAgree = driver.findElement(By.xpath("//input[@id='registrationform-agree']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(commonPageElements.getRegistrationFormAgree()).click().perform();

        WebElement submitRegistrationForm = driver.findElement(By.xpath("//*[@type='submit']"));
        submitRegistrationForm.click();
        driver.switchTo().defaultContent();

        driver.switchTo().window(tabs.get(0));
        WebElement letterConfirm = driver.findElement(By.xpath("//td[contains(text(), 'RIA')]"));
        letterConfirm.click();
        driver.switchTo().frame(driver.findElement(By.id("html_msg_body")));
        WebElement codeFromEmailLetter = driver.findElement(By.xpath("//h2[@style='background: #f3f3f3']"));
        String codeToRegistration = codeFromEmailLetter.getText();
        driver.switchTo().defaultContent();

        driver.switchTo().window(tabs.get(1));
        driver.switchTo().frame(driver.findElement(By.id("login_frame")));
        WebElement inputCodeFromEmail = driver.findElement(By.id("secure_code"));
        inputCodeFromEmail.sendKeys(codeToRegistration);

        String userPassword = "Qwerty" + (int) (Math.random() * 200000);
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='new_pass']"));
        WebElement confirmPassword = driver.findElement(By.xpath("//input[@id='confirm_new']"));
        inputPassword.sendKeys(userPassword);
        confirmPassword.sendKeys(userPassword);
        WebElement submitRegistration = driver.findElement(By.tagName("button"));
        submitRegistration.click();
        WebElement userInfo = driver.findElement(By.xpath("//*[@class='unlink name']"));
        assertThat(userName + userSecondName).containsIgnoringWhitespaces(userInfo.getText());
    }


    @Test
    public void findCarHonda() {
        WebElement inputCarMaker = driver.findElement(By.id("brandTooltipBrandAutocomplete-brand"));
        WebElement hondaList = driver.findElement(By.xpath("//li[@data-value=\"28\"]"));

        inputCarMaker.click();
        hondaList.click();

        WebElement inputCarYear = driver.findElement(By.xpath("//*[@class=\"m-rows e-year\"]"));
        inputCarYear.click();

        Select inputCarYearFrom = new Select(driver.findElement(By.id("yearFrom")));
        Select inputCarYearTo = new Select(driver.findElement(By.id("yearTo")));
        inputCarYearTo.selectByVisibleText("1999");
        inputCarYearFrom.selectByVisibleText("1989");


        WebElement buttonSearchCar = driver.findElement(By.cssSelector("button[type*=\"submit\"]"));
        WebElement sda = driver.findElement(By.xpath("//*[@class=\"wrapping\"]"));

        sda.click();
        buttonSearchCar.click();
        int orderCar = (int) (Math.random() * 5);
        WebElement openCar = driver.findElement(By.xpath("//*[@data-search-position=\"" + orderCar + "\"] //*[@class=\"ticket-photo loaded\"]"));
        openCar.click();

        WebElement carPage = driver.findElement(By.className("auto-wrap"));
//        assertThat(carPage)


    }

    //HW
//- TestNG
//1 use annotation Paramitrized test for login test with wrong and correct data
//2 use @RepeatedTest("how many times we will run")
//3 use @DisplayName
//4 use @Before and after each
//5 before/after all
//6 timeOut
//
//
//https://auto.ria.com/uk/
//
//1 follow to the donate to Ukraine page
//2 Search auto honda ... ..
//3 Registration a new user + add generator email services + password --- priority 1
//4 Add car to the favourites
//5 sell auto / ask to seller
//6 Copy link and open the link in new tab
    @AfterClass
    public void treatDown() {

    }

    @AfterMethod
    public void treat1Down() {
        driver.quit();
    }

}
