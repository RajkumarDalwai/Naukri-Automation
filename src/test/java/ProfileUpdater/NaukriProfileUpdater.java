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

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void testUpdateProfileHeadline() throws InterruptedException {
        driver.get("https://www.naukri.com/nlogin/login");

        // Use explicit wait for username field
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='usernameField']")));
        usernameField.sendKeys("dalwairajkumar22@gmail.com");

        // Password field
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='passwordField']")));
        passwordField.sendKeys("98608663140");

        // Login button
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Wait for navigation
        Thread.sleep(6000);

        // Navigate to profile
        driver.findElement(By.xpath("//a[normalize-space()='View profile']")).click();

        // Wait for profile to load
        Thread.sleep(6000);

        // Click edit on Resume Headline
        driver.findElement(By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']")).click();

        // Clear and update headline
        By headlineBox = By.xpath("//textarea[@id='resumeHeadlineTxt']");
        driver.findElement(headlineBox).clear();
        driver.findElement(headlineBox).sendKeys("Senior QA Engineer | Manual & Automation Testing: Selenium, Cypress, Appium, Rest Assured | TestNG, BDD, Cucumber | API, DB, Performance Testing | JIRA, Postman, JMeter, GitHub | CI/CD: Jenkins | SQL with github actions");

        // Click Save
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}