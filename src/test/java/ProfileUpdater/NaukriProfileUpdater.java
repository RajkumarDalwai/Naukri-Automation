package ProfileUpdater;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NaukriProfileUpdater {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // Set to 0 to rely on explicit waits only
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testUpdateProfileHeadline() {
        driver.get("https://www.naukri.com/nlogin/login");

        // Wait and fill username
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameField")));
        usernameField.sendKeys("dalwairajkumar22@gmail.com");

        // Wait and fill password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
        passwordField.sendKeys("98608663140");

        // Click login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        loginButton.click();

        // Wait until "View profile" link is visible and clickable
        WebElement viewProfileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='View profile']")));
        viewProfileLink.click();

        // Wait until Edit icon is clickable
        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='widgetHead']//span[contains(@class,'edit') and normalize-space()='editOneTheme']")));
        editIcon.click();

        // Wait for headline textarea to be visible
        WebElement headlineBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resumeHeadlineTxt")));
        headlineBox.clear();
        headlineBox.sendKeys("Senior QA Engineer | Manual & Automation Testing: Selenium, Cypress, Appium, Rest Assured | TestNG, BDD, Cucumber | API, DB, Performance Testing | JIRA, Postman, JMeter, GitHub | CI/CD: Jenkins | SQL with github actions");

        // Wait and click Save button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
        saveButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
