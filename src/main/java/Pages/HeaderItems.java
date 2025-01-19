package Pages;

import Base.BaseLibrary;
import Base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class HeaderItems extends BaseTest {
    private By userDropdown = By.cssSelector("span.oxd-userdropdown-tab");
    private By changePasswordLink = By.xpath("//a[@href='/web/index.php/pim/updatePassword']");
    private By logoutLink = By.xpath("//a[@class='oxd-userdropdown-link' and @href='/web/index.php/auth/logout']");

    @Step("Kullanıcı açılır menüsüne tıklanır")
    public void clickUserDropdown() {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
        dropdownElement.click();
    }

    @Step("Şifre değiştirme bağlantısına tıklanır")
    public void clickChangePassword() {
        WebElement changePasswordElement = wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink));
        changePasswordElement.click();
    }

    @Step("Çıkış bağlantısına tıklanır")
    public void clickLogout() {
        WebElement logoutElement = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutElement.click();
    }

    @Step("Çıkış başarılı mı kontrol edilir")
    public void verifyLogoutSuccess() {
        wait.until(ExpectedConditions.urlToBe(url));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, url, "Logout işlemi başarısız.");
    }
}
