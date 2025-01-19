import Base.BaseTest;
import Base.Data;
import Pages.HeaderItems;
import Pages.LoginPage;
import Pages.UpdatePasswordPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testfilter.TestPlanV1_0;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePasswordTests extends BaseTest{

    HeaderItems item = new HeaderItems();
    LoginPage loginPage = new LoginPage();
    UpdatePasswordPage passwordUpdatePage = new UpdatePasswordPage();

    @Test(description = "Başarılı şifre güncelleme testi")
    @Severity(SeverityLevel.CRITICAL)
    public void testPasswordUpdate() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("NewPassword123!");
        passwordUpdatePage.confirmPassword("NewPassword123!");
        passwordUpdatePage.clickSaveButton();

        passwordUpdatePage.verifyPasswordUpdateSuccess();
    }

    @Test(description = "Şifre değiştirilirken yanlış güncel şifre girişi")
    @Severity(SeverityLevel.CRITICAL)
    public void testIncorrectCurrentPassword() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword("WrongCurrentPassword123");
        passwordUpdatePage.enterNewPassword("ValidNewPassword123");
        passwordUpdatePage.confirmPassword("ValidNewPassword123");

        loginPage.sleep(1000);
        passwordUpdatePage.clickSaveButton();
        screenshot();

        String actualError = passwordUpdatePage.getToastErrorMessage();
        loginPage.sleep(2000);
        Assert.assertEquals(actualError, "Error", "Expected error toast message not displayed!");
    }

    @Test(description = "Şifre 64 karakterden fazla olmamalı.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordExceedsMaxLength() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("a".repeat(65));
        passwordUpdatePage.confirmPassword("a".repeat(65));
        passwordUpdatePage.clickSaveButton();
        screenshot();
        Assert.assertEquals(passwordUpdatePage.getErrorMessage(), "Should not exceed 64 characters");
        loginPage.sleep(2000);
    }

    @Test(description = "Şifre en az 7 karakter olmalı.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordBelowMinLength() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("abc");
        passwordUpdatePage.confirmPassword("abc");
        passwordUpdatePage.clickSaveButton();
        loginPage.sleep(1000);

        screenshot();
        Assert.assertEquals(passwordUpdatePage.getErrorMessage(), "Should have at least 7 characters");
        loginPage.sleep(2000);
    }

    @Test(description = "Yeni şifre onaylanırken şifreler eşleşmeli.")
    @Severity(SeverityLevel.CRITICAL)
    public void testPasswordsDoNotMatch() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("ValidPassword123");
        passwordUpdatePage.confirmPassword("DifferentPassword123");
        passwordUpdatePage.clickSaveButton();
        screenshot();
        Assert.assertEquals(passwordUpdatePage.getErrorMessage(), "Passwords do not match");
        loginPage.sleep(2000);
    }

    @Test(description = "Şifre en az 1 sayı içermeli.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordRequiresNumber() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("PasswordWithoutNumbers");
        passwordUpdatePage.confirmPassword("PasswordWithoutNumbers");
        passwordUpdatePage.clickSaveButton();

        loginPage.sleep(1000);
        screenshot();

        Assert.assertEquals(passwordUpdatePage.getErrorMessage(), "Your password must contain minimum 1 number");
        loginPage.sleep(2000);
    }

    @Test(description = "Şifre boşluk içermemeli.")
    @Severity(SeverityLevel.NORMAL)
    public void testPasswordShouldNotContainSpaces() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();
        item.clickChangePassword();

        passwordUpdatePage.enterCurrentPassword(password);
        passwordUpdatePage.enterNewPassword("Password WithSpaces123");
        passwordUpdatePage.confirmPassword("Password WithSpaces123");
        passwordUpdatePage.clickSaveButton();
        loginPage.sleep(1000);
        screenshot();

        Assert.assertEquals(passwordUpdatePage.getErrorMessage(), "Your password should not contain spaces");
        loginPage.sleep(2000);
    }

}
