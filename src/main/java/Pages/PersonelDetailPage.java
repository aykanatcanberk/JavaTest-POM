package Pages;

import Base.BaseLibrary;
import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PersonelDetailPage extends BaseTest {

    LoginPage loginPage = new LoginPage();
    By personalDetailsLink = By.xpath("//a[contains(@href, '/web/index.php/pim/viewPersonalDetails/empNumber/7')]");
    private By firstName = By.className("orangehrm-firstname");
    private By middleName = By.className("orangehrm-middlename");
    private By lastName = By.className("orangehrm-lastname");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast--success')]//p[contains(@class, 'oxd-text--toast-title') and text()='Success']");

    @Step("Adı '{fName}' girilir")
    public void enterfirstName(String fName) {
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(firstName));

        firstNameInput.clear();
        firstNameInput.sendKeys(fName);
    }

    @Step("İkinci ad '{mName}' girilir")
    public void entermiddleName(String mName) {
        WebElement middleNameInput = wait.until(ExpectedConditions.elementToBeClickable(middleName));

        middleNameInput.clear();
        middleNameInput.sendKeys(mName);
    }

    @Step("Soyad '{lName}' girilir")
    public void enterlastName(String lName) throws InterruptedException {
        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(lastName));

        lastNameInput.clear();
        loginPage.sleep(2000);
        lastNameInput.sendKeys(lName);
    }

    @Step("Personel bilgileri doldurulur")
    public void fillPersonelInfo() {
        WebElement personalDetailsElement = wait.until(ExpectedConditions.elementToBeClickable(personalDetailsLink));
        personalDetailsElement.click();
    }

    @Step("Personel bilgileri kaydedilir")
    public void savePersonelInfo() {
        List<WebElement> buttons = driver.findElements(By.className("oxd-button--secondary"));

        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    @Step("Başarı mesajı kontrol edilir")
    public boolean isSuccessMessageDisplayed() {
        try {
            WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return successToast.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}