package util.photographer;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Photographer {

    private WebDriver driver;

    public Photographer(WebDriver driver){
        this.driver = driver;
    }

    public void takePhoto(String screenshotName) throws IOException {
        // Take the screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Generate a random filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String randomId = UUID.randomUUID().toString().substring(0, 8); // Short random suffix
        String fileName = screenshotName + "_" + timestamp + "_" + randomId + ".png";

        // Path to resources/screenshots folder
        File resourcesDir = new File("src/test/resources/screenshots");
        if (!resourcesDir.exists()) {
            resourcesDir.mkdirs();
        }

        File destFile = new File(resourcesDir, fileName);

        // Copy the screenshot to the destination
        FileHandler.copy(screenshotFile, destFile);

        System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
    }
}
