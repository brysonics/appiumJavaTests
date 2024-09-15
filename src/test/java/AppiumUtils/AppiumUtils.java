package AppiumUtils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class AppiumUtils {

    private AndroidDriver driver;

    public AppiumUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(By locator, int maxSwipes) {
        WebElement element;
        boolean elementFound = false;

        int swipeCount = 0;

        // Loop until the element is found or maxSwipes is reached
        while (!elementFound && swipeCount < maxSwipes) {
            try {
                element = driver.findElement(locator);

                if (isElementInViewport(element)) {
                    System.out.println("Element is visible.");
                    elementFound = true;
                } else {
                    System.out.println("Element is not visible. Attempting to swipe.");
                    performSwipe();
                }
            } catch (Exception e) {
                // Element not found yet, perform swipe
                performSwipe();
            }

            swipeCount++;
        }

        if (!elementFound) {
            throw new RuntimeException("Element not found after " + maxSwipes + " swipes.");
        }
    }

    private boolean isElementInViewport(WebElement element) {
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        Dimension viewportSize = driver.manage().window().getSize();

        int elementTop = elementLocation.getY();
        int elementBottom = elementTop + elementSize.getHeight();
        int viewportTop = 0;
        int viewportBottom = viewportSize.getHeight();

        return (elementBottom > viewportTop && elementTop < viewportBottom);
    }

    private void performSwipe() {
        Dimension size = driver.manage().window().getSize();
        int width = size.getWidth();
        int height = size.getHeight();

        int startX = width / 2;
        int startY = (int) (height * 0.8); // Start swipe from 80% of the screen height
        int endX = width / 2;
        int endY = (int) (height * 0.2); // Swipe up to 20% of the screen height

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void doubleTap(WebElement element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Point location = element.getLocation();
        int centerX = location.getX() + (element.getSize().getWidth() / 2);
        int centerY = location.getY() + (element.getSize().getHeight() / 2);

        // First tap sequence
        Sequence tap1 = new Sequence(finger, 0);
        tap1.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap1.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap1.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Pause (can use a small delay to simulate real double tap)
        tap1.addAction(new Pause(finger, Duration.ofMillis(100))); // Corrected Pause action

        // Second tap sequence
        Sequence tap2 = new Sequence(finger, 1);
        tap2.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap2.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap2.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform both taps as part of the double-tap action
        driver.perform(Collections.singletonList(tap1));
        driver.perform(Collections.singletonList(tap2));
    }
}
