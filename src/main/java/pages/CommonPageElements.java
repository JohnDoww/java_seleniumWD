package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CommonPageElements extends BasePageElement {

    public CommonPageElements(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@title='Use ANY Inbox you want @mailinator.com !']")
    private WebElement emailInputMailinator;

    public WebElement getEmailInputMailinator() {
        return emailInputMailinator;
    }

    @FindBy(className = "primary-btn")
    private WebElement buttonCreateEmail;

    public WebElement getButtonCreateEmail() {
        return buttonCreateEmail;
    }

    @FindBy(linkText = "Увійти в кабінет")
    private WebElement logInSignIn;

    public WebElement getLogInSignIn() {
        return logInSignIn;
    }

    @FindBy(xpath = "//iframe[@id]")
    private WebElement loginFrameRia;

    public WebElement getLoginFrameRia() {
        return loginFrameRia;
    }

    @FindBy(xpath = "//a[contains(text(), 'Зареєструватися')]")
    private WebElement registrationButton;

    public WebElement getRegistrationButton() {
        return loginFrameRia;
    }

    @FindBy(id = "registrationform-name")
    private WebElement nameRegistration;

    public WebElement getNameRegistration() {
        return nameRegistration;
    }

    @FindBy(id = "registrationform-second_name")
    private WebElement secondNameRegistration;

    public WebElement getSecondNameRegistration() {
        return nameRegistration;
    }

    @FindBy(id = "registrationform-email")
    private WebElement inputEmailRegistration;

    public WebElement getInputEmailRegistration() {
        return nameRegistration;
    }

    @FindBy(xpath = "//input[@id='registrationform-agree']")
    private WebElement registrationFormAgree;

    public WebElement getRegistrationFormAgree() {
        return nameRegistration;
    }




}

