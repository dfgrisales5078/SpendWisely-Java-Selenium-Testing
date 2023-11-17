package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TestFinancialOverview {
    public WebDriver driver;

    @BeforeClass
    public void testSetup() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Open browser and login
        driver.get("http://www.localhost:3000");
        WebElement name = driver.findElement(By.xpath(
                "//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.xpath(
                "//*[@id=\"password\"]"));
        WebElement login_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/button"));

        name.sendKeys("amplify@email.com");
        password.sendKeys("amplify");
        Thread.sleep(1000);
        login_button.click();
    }

    @BeforeMethod
    public void OpenWebsite() {
        driver.get("http://www.localhost:3000/financial-overview");
    }

    @Test (priority = 1)

    public void CheckBarGraphElement() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement barGraph = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"root\"]/div/div/div/div/div/canvas")));

        assert barGraph.isDisplayed();
        Thread.sleep(1000);
    }

    @Test (priority = 2)
    public void HomeLink() throws InterruptedException {
        WebElement nav_home_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[1]/a"));
        nav_home_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/transactions");
        Thread.sleep(1000);
    }

    @Test (priority = 3)
    public void SignOutLink() throws InterruptedException {
        WebElement sign_out_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[3]/a"));
        sign_out_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/");
        Thread.sleep(1000);
    }


    @AfterClass
    public void CloseBrowser() {
        driver.quit();
    }
}
