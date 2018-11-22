import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewClass {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    String superlink = "https://yandex.ru/";


    void goTo(String superlink) {
        driver.get(this.superlink);

    }

    void login(String name, String password) {
        driver.findElement(By.xpath("//a[contains(@class,'login-enter-expanded')]")).click();
        driver.findElement(By.xpath("//input[contains(@autocomplete,'username')]")).sendKeys(name);
        driver.findElement(By.xpath("//input[contains(@autocomplete,'current-password')]")).sendKeys(password);
        driver.findElement(By.xpath("//span[contains(@class,'Button-Text')]")).click();
    }
    void sendMessage (String subject ) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]"))));
        driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).click();
        driver.findElement(By.xpath("//span[contains(@data-name, 'Себе')]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'tabfocus-prev')]")).sendKeys(subject);
        driver.findElement(By.xpath("//button[contains(@id, 'nb-33')]")).click();
     }
    /*void checkLogin () throws InterruptedException {
        //driver.wait(3);
        WebDriver.Timeouts timeouts = driver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@class, 'mail-ComposeButton')]")).isDisplayed());
    }*/
    void checkSendMessage() {
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'mail-Done-Title ')]")).isDisplayed());
    }
    //////////////////////////////////////////////

    @Test
    private void someTest() throws InterruptedException {

        goTo(superlink);
        login("Fezilya", "randompassword001");
        //checkLogin();
        sendMessage("anySubject");
        checkSendMessage();

    }
}


