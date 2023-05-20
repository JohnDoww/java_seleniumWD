import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.CommonPageObjects;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;

import static org.testng.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;
import static utils.WebDriverFactory.getDriver;

public class HomePageTest extends MainTest {

    WebDriver driver;
    CommonPageObjects commonPageObjects;


//    public void clickOn ( WebElement element, int timeout){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }


    @BeforeClass
    public void setUp() {
    }

    @BeforeMethod
    public void setUp1() {
        driver = getDriver("chrome");
        commonPageObjects = new CommonPageObjects(driver);
        driver.get("https://auto.ria.com/uk/");
    }

    @Test(priority = 1)
    public void test() {
//        assertTrue(CommonPageObjects.get.isDisplayed(), "we have one"); // test NG assert
        WebElement nameLogo = driver.findElement(By.xpath("//a[@aria-label=\"AUTO.RIA\"]"));
        String name = nameLogo.getCssValue("aria-label");
//        assertThat(nameLogo).isEqualTo(name);        // there we use assert J


        SoftAssert checkButtons =new SoftAssert();
        checkButtons.assertEquals("buy car","buy car", "we can't find buy car");
    }

    @Test
    public void registration(){
        driver.get("https://www.mailinator.com/v4/public/inboxes.jsp");
        WebElement emailInputArea = driver.findElement(By.xpath("//*[@title=\"Use ANY Inbox you want @mailinator.com !\"]"));
        WebElement createEmail = driver.findElement(By.className("primary-btn"));

        String generateMailName = "saho"+(int)(Math.random()*1000);
        String testMail =generateMailName+"@mailinator.com";

        emailInputArea.click();
        emailInputArea.sendKeys(generateMailName);
        createEmail.click();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://auto.ria.com/uk/");
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        WebElement logInSignIn = driver.findElement(By.linkText("Увійти в кабінет"));
        logInSignIn.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        //////////here I have a trouble

        WebElement registrationButton = driver.findElement(By.xpath("//a[contains(text(), \'Зареєструватися \')] "));
        registrationButton.click();
        //////////////////

        WebElement nameRegistration = driver.findElement(By.className("registrationform-name"));
        nameRegistration.sendKeys("Petro");
        WebElement secondNameRegistration = driver.findElement(By.className("registrationform-second_name"));
        secondNameRegistration.sendKeys("Mostapchuk");
        WebElement setEmailRegistration = driver.findElement(By.className("registrationform-email"));
        setEmailRegistration.sendKeys(testMail);
        WebElement registrationFormAgree = driver.findElement(By.className("registrationform-agree"));
        registrationFormAgree.click();


//        driver.switchTo().window(tabs.get(1));
//        driver.switchTo().window(tabs.get(0));

        //Get text:
    }

    @Test
    public void findCarHonda(){
//        WebElement choseCarMaker = driver.findElement(By.xpath("//input[@aria-label=\"Пошук Марка\"]"));
//        Select choseCarMaker = new Select(driver.findElement(By.xpath("//input[@aria-label=\"Пошук Марка\"]")))


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
