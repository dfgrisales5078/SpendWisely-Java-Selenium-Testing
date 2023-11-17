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


public class TestLoginPage {
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
    public void CheckNavBarLinks() throws InterruptedException {
        WebElement nav_register_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[2]/a"));
        nav_register_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/register");
        Thread.sleep(1000);

        WebElement nav_forgot_password_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[3]/a"));
        nav_forgot_password_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/forgot-password");
        Thread.sleep(1000);

        WebElement nav_home_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[1]/a"));
        nav_home_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/");
        Thread.sleep(1000);
    }

    @Test (priority = 2)
    public void CheckLoginPageLinks() throws InterruptedException {
        WebElement register_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/p/a[1]"));
        register_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/register");
        Thread.sleep(1000);

        driver.get("http://www.localhost:3000");
        WebElement forgot_password_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/p/a[2]"));
        forgot_password_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/forgot-password");
        Thread.sleep(1000);
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
        Thread.sleep(1000);

        WebElement invalid_credentials_message = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/div"));
        WebElement close_message = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/div/button"));
        assert invalid_credentials_message.isDisplayed();
        close_message.click();
        Assert.assertFalse(isElementPresent(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[3]/div")));
        Thread.sleep(1000);
    }

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Test (priority = 4)
    public void SuccessfulLogin() throws InterruptedException {
        WebElement name = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/button"));

        name.sendKeys("amplify@email.com");
        password.sendKeys("amplify");
        Thread.sleep(1000);
        login_button.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/transactions");
        Thread.sleep(1000);
    }

    @AfterClass
    public void CloseBrowser() {
        driver.quit();
    }

}