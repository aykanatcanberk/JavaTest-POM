package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BuzzPage extends BaseLibrary {

    private By postTextArea = By.xpath("//textarea[@placeholder=\"What's on your mind?\"]");
    private By postButton = By.xpath("//button[contains(@class, 'oxd-button--main') and text()=' Post ']");
    private By threeDotsButton = By.cssSelector("button.oxd-icon-button i.oxd-icon.bi-three-dots:first-of-type");
    private By editPostOption = By.xpath("//li[contains(@class, 'orangehrm-buzz-post-header-config-item')]//p[text()='Edit Post']");
    private By deletePosOption = By.xpath("//li[contains(@class, 'orangehrm-buzz-post-header-config-item')]//p[text()='Delete Post']");
    private By postContentTextarea = By.xpath("(//textarea[@class='oxd-buzz-post-input'])[2]");
    private By editPostButton = By.xpath("(//button[@class='oxd-button oxd-button--medium oxd-button--main'])[2]");
    private By deletePostButton = By.cssSelector("button.oxd-button--label-danger");
    private By toastMessage = By.xpath("//p[contains(@class, 'oxd-text--toast-title') and text()='Success']");
    private By likeButton = By.cssSelector("svg#heart-svg.orangehrm-heart-icon");
    private By likeText = By.xpath("(//div[contains(@class, 'orangehrm-buzz-stats-row')]//p[contains(@class, 'oxd-text')])[1]");


    @Step("Post metni '{postContent}' girilir")
    public void enterPostContent(String postContent) {
        WebElement textArea = driver.findElement(postTextArea);
        textArea.sendKeys(postContent);
    }

    @Step("Post paylaşma butonuna tıklanır")
    public void clickPostButton() {
        WebElement postBtn = driver.findElement(postButton);
        postBtn.click();
    }

    @Step("Post paylaşılır: '{postContent}'")
    public void sharePost(String postContent) {
        enterPostContent(postContent);
        clickPostButton();
    }

    @Step("kullanıcı üç nokta simgesine tıklar")
    public void clickEditPostButton() {
        WebElement threeDots = wait.until(ExpectedConditions.elementToBeClickable(threeDotsButton));
        threeDots.click();
    }

    @Step("kullanıcı düzenleme seçeneğini seçer")
    public void clickEditOption() {
        WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(editPostOption));
        editOption.click();
    }

    @Step("kullanıcı silme seçeneğini seçer")
    public void clickDeleteOption() {
        WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(deletePosOption));
        editOption.click();
    }

    @Step("kullanıcı yeni içerik olarak {string} girer")
    public void updatePostContent(String newContent) {
        WebElement contentArea = wait.until(ExpectedConditions.visibilityOfElementLocated(postContentTextarea));
        contentArea.clear();
        contentArea.sendKeys(newContent);
    }

    @Step("kullanıcı düzenlenmiş postu paylaşır")
    public void submitUpdatedPost() {
        WebElement postBtn = wait.until(ExpectedConditions.elementToBeClickable(editPostButton));
        postBtn.click();
    }

    @Step("kullanıcı silme butonuna tıklar")
    public void clickDeletePost() {

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deletePostButton));
        deleteButton.click();
    }

    @Step("post başarıyla silinmelidir")
    public boolean isDeleteSuccessful() {

        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
        return successToast.isDisplayed();
    }

    @Step("post begeni butonuna tıklanmalıdır")
    public void clickLikeButton() {

        WebElement _likeButton = wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        _likeButton.click();
    }

    @Step("post begeni butonunun işlevi kontrol edilir")
    public String getLikeCount() {
        WebElement likeText = driver.findElement(likeButton);
        return likeText.getText();
    }
}
