package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseLibrary {

    private By usernameField =  By.cssSelector("input[name='username']");
    private By passwordField = By.cssSelector("input[name='password']");
    private By loginButton = By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--main.orangehrm-login-button");
    private By errorMessage = By.xpath("//div[contains(@class, 'oxd-alert-content') and contains(text(), 'Invalid credentials')]");
    private By nullCheckMessage = By.xpath("//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']");
    private By forgotPasswordText = By.cssSelector("p.orangehrm-login-forgot-header");

    @Step("Kullanıcı adı '{username}' girilir")
    public void enterUsername(String username) {
        WebElement usernameElement = driver.findElement(usernameField);
        usernameElement.sendKeys(username);
    }

    @Step("Şifre '{password}' girilir")
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.sendKeys(password);
    }

    @Step("Login butonuna tıklanır")
    public void clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButton);
        loginBtn.click();
    }

    @Step("Kullanıcı '{username}' ve şifre '{password}' ile giriş yapar")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    @Step("Hata mesajı görüntüleniyor mu?")
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement errorElement = driver.findElement(errorMessage);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Username ya da Password alanı için 'Required' mesajı görünüyor mu?")
    public boolean nullErrorMessageDisplayed() {
        try {
            WebElement errorElement = driver.findElement(nullCheckMessage);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Şifremi unuttum sayfasına yönlendirir")
    public void clickForgotPassword() {
        WebElement forgotPasswordElement = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordText));
        forgotPasswordElement.click();
    }
}
