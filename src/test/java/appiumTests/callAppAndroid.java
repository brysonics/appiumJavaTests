package appiumTests;

import AppiumUtils.AppiumUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class callAppAndroid {
    public AppiumDriver driver;
    public WebDriverWait wait;
    private AppiumUtils appiumUtils;

    @BeforeClass
    public void beforeClass() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setUdid("192.168.100.3:5555")
                .setAppPackage("com.google.android.dialer")
                .setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity")
                .setAutomationName("uiautomator2");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, ofSeconds(30));
        appiumUtils = new AppiumUtils((AndroidDriver) driver);
    }

    /*@Test(priority = 1)
    public void clickSearch(){
        wait.until(elementToBeClickable(AppiumBy.id("com.google.android.dialer:id/open_search_bar"))).click();
    }*/

    /*@Test(priority = 2)
    public void searchUser(){
        driver.findElement(AppiumBy.xpath
                ("//android.widget.EditText[@resource-id=\"com.google.android.dialer:id/open_search_view_edit_text\"]"))
                .sendKeys("chep");

    }*/

    @Test(priority = 3)
    public void longPress(){
        // Find the element by accessibility ID
        WebElement contact = driver.findElement(AppiumBy.accessibilityId("Quick contact for Uncle Gitau"));

// Get the element's location and size
        int centerX = contact.getRect().getX() + (contact.getRect().getWidth() / 2);
        int centerY = contact.getRect().getY() + (contact.getRect().getHeight() / 2);

// Initialize the pointer input (simulating a finger)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

// Create a sequence of actions for a long press
        Sequence longPress = new Sequence(finger, 1);

// Move the "finger" to the center of the element
        longPress.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));

// Press down at the element location (centerX, centerY)
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

// Wait for the desired duration to simulate the long press (e.g., 2 seconds)
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2))); // Adjust the time as needed

// Release the press
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

// Perform the action
        driver.perform(Collections.singletonList(longPress));


    }

    @Test(priority = 4)
    public void Tap (){
        WebElement contact_info = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Contact info\"]"));

        // Initialize the pointer input (simulating a finger)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

// Create a sequence of actions for a tap
        Sequence tap = new Sequence(finger, 1);

// Move the "finger" to the element's location
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.fromElement(contact_info), 0, 0));

// Press down at the element location
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

// Release the press to complete the tap
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

// Perform the action
        driver.perform(Collections.singletonList(tap));
    }

    @Test(priority = 1)
    public void scrollToElement (){
        By elementLocator = By.xpath("//android.widget.TextView[@resource-id='com.google.android.dialer:id/name' and @text='John Swekenyi']");
        appiumUtils.scrollToElement(elementLocator, 5); // Adjust the maxSwipes value

    }

    @Test(priority = 2)
    public void doubleTap(){
        WebElement element = driver.findElement(By.xpath(
                "//android.widget.TextView[@resource-id=\"com.google.android.dialer:id/name\" and @text=\"John Swekenyi\"]"));

        AppiumUtils utils = new AppiumUtils((AndroidDriver) driver);

        // Double tap the element
        utils.doubleTap(element);
    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
