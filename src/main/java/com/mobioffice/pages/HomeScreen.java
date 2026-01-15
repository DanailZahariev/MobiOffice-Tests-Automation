package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomeScreen extends BaseScreen {


    private final By accountTab = AppiumBy.accessibilityId("Account");
    private final By homeTab = AppiumBy.accessibilityId("Home");
    private final By welcomeMessage = AppiumBy.xpath("//*[contains(@text, 'Welcome, Test User')]");
    private final By showMoreHeader = AppiumBy.id("com.mobisystems.office:id/header_button");
    private final By fileNames = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"File name\"]");
    private final By cloudIcon = AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.mobisystems.office:id/file_location_imageview\"])");

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public AccountScreen goToAccountSection() {
        handleWelcomePopUp();
        click(accountTab);
        return new AccountScreen(driver);
    }


    public void clickShowMoreOrLess() {
        waitForVisibility(showMoreHeader);
        click(showMoreHeader);
    }

    public boolean isCloudIconDisplayed() {
        List<WebElement> icons = driver.findElements(cloudIcon);
        return !icons.isEmpty();
    }

    public List<String> getAllFiles() {
        return fluentWaitUntilVisible(fileNames);
    }

    public void clickHome() {
        click(homeTab);
    }

    public void handleWelcomePopUp() {
        try {
            if (isElementDisplayed(welcomeMessage)) {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(welcomeMessage));
            }
        } catch (Exception e) {
            LOGGER.info("Welcome message is not displayed");
        }
    }
}
