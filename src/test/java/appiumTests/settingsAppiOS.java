package appiumTests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.time.Duration;



public class settingsAppiOS {

    private IOSDriver driver;
    private void performTap(int i, int i1) {
    }

    @BeforeClass
    public void setUp() throws Exception {
        XCUITestOptions options = new XCUITestOptions();
        options.setCapability("platformName", "iOS");
        options.setCapability("udid", "88E4FF32-96D7-4A61-A038-DA0FF519FC8C");
        options.setCapability("bundleId", "com.apple.Preferences");
        options.setCapability("automationName", "XCUITest");
        String SERVER = "http://127.0.0.1:4723";
        driver = new IOSDriver(new URL(SERVER), options);
    }


    //@Test(priority = 1)
    //public void clickScreenTime(){
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //driver.findElement(AppiumBy.accessibilityId("Screen Time")).click();

    //}

    @Test(priority = 1)
    public void testTouchActions() {
        // Perform tap gesture at coordinates (280, 615)
        performTap(280, 615);

        // Perform tap gesture at coordinates (264, 557)
        performTap(264, 557);

    }

    @Test(priority = 2)
    public void clickDeveloper(){

        // Click on element with accessibility ID "Developer"
        driver.findElement(AppiumBy.accessibilityId("Developer")).click();
    }

    @Test(priority = 3)



    @AfterClass
    public void teardown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
