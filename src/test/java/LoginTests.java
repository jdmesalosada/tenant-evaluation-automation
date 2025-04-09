import home.HomePage;
import login.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.environment.EnvironmentDataHandler;
import util.photographer.Photographer;

import java.io.IOException;

public class LoginTests {

    WebDriver driver;
    Photographer photographer;

    @BeforeAll
    public static void getEnvVariables(){
        if (System.getProperty("env") == null) {
            throw new IllegalArgumentException("You have to define the env variable with any of these values: local, dev or uat");
        }
    }

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        this.driver.get(EnvironmentDataHandler.get().url());
        this.photographer = new Photographer(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    public void loginValidationTest(String username, String password, String validationMessage) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials(username, password);

        HomePage homePage = new HomePage(driver);
        homePage.checkValidationLoginMessage(validationMessage);
        photographer.takePhoto("login");
    }

    @Test
    public void logoutTest() throws IOException {
        String validUsername = EnvironmentDataHandler.get().username();
        String validPassword =  EnvironmentDataHandler.get().password();
        String expectedValidationMessage = String.format("Welcome, %s!", validUsername);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials(validUsername, validPassword);

        HomePage homePage = new HomePage(driver);
        homePage.checkValidationLoginMessage(expectedValidationMessage);
        homePage.clickOnLogOutButton();

        loginPage.checkUserIsLoggedOut();
        photographer.takePhoto("login");
    }

    @AfterEach
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
