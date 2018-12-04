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
        driver.get(this.superlink);

    }

    void login(String name, String password) throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@class,'login-enter-expanded')]")).click();
        driver.findElement(By.xpath("//input[contains(@autocomplete,'username')]")).sendKeys(name);
        driver.findElement(By.xpath("//input[contains(@autocomplete,'current-password')]")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(@class,'Button-Text')]")).click();
    }

    void writeMessage() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).click();
    }

    void writeAdress() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-name, 'Себе')]")));
        driver.findElement(By.xpath("//span[contains(@data-name, 'Себе')]")).click();
}
    void writeSubject(String subject) {
        driver.findElement(By.xpath("//input[contains(@class, 'tabfocus-prev')]")).sendKeys(subject);
    }
    void sendMessage() {
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
    void chooseMessage() {
        List<WebElement> login = driver.findElements(By.xpath("//span[@title='fezilya@yandex.ru']/../..//span[contains(@class, 'nb-checkbox-flag ')]"));
        for( WebElement a:login) {
            a.click();

        }
    }
    void deleteMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, '-delete')]")));
        driver.findElement(By.xpath("//span[contains(@class, '-delete')]")).click();
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
    private void someTest() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        writeMessage();
        writeAdress();
        writeSubject("anySubject");
        sendMessage();
        checkSendMessage();

    }
    @Test
    private void anotherTest() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        chooseMessage();
        deleteMessage();
    }
    @Test
    private void teest() throws  InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();


    }
}


