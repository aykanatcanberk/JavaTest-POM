import Base.BaseTest;
import Pages.LoginPage;
import Pages.PimPage;
import Pages.SideBar;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PimTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    SideBar sideBar = new SideBar();
    PimPage pimPage = new PimPage();

    @Test(description = "PIM modülüne tıklama ve yeni çalışan kaydetme testi")
    @Severity(SeverityLevel.NORMAL)
    public void testAddEmployee() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickPimModule();
        loginPage.sleep(2000);

        pimPage.clickAddButton();
        loginPage.sleep(2000);

        pimPage.enterFullName(firstName, middleName, lastName);
        pimPage.clickSaveButton();
        loginPage.sleep(5000);

        boolean isRedirected = pimPage.isRedirectedToPimPage();
        Assert.assertTrue(isRedirected, "Yeni çalışan kaydedilemedi.");
    }

    @Test(description = "First Name veya Last Name boş bırakıldığında kayıt işlemi gerçekleşiyor mu?")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyFieldsErrorMessages() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickPimModule();
        loginPage.sleep(2000);

        pimPage.clickAddButton();
        loginPage.sleep(2000);


        pimPage.enterFullName("", "", "");
        pimPage.clickSaveButton();
        loginPage.sleep(2000);


        boolean isFirstNameErrorDisplayed = pimPage.isErrorMessageDisplayed();
        boolean isLastNameErrorDisplayed = pimPage.isErrorMessageDisplayed();
        screenshot();

        Assert.assertTrue(isFirstNameErrorDisplayed, "First Name için hata mesajı gösterilmedi.");
        Assert.assertTrue(isLastNameErrorDisplayed, "Last Name için hata mesajı gösterilmedi.");
    }

    @Test(description = "İşçi kayıtlarının silinmesi testi")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteWorkerRecord() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickPimModule();
        loginPage.sleep(2000);

        pimPage.selectAllRecords();
        loginPage.sleep(4000);

        pimPage.deleteSelectedRecords();
        screenshot();
        loginPage.sleep(1000);
    }
}
