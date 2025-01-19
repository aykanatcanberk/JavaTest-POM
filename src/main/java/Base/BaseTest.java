package Base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest extends BaseLibrary {


    @BeforeMethod(description = "Web sayfası açılır")
    public void openBrowser() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Tarayıcı kapatılır")
    public void closeBrowser() {
        driver.quit();
    }
}
