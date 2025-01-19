import Base.BaseTest;
import Pages.HeaderItems;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    HeaderItems item = new HeaderItems();
    LoginPage loginPage = new LoginPage();

    @Test(description = "Kullanıcı çıkış yapma testi")
    public void testDropdownLinks() throws InterruptedException {
        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        item.clickUserDropdown();

        item.clickLogout();
        item.verifyLogoutSuccess();
        loginPage.sleep(1000);
    }
}
