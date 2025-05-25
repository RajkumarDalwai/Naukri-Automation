import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NaukriProfileUpdater {
    public static void main(String[] args) throws InterruptedException {
    	
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Add implicit wait
        
        driver.get("https://www.naukri.com/nlogin/login");

        driver.findElement(By.id("usernameField")).sendKeys("dalwairajkumar22@gmail.com");
        driver.findElement(By.id("passwordField")).sendKeys("98608663140");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Navigate to profile
        driver.findElement(By.cssSelector("div[class='view-profile-wrapper'] a")).click();

        // Click edit on Resume Headline
        driver.findElement(By.cssSelector("div[class='card mt15'] div span[class='edit icon']")).click();

        // Clear and update headline
        By headlineBox = By.cssSelector("#resumeHeadlineTxt");
        driver.findElement(headlineBox).clear();
        driver.findElement(headlineBox).sendKeys("Senior QA Engineer | Manual & Automation Testing: Selenium, Cypress, Appium, Rest Assured | TestNG, BDD, Cucumber | API, DB, Performance Testing | JIRA, Postman, JMeter, GitHub | CI/CD: Jenkins | SQL");

        // Click Save
        driver.findElement(By.cssSelector("div[class='action s12'] button[type='submit']")).click();

        driver.quit(); // Enable if you want to close the browser after update
    }
}
