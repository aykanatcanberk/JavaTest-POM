package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class PimPage extends BaseLibrary {

    LoginPage loginPage = new LoginPage();

    private By addButton = By.cssSelector("button.oxd-button--secondary > i.bi-plus");
    private By firstNameField = By.name("firstName");
    private By middleNameField = By.name("middleName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.xpath("//button[contains(@class, 'oxd-button--secondary') and text()=' Save ']");
    private By errorMessage = By.cssSelector("span.oxd-input-field-error-message");
    private By checkBox = By.cssSelector("span.oxd-checkbox-input");
    private By deleteButton = By.cssSelector("button.oxd-button.oxd-button--medium.oxd-button--label-danger");
    private By confirmDeleteButton = By.xpath("//button[contains(@class, 'oxd-button--label-danger') and text()=' Yes, Delete ']");
    private By successMessage = By.xpath("//div[contains(@class, 'oxd-toast--success')]//p[contains(@class, 'oxd-text--toast-title') and text()='Success']");

    @Step("PIM modülünde Add butonuna tıklanır ve Çalışan ekleme sayfasına gidilir.")
    public void clickAddButton() {
        WebElement addBtn = driver.findElement(addButton);
        addBtn.click();
    }

    @Step("Çalışanı eklemek için İsim, İkinci İsim ve Soyad girilir")
    public void enterFullName(String firstName, String middleName, String lastName) {
        WebElement firstNameElement = driver.findElement(firstNameField);
        WebElement middleNameElement = driver.findElement(middleNameField);
        WebElement lastNameElement = driver.findElement(lastNameField);

        firstNameElement.sendKeys(firstName);
        middleNameElement.sendKeys(middleName);
        lastNameElement.sendKeys(lastName);
    }

    @Step("Save butonuna tıklanır")
    public void clickSaveButton() {
        WebElement saveBtn = driver.findElement(saveButton);
        saveBtn.click();
    }

    @Step("PIM sayfası başarıyla kaydedildi mi?")
    public boolean isRedirectedToPimPage() {
        return driver.getCurrentUrl().contains(pimRedirectURL);
    }

    @Step("First Name ve Last Name için boş karakter kontrolü")
    public boolean isErrorMessageDisplayed() {
        WebElement errorElement = driver.findElement(errorMessage);
        return errorElement.isDisplayed() && errorElement.getText().equals("Required");
    }

    @Step("Eğer veri mevcutsa silmek için seçilir")
    public void selectAllRecords() {
        WebElement checkBoxElement = driver.findElement(checkBox);
        if (!checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }
        else{
            System.out.println("Silinecek veri bulunamadı.");
        }

        WebElement checkboxIcon = driver.findElement(checkBox);
        assert checkboxIcon.isDisplayed();
    }

    @Step("Seçilen verilen silme işlemi onaylanır.")
    public void deleteSelectedRecords() throws InterruptedException {

        WebElement deleteButtonElement = driver.findElement(deleteButton);
        deleteButtonElement.click();
        loginPage.sleep(2000);

        WebElement confirmDeleteButtonElement = driver.findElement(confirmDeleteButton);
        confirmDeleteButtonElement.click();
        loginPage.sleep(2000);

        WebElement successMessageElement = driver.findElement(successMessage);
        Assert.assertTrue(successMessageElement.isDisplayed(), "Delete işlemi başarısız oldu!");
        System.out.println("Delete işlemi başarılı.");


    }



}
