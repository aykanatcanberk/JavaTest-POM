package Pages;

import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class UpdatePasswordPage extends BaseTest {

    private By currentPasswordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/input");
    private By newPasswordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private By confirmPasswordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private By saveButton = By.xpath("//button[text()=' Save ']");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast--success')]//p[contains(@class, 'oxd-text--toast-title') and text()='Success']");
    private By errorMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
    private By errorToastMessage = By.xpath("//p[text()='Error']");

    @Step("Mevcut şifreyi gir: {0}")
    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(currentPasswordField));
        currentPasswordInput.sendKeys(currentPassword);
    }

    @Step("Yeni şifreyi gir: {0}")
    public void enterNewPassword(String newPassword) {
        WebElement newPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(newPasswordField));
        newPasswordInput.sendKeys(newPassword);
    }

    @Step("Yeni şifreyi onayla: {0}")
    public void confirmPassword(String confirmPassword) {
        WebElement confirmPasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    @Step("Kaydet butonuna tıkla")
    public void clickSaveButton() {
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveBtn.click();
    }

    @Step("Şifre güncelleme başarılı mı kontrol et")
    public void verifyPasswordUpdateSuccess() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        Assert.assertTrue(successMsg.isDisplayed(), "Başarılı mesaj görüntülenemedi.");
    }

    @Step("Şifre değiştirme işleminde hata var")
    public String getErrorMessage() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return error.getText();
    }

    @Step("Şifre değiştirme işleminde hata var")
    public String getToastErrorMessage() {
        WebElement errorToast = wait.until(ExpectedConditions.visibilityOfElementLocated(errorToastMessage));
        return errorToast.getText();
    }
}
