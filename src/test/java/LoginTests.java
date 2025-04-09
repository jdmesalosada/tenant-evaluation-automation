import home.HomePage;
import login.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.environment.EnvironmentDataHandler;
import util.photographer.TestResultLoggerExtension;

@ExtendWith(TestResultLoggerExtension.class)
public class LoginTests {

    WebDriver driver;

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
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    public void loginValidationTest(String username, String password, String validationMessage){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials(username, password);

        HomePage homePage = new HomePage(driver);
        homePage.checkValidationLoginMessage(validationMessage);
    }

    @Test
    public void logoutTest(){
        String validUsername = EnvironmentDataHandler.get().username();
        String validPassword =  EnvironmentDataHandler.get().password();
        String expectedValidationMessage = String.format("Welcome, %s!", validUsername);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithCredentials(validUsername, validPassword);

        HomePage homePage = new HomePage(driver);
        homePage.checkValidationLoginMessage(expectedValidationMessage);
        homePage.clickOnLogOutButton();

        loginPage.checkUserIsLoggedOut();

    }

    @AfterEach
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
