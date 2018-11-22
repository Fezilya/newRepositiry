import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    void sendMessage (String subject ) throws InterruptedException{
        driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@data-name, 'Себе')]")));
        driver.findElement(By.xpath("//span[contains(@data-name, 'Себе')]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'tabfocus-prev')]")).sendKeys(subject);
        driver.findElement(By.xpath("//button[contains(@class, ' js-send-button')] ")).click();
     }
    void checkLogin () throws InterruptedException {
        Thread.sleep(10000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).isDisplayed());
    }
    void checkSendMessage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")).isDisplayed());
    }
    //////////////////////////////////////////////

    @Test
    private void someTest() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        checkLogin();
        sendMessage("anySubject");
        checkSendMessage();

    }
}


