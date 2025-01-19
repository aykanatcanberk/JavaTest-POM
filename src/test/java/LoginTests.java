import Base.BaseTest;
import Pages.LoginPage;
import Pages.SideBar;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Test(description = "Geçerli bilgilerle giriş testi")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogin() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.sleep(2000);
        boolean isDashboardVisible = driver.getCurrentUrl().equals(dashboardUrl);

        Assert.assertTrue(isDashboardVisible, "Login işlemi başarısız oldu veya Dashboard'a yönlendirilmedi.");
    }

    @Test(description = "Başarısız giriş testi (yanlış kullanıcı adı veya şifre)")
    @Severity(SeverityLevel.CRITICAL)
    public void testInvalidLogin() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login("wrongUsername", "wrongPassword");
        loginPage.sleep(1000);
        screenshot();
        boolean isErrorVisible = loginPage.isErrorMessageDisplayed();
        Assert.assertFalse(isErrorVisible, "Hata mesajı görüntülendi.");
        loginPage.sleep(2000);
    }

    @Test(description = "Kullanıcı adı veya şifre boş bırakıldığında hata mesajının görüntülenmesi")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyFieldsLogin() throws InterruptedException {

        loginPage.sleep(2000);

        loginPage.login("", "");
        Assert.assertTrue(loginPage.nullErrorMessageDisplayed(), "Username için 'Required' mesajı görüntülenmedi.");
        Assert.assertTrue(loginPage.nullErrorMessageDisplayed(), "Password için 'Required' mesajı görüntülenmedi.");
        screenshot();
        loginPage.sleep(1000);
    }
}
