package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestFinancialOverview {
    public WebDriver driver;

    @BeforeClass
    public void testSetup() {
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
        login_button.click();
    }

    @BeforeMethod
    public void OpenWebsite() {
        driver.get("http://www.localhost:3000/financial-overview");
    }

    @Test (priority = 1)
    public void CheckBarGraphElement() {
        WebElement bar_graph = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div/canvas"));
        assert bar_graph.isDisplayed();
    }

    @Test (priority = 2)
    public void HomeLink() {
        WebElement nav_home_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[1]/a"));
        nav_home_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/transactions");
    }

    @Test (priority = 3)
    public void SignOutLink() {
        WebElement sign_out_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[3]/a"));
        sign_out_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/");
    }


    @AfterClass
    public void CloseBrowser() {
        driver.quit();
    }
}
