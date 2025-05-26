package ProfileUpdater;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NaukriProfileUpdater {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // For GitHub Actions (new headless mode for Chrome 109+)
        options.addArguments("--no-sandbox"); // Required in CI environments
        options.addArguments("--disable-dev-shm-usage"); // Prevents limited resource issues

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testUpdateProfileHeadline() throws InterruptedException {
        driver.get("https://www.naukri.com/nlogin/login");

        driver.findElement(By.id("usernameField")).sendKeys("dalwairajkumar22@gmail.com");
        driver.findElement(By.id("passwordField")).sendKeys("98608663140");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Wait for navigation (use explicit wait in real code)
        Thread.sleep(5000);

        // Navigate to profile
        driver.findElement(By.cssSelector("div[class='view-profile-wrapper'] a")).click();

        // Wait for profile to load
        Thread.sleep(5000);

        // Click edit on Resume Headline
        driver.findElement(By.cssSelector("div[class='card mt15'] div span[class='edit icon']")).click();

        // Clear and update headline
        By headlineBox = By.cssSelector("#resumeHeadlineTxt");
        driver.findElement(headlineBox).clear();
        driver.findElement(headlineBox).sendKeys("Senior QA Engineer | Manual & Automation Testing: Selenium, Cypress, Appium, Rest Assured | TestNG, BDD, Cucumber | API, DB, Performance Testing | JIRA, Postman, JMeter, GitHub | CI/CD: Jenkins | SQL at 7 am");

        // Click Save
        driver.findElement(By.cssSelector("div[class='action s12'] button[type='submit']")).click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
