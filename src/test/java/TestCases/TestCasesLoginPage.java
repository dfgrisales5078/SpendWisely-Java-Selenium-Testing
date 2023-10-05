package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestCasesLoginPage{
    public WebDriver driver;

    @BeforeClass
    public void testSetup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void OpenWebsite() {
        driver.get("http://www.localhost:3000");
    }

    @Test (priority = 1)
    public void RegisterLink() throws InterruptedException {
        WebElement register_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/p/a[1]"));

        register_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/register");
        Thread.sleep(2000);
    }

    @Test (priority = 2)
    public void ForgotPasswordLink() throws InterruptedException {
        WebElement forgot_password_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/p/a[2]"));

        forgot_password_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/forgot-password");
        Thread.sleep(2000);
    }

    @Test (priority = 3)
    public void FailedLogin() throws InterruptedException {
        WebElement name = driver.findElement(By.xpath(
                "//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.xpath(
                "//*[@id=\"password\"]"));
        WebElement login_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/button"));


        name.sendKeys("amplify@email.com");
        password.sendKeys("amp");
        login_button.click();

        WebElement invalid_credentials_message = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/div"));
        WebElement close_message = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/div/button"));

        assert invalid_credentials_message.isDisplayed();
        close_message.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/");
        Thread.sleep(2000);
        // TODO - assert !invalid_credentials_message.isDisplayed();
    }

    @Test (priority = 4)
    public void SuccessfulLogin() throws InterruptedException {
        WebElement name = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/button"));

        name.sendKeys("amplify@email.com");
        password.sendKeys("amplify");
        login_button.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/transactions");
        Thread.sleep(2000);
    }

    @AfterClass
    public void CloseBrowser() {
        driver.quit();
    }

}