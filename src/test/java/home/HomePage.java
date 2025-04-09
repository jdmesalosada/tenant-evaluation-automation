package home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePage {

    @FindBy(id = "loginstatus")
    WebElement successfulLoginMessage;

    @FindBy(xpath = "//button[contains(., 'Log Out')]")
    WebElement logoutButton;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void checkValidationLoginMessage(String expectedValidationMessage){
        String currentSuccessfulMessage = successfulLoginMessage.getText();

        assertEquals(expectedValidationMessage, currentSuccessfulMessage,
                "The login validation message is not the expected.");
    }

    public void clickOnLogOutButton(){
        logoutButton.click();
    }

}
