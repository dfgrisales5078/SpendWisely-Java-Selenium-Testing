//package TestCases;
//
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//
//public class TestRegisterPage{
//    public WebDriver driver;
//
//    @BeforeClass
//    public void testSetup() {
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//    }
//
//    @BeforeMethod
//    public void openWebsite() {
//        driver.get("http://www.localhost:3000/register");
//    }
//
//    @Test (priority = 1)
//    public void ExistingAccountRegistration() throws InterruptedException {
//        WebElement name = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[1]/input"));
//        WebElement email = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[2]/input"));
//        WebElement password = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[3]/input"));
//        WebElement register_button = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[4]/button"));
//
//        WebElement existing_account_message = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[4]/div"));
//        WebElement close_message = driver.findElement(By.xpath(
//                "//*[@id=\"root\"]/div/div/div/form/div[4]/div/button"));
//
//        name.sendKeys("Test User");
//        email.sendKeys("amplify@email.com");
//        password.sendKeys("Amplify23");
//        register_button.click();
//        Thread.sleep(2000);
//        //assert existing_account_message.isDisplayed();
//    }
//
//    @AfterClass
//    public void closeBrowser() {
//        driver.quit();
//    }
//
//}