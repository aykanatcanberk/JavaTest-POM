package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v129.page.Page;
import org.testng.Assert;

public class ForgotPasswordPage extends BaseTest {

    private By usernameField = By.name("username");
    private By resetButton = By.xpath("//button[contains(@class, 'orangehrm-forgot-password-button--reset')]");
    private By errorMessage = By.xpath("//span[contains(@class, 'oxd-input-field-error-message') and text()='Required']");

    @Step("Kullanıcı adı '{username}' doğru bir şekilde girilir")
    public void enterUsername(String username) {
        WebElement usernameInput = driver.findElement(usernameField);
        usernameInput.sendKeys(username);
    }

    @Step("Şifre sıfırlama butonuna tıklanır.")
    public void clickResetPasswordButton() {
        WebElement resetPasswordButton = driver.findElement(resetButton);
        resetPasswordButton.click();
    }

    @Step("Şifre sıfırlama işleminde hata var mı?")
    public void isErrorMessageDisplayed() {
        WebElement error = driver.findElement(errorMessage);
        Assert.assertTrue(error.isDisplayed(), "Kullanıcı adı boşken 'Required' hatası görüntülendi.");
    }
}
