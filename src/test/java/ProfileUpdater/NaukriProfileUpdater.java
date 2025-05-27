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
        // For debugging, you can comment out headless mode temporarily
        options.addArguments("--headless=new");  
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        // Use a reasonable implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testUpdateProfileHeadline() throws InterruptedException {
        driver.get("https://www.naukri.com/nlogin/login");

        // Login username
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameField")));
        usernameField.sendKeys("dalwairajkumar22@gmail.com");

        // Login password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
        passwordField.sendKeys("98608663140");

        // Click login
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Wait until URL changes away from login page or some element appears indicating successful login
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/nlogin")));

        // Wait for and click 'View profile' link using updated locator
        By profileLink = By.xpath("//div[@class='view-profile-wrapper']/a[normalize-space()='View profile']");
        System.out.println("Waiting for 'View profile' link...");
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        System.out.println("'View profile' link found and clickable");
        profile.click();

        // Wait for profile page to load - can be improved by waiting for a specific element on profile page
        Thread.sleep(5000);

        // Click edit on Resume Headline (adjust the locator if necessary)
        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[contains(@class,'widgetHead')]//span[contains(@class,'edit') and contains(text(),'editOneTheme')]")
        ));
        editIcon.click();

        // Update headline textarea
        By headlineBox = By.id("resumeHeadlineTxt");
        WebElement headlineTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(headlineBox));
        headlineTextArea.clear();
        headlineTextArea.sendKeys("Senior QA Engineer | Manual & Automation Testing: Selenium, Cypress, Appium, Rest Assured | TestNG, BDD, Cucumber | API, DB, Performance Testing | JIRA, Postman, JMeter, GitHub | CI/CD: Jenkins | SQL.");

        // Click Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']")));
        saveBtn.click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
