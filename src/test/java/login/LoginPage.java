package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage {

    @FindBy(name = "UserName")
    WebElement usernameElement;

    @FindBy(name = "Password")
    WebElement passwordElement;

    @FindBy(id = "login")
    WebElement submitElement;

    @FindBy(id= "loginstatus")
    WebElement loginStatus;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    private void enterUsername(String username){
        this.usernameElement.sendKeys(username);
    }

    private void enterPassword(String password){
        this.passwordElement.sendKeys(password);
    }

    private void clickOnSubmit(){
        this.submitElement.click();
    }

    public void loginWithCredentials(String username, String password){

        if(username == null){
            username = "";
        }
        if(password == null){
            password = "";
        }
        this.enterUsername(username);
        this.enterPassword(password);
        this.clickOnSubmit();
    }

    public void checkUserIsLoggedOut(){
        assertEquals("User logged out.", loginStatus.getText(), "Expected log out message is not displayed.");
    }

}
