package util.photographer;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.lang.reflect.Field;

public class TestResultLoggerExtension implements TestWatcher, BeforeEachCallback {

    private WebDriver driver;

    //Accessing the driver through reflection
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

        Object testInstance = context.getRequiredTestInstance();

        for (Field field : testInstance.getClass().getDeclaredFields()) {
            if (WebDriver.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                driver = (WebDriver) field.get(testInstance);
                break;
            }
        }

    }


    @Override
    public void testSuccessful(ExtensionContext context) {
        if (driver != null) {
            try {
                new Photographer(driver).takePhoto("success_" + context.getDisplayName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("⚠️ WebDriver is null in testFailed()");
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (driver != null) {
            try {
                new Photographer(driver).takePhoto("fail_" + context.getDisplayName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
