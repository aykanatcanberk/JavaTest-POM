import Base.BaseTest;
import Pages.LoginPage;
import Pages.SideBar;
import Pages.BuzzPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuzzTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    BuzzPage buzzPage = new BuzzPage();
    SideBar sideBar = new SideBar();

    @Test(description = "Buzz sayfasında post paylaşma testi")
    @Severity(SeverityLevel.NORMAL)
    public void testSharePost() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickBuzzModule();
        loginPage.sleep(2000);

        buzzPage.sharePost(postContent);
        loginPage.sleep(2000);

        boolean isPostShared = driver.getPageSource().contains(postContent);
        Assert.assertTrue(isPostShared, "Post başarıyla paylaşılmadı.");
        loginPage.sleep(2000);
    }

    @Test(description = "Buzz sayfasında paylaşılan postu güncelleme")
    @Severity(SeverityLevel.NORMAL)
    public void testUpdatePost() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickBuzzModule();
        loginPage.sleep(2000);

        buzzPage.clickEditPostButton();
        buzzPage.clickEditOption();

        buzzPage.updatePostContent(updatedContent);

        buzzPage.submitUpdatedPost();
        loginPage.sleep(2000);
        boolean isPostUpdated = driver.getPageSource().contains(updatedContent);
        Assert.assertTrue(isPostUpdated, "Post güncellenmedi.");
        loginPage.sleep(2000);

        String successMessage = driver.findElement(By.xpath("//div[contains(@class, 'oxd-toast-container')]")).getText();
        Assert.assertFalse(successMessage.contains("Post successfully updated"), "Post güncellenirken hata oluştu.");

    }

    @Test(description = "Buzz sayfasında paylaşılan postu silme işlemi")
    @Severity(SeverityLevel.NORMAL)
    public void testDeletePost() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickBuzzModule();
        loginPage.sleep(2000);

        buzzPage.clickEditPostButton();
        loginPage.sleep(1000);

        buzzPage.clickDeleteOption();
        buzzPage.clickDeletePost();

        boolean isDeleted = buzzPage.isDeleteSuccessful();
        Assert.assertTrue(isDeleted, "Post silinirken hata oluştu.");

        loginPage.sleep(2000);

    }

    @Test(description = "Buzz sayfasında paylaşılan postu beğenme ya da beğeniyi çekme")
    @Severity(SeverityLevel.NORMAL)
    public void testLikeButton() throws InterruptedException {

        loginPage.sleep(2000);
        loginPage.login(username, password);
        loginPage.sleep(2000);

        sideBar.clickBuzzModule();
        loginPage.sleep(2000);

        String initialLikeCount = buzzPage.getLikeCount();

        buzzPage.clickLikeButton();
        loginPage.sleep(2000);

        String updatedLikeCount = buzzPage.getLikeCount();

        Assert.assertEquals(initialLikeCount, updatedLikeCount, "Beğeni sayısı değişmedi.Buton çalışmıyor.");
    }
}
