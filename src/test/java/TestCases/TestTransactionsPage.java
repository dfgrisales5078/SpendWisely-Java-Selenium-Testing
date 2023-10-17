package TestCases;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;


public class TestTransactionsPage{
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
        driver.get("http://www.localhost:3000/transactions");
    }

    @Test (priority = 1)
    public void FinancialOverviewLink() {
        WebElement financial_overview_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[2]/a"));
        financial_overview_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/financial-overview");
    }

    @Test (priority = 2)
    public void HomeLink() {
        WebElement nav_home_link = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/ul/li[1]/a"));
        nav_home_link.click();
        assert driver.getCurrentUrl().equals("http://www.localhost:3000/transactions");
    }

    @Test (priority = 3)
    public void AddIncomeTransaction() {
        WebElement transaction_type_dropdown = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/select[1]"));
        transaction_type_dropdown.click();
        Select transaction_type_select = new Select(transaction_type_dropdown);
        transaction_type_select.selectByVisibleText("Income");

        WebElement transaction_category_dropdown = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/select[2]"));
        transaction_category_dropdown.click();

        Select transaction_category_select = new Select(transaction_category_dropdown);
        transaction_category_select.selectByVisibleText("Salary");

        WebElement transaction_amount = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/input"));
        float random_amount = (float) (Math.random() * 900 + 100);
        transaction_amount.sendKeys(Float.toString(random_amount));

        WebElement add_income_transaction_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/div/button"));
        add_income_transaction_button.click();
    }

    @Test (priority = 4)
    public void AddExpenseTransaction() {
        WebElement transaction_type_dropdown = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/select[1]"));
        transaction_type_dropdown.click();
        Select transaction_type_select = new Select(transaction_type_dropdown);
        transaction_type_select.selectByVisibleText("Expense");

        WebElement transaction_category_dropdown = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/select[2]"));
        transaction_category_dropdown.click();

        Select transaction_category_select = new Select(transaction_category_dropdown);
        transaction_category_select.selectByVisibleText("Education");

        WebElement transaction_amount = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/input"));
        float random_amount = (float) (Math.random() * 900 + 100);
        transaction_amount.sendKeys(Float.toString(random_amount));

        WebElement add_income_transaction_button = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div/div/div[2]/form/div/button"));
        add_income_transaction_button.click();
    }

    @Test (priority = 5)
    public void DeleteTransaction() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(driver -> driver.findElement(By.cssSelector(
                "#root > div > div > div > div > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(5) > button")));

        WebElement first_transaction = driver.findElement(By.cssSelector(
                "#root > div > div > div > div > div:nth-child(3) > table > tbody > tr:nth-child(1) > td:nth-child(5) > button"));
        first_transaction.click();
    }

    @Test (priority = 6)
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