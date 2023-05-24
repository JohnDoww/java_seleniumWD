package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static utils.PropertiesFactory.getProperty;

public class WebDriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver(final String nameOfBrowser) {
        if (driver == null) {
            switch (nameOfBrowser.toLowerCase()) {
                case "chrome" -> {
                    System.setProperty("webdriver.chrome.driver", getProperty ("path_to_chromedriver"));
                    driver = new ChromeDriver();
                }
                case "firefox" -> {
                    System.setProperty("webdriver.firefox.driver", getProperty ("path_to_firefoxdriver"));
                    driver = new FirefoxDriver();
                }
                default -> System.setProperty("webdriver.chrome.driver", getProperty ("path_to_chromedriver"));
            }
        } else {return driver;}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

            return driver;
    }
}
