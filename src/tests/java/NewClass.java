import com.sun.jna.IntegerType;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class NewClass {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    String superlink = "https://yandex.ru/";


    void goTo(String superlink) {
        driver.get(superlink);

    }

    void login(String name, String password) throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@class,'login-enter-expanded')]")).click();
        driver.findElement(By.xpath("//input[contains(@autocomplete,'username')]")).sendKeys(name);
        driver.findElement(By.xpath("//input[contains(@autocomplete,'current-password')]")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(@class,'Button-Text')]")).click();
    }

    void sendMessage() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).click();
    }

    void writeAdress() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-name, 'Себе')]")));
        driver.findElement(By.xpath("//span[contains(@title, 'Fezilya')]")).click();
}
    void writeSubject(String subject) {
        driver.findElement(By.xpath("//input[contains(@class, 'tabfocus-prev')]")).sendKeys(subject);
    }
    void writeMessage() {
        driver.findElement(By.xpath("//button[contains(@class, ' js-send-button')] ")).click();
    }

    void checkLogin () throws InterruptedException {
        //Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).isDisplayed());
    }
    void checkSendMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")).isDisplayed());

    }
    void chooseMessage(String email) {
        List<WebElement> login = driver.findElements(By.xpath("//span[@title='"+ email + "']/../..//span[contains(@class, 'nb-checkbox-flag ')]"));
        for( WebElement a:login) {
            a.click();

        }
    }
    void chooseAnyMessage(String mail) {
        List<WebElement> emails = driver.findElements(By.xpath("//span[contains(@class, '-FromText')]"));
        List<WebElement> checkBox = driver.findElements(By.xpath("//span[contains(@class, 'nb-checkbox-flag ')]"));
            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).getAttribute("title").contains(mail)) {
                    checkBox.get(i).click();
                }
            }
    }
    void deleteMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, '-delete')]")));
        driver.findElement(By.xpath("//span[contains(@class, '-delete')]")).click();
    }
    void goToSettings() {
        driver.findElement(By.xpath("//a[contains(@data-nb-popup-toggler, 'settings')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '#setup/other')]")));
    }
    void otherSettings () {
        driver.findElement(By.xpath("//a[contains(@href, '#setup/other')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'Lang_arrow')]")));
    }
    void changeLanguage (String language) {
        driver.findElement(By.xpath("//span[contains(@class, 'Lang_arrow')]")).click();
        List<WebElement> languages = driver.findElements(By.xpath("//a[contains(@data-params, 'lang')]"));
            for (WebElement lang : languages) {
                if (lang.getAttribute("data-params").contains(language))
                lang.click();
            }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'Lang_arrow')]")));
    }
               /* boolean present;
                try {
                    a.findElement(By.xpath("span[contains(@title, 'fezilya@yandex.ru')]"));
                    present = true;
                } catch (NoSuchElementException e) {
                    present = false;
                }
            if (present = true)*/

    // void deleteMessage() {
     ////span[contains(@title, 'fezilya@yandex.ru')]
    //////////////////////////////////////////////

    @Test
    private void sendAMessageToMyself() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        writeMessage();
        writeAdress();
        writeSubject("AnotherSubject");
        sendMessage();
        checkSendMessage();

    }
    @Test
    private void DeleteMyMessages() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        chooseAnyMessage("fezilya@yandex.ru");
        //chooseMessage("fezilya@yandex.ru");
        deleteMessage();
    }
    @Test
    private void ChangeLanguage() throws  InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        goToSettings();
        otherSettings();
        changeLanguage("en");

    }
    @Test
    private void MessageWithoutSubject() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        writeMessage();
        writeAdress();
        sendMessage();
        checkSendMessage();
    }
    @Test
    private void EmptyMessage() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        writeMessage();
        writeAdress();
        sendMessage();
        checkSendMessage();
    }
    @Test
    private void Delete() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        deleteMessage();
    }

}



