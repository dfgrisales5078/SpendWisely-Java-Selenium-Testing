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


public class TestTransactionsPage{
    public WebDriver driver;

    @BeforeClass
    public void testSetup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        // Open browser and login
        driver.get("http://www.localhost:3000");
        WebElement name = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        WebElement login_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/form/div[3]/button"));

        name.sendKeys("amplify@email.com");
        password.sendKeys("amplify");
        login_button.click();
    }

    @BeforeMethod
    public void OpenWebsite() {
        driver.get("http://www.localhost:3000/transactions");
    }

    @Test (priority = 1)
    public void FinancialOverviewLink() throws InterruptedException {
        WebElement financial_overview_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[2]/a"));
    }

    @AfterClass
    public void CloseBrowser() {
        driver.quit();
    }

}