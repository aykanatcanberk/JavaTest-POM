package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SideBar extends BaseLibrary {

    private By adminModule = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
    private By pimModule = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
    private By leaveModule = By.xpath("//a[@href='/web/index.php/leave/viewLeaveModule']");
    private By timeModule = By.xpath("//a[@href='/web/index.php/time/viewTimeModule']");
    private By recruitmentModule = By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']");
    private By dashboardModule = By.xpath("//a[@href='/web/index.php/dashboard/index']");
    private By buzzModule = By.xpath("//a[@href='/web/index.php/buzz/viewBuzz']");
    private By viewDetails = By.xpath("//a[@href='/web/index.php/pim/viewMyDetails']//span[text()='My Info']");

    @Step("Admin modülüne tıklanır")
    public void clickAdminModule() {
        clickMenuItem(adminModule);
    }

    @Step("PIM modülüne tıklanır")
    public void clickPimModule() {
        clickMenuItem(pimModule);
    }

    @Step("MyInfo modülüne tıklanır")
    public void clickMyInfoModule() {
        clickMenuItem(viewDetails);
    }

    @Step("Leave modülüne tıklanır")
    public void clickLeaveModule() {
        clickMenuItem(leaveModule);
    }

    @Step("Time modülüne tıklanır")
    public void clickTimeModule() {
        clickMenuItem(timeModule);
    }

    @Step("Recruitment modülüne tıklanır")
    public void clickRecruitmentModule() {
        clickMenuItem(recruitmentModule);
    }

    @Step("Dashboard modülüne tıklanır")
    public void clickDashboardModule() {
        clickMenuItem(dashboardModule);
    }

    @Step("Buzz modülüne tıklanır")
    public void clickBuzzModule() {
        clickMenuItem(buzzModule);
    }

    // Genel menü öğesi tıklama işlemi
    @Step("Menü öğesine tıklanır")
    private void clickMenuItem(By menuItem) {
        WebElement menuElement = driver.findElement(menuItem);
        menuElement.click();
    }
}
