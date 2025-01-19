import Base.BaseTest;
import Base.Data;
import Pages.ForgotPasswordPage;
import Pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testfilter.TestPlanV1_0;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    @Test(description = "Şifre sıfırlama işlemleri gerçekleştirilir")
    @Severity(SeverityLevel.CRITICAL)
    public void testForgetPassword() throws InterruptedException {

        loginPage.clickForgotPassword();
        loginPage.sleep(2000);

        forgotPasswordPage.enterUsername(username);
        forgotPasswordPage.clickResetPasswordButton();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, passwordResetLink,"Şifre sıfırlama bağlantısı başarıyla gönderildi.");
        loginPage.sleep(3000);
    }

    @Test(description = "Şifre sıfırlama işlemlemini boş username ile deneme")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithEmptyUsername() throws InterruptedException {

        loginPage.clickForgotPassword();
        loginPage.sleep(3000);

        forgotPasswordPage.enterUsername("");
        forgotPasswordPage.clickResetPasswordButton();

        forgotPasswordPage.isErrorMessageDisplayed();
        screenshot();
        loginPage.sleep(2000);
    }
}