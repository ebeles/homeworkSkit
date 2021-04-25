package mk.ukim.finki.homework3.seleniumTest;


import mk.ukim.finki.homework3.JavaPages.LoginPage;
import mk.ukim.finki.homework3.JavaPages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test
    public void couldntLogInWithInvalidCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("username_test", "Test1234");
        String errorMessage = loginPage.getErrorMessage();
        System.out.println(errorMessage);
        assertEquals(errorMessage, "Your email or password were incorrect.");

    }

    @Test
    public void couldntLogInWithEmptyUsername() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "Test1234");
        String errorMessage = loginPage.getErrorMessage();
        System.out.println(errorMessage);
        assertEquals(errorMessage, "Your email or password were incorrect.");

    }

    @Test
    public void succeededLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("beleskaemilija@gmail.com", "joshswain");
        assertTrue(new ProductPage(driver).isLoaded());

    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/User/IdeaProjects/homework3/src/main/resources/driver/usedchromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}