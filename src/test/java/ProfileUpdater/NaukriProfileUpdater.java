package ProfileUpdater;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NaukriProfileUpdater {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Use WebDriverManager to handle ChromeDriver setup
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Assign to class-level driver
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testUpdateProfileHeadline() {
        driver.get("https://www.naukri.com/nlogin/login");

        // Login username
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameField")));
        usernameField.sendKeys("dalwairajkumar22@gmail.com");

        // Login password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordField")));
        passwordField.sendKeys("Rajkumar@25");

        // Click login
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        loginButton.click();

        // Wait until URL changes away from login page
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/nlogin")));

        // Wait for and click 'View profile' link
        By profileLink = By.xpath("//div[@class='view-profile-wrapper']/a[normalize-space()='View profile']");
        System.out.println("Waiting for 'View profile' link...");
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        System.out.println("'View profile' link found and clickable");
        profile.click();

        // Click edit on Resume Headline
        WebElement editIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']")
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