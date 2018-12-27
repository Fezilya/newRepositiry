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

    void login(String name, String password) {
        driver.findElement(By.xpath("//a[contains(@class,'login-enter-expanded')]")).click();
        driver.findElement(By.xpath("//input[contains(@autocomplete,'username')]")).sendKeys(name);
        driver.findElement(By.xpath("//input[contains(@autocomplete,'current-password')]")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(@class,'Button-Text')]")).click();
    }

    void composeMessage()  {
        driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).click();
    }
    void writeAddress()  {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title, 'Fezilya')]")));
        driver.findElement(By.xpath("//span[contains(@title, 'Fezilya')]")).click();
    }
    void writeSubject(String subject) {
        driver.findElement(By.xpath("//input[contains(@class, 'tabfocus-prev')]")).sendKeys(subject);
    }
    void sendMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, ' js-send-button')]")));
        driver.findElement(By.xpath("//button[contains(@class, ' js-send-button')]")).click();
    }

    void checkLogin ()  {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")));
        }
        catch(Exception e){
            Assert.fail("Element is not present");
        }
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).isDisplayed());
    }
    void checkSendMessage() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")));
        }
        catch(Exception e) {
            Assert.fail("Element is not present");
        }
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")).isDisplayed());
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




    @Test
    private void sendAMessageToMyself()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        composeMessage();
        writeAddress();
        writeSubject("AnotherSubject");
        sendMessage();
        checkSendMessage();

    }
    @Test
    private void DeleteMyMessages()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        chooseAnyMessage("fezilya@yandex.ru");
        deleteMessage();
    }
    @Test
    private void changeLanguage()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        goToSettings();
        otherSettings();
        changeLanguage("en");

    }
    @Test
    private void messageWithoutSubject()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        composeMessage();
        writeAddress();
        sendMessage();
        checkSendMessage();
    }
    @Test
    private void emptyMessage()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        composeMessage();
        sendMessage();
        checkSendMessage();
    }
    @Test
    private void delete()  {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        deleteMessage();
    }

}



