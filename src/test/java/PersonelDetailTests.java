import Base.BaseLibrary;
import Base.BaseTest;
import Pages.BuzzPage;
import Pages.LoginPage;
import Pages.PersonelDetailPage;
import Pages.SideBar;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PersonelDetailTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    PersonelDetailPage personelDetailPage = new PersonelDetailPage();
    SideBar sideBar = new SideBar();

    @Test(description = "Personel bilgilerini güncelleme")
    @Severity(SeverityLevel.CRITICAL)
    public void updatePersonelInfo() throws InterruptedException {
        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickMyInfoModule();

        personelDetailPage.fillPersonelInfo();

        personelDetailPage.enterfirstName("AA");
        personelDetailPage.entermiddleName("BB");
        personelDetailPage.enterlastName("CC");
        loginPage.sleep(2000);

        personelDetailPage.savePersonelInfo();
        boolean isSuccessDisplayed = personelDetailPage.isSuccessMessageDisplayed();
        assertTrue(isSuccessDisplayed, "Success message not displayed after saving personnel details");
        loginPage.sleep(2000);
    }



}
