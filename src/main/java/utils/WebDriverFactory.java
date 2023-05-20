package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;

public class WebDriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver(final String nameOfBrowser) {
        if (driver == null) {
            switch (nameOfBrowser.toLowerCase()) {
                case "chrome" -> {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    System.setProperty("webdriver.firefox.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
                    driver = new FirefoxDriver();
                }
                default -> System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
            }
        } else {return driver;}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

            return driver;
    }
}
