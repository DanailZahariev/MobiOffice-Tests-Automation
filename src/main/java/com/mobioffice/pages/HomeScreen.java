package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class HomeScreen extends BaseScreen {

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


    public HomeScreen clickShowMoreOrLess() {
        waitForVisibility(showMoreHeader);
        click(showMoreHeader);
        return this;
    }

    public boolean isCloudIconDisplayed() {
        List<WebElement> icons = driver.findElements(cloudIcon);
        return !icons.isEmpty();
    }

    public HomeScreen verifyCloudIconVisible(boolean shouldBeVisible) {
        if (shouldBeVisible) {
            Assert.assertTrue(isCloudIconDisplayed(), "Cloud icons should be visible!");
        } else {
            Assert.assertFalse(isCloudIconDisplayed(), "Cloud icons should NOT be visible!");
        }
        return this;
    }

    public HomeScreen verifyFileDoesNotExists(String fileName) {
        List<String> allFiles = getAllFiles();
        if (allFiles != null) {
            Assert.assertFalse(allFiles.contains(fileName), "File should NOT be visible: " + fileName);
        }
        return this;
    }

    public HomeScreen verifyFileExists(String fileName) {
        List<String> allFiles = getAllFiles();
        Assert.assertTrue(allFiles.contains(fileName), "File missing: " + fileName);
        return this;
    }

    public List<String> getAllFiles() {
        return fluentWaitUntilVisible(fileNames);
    }

    public HomeScreen clickHome() {
        click(homeTab);
        return this;
    }

    public void handleWelcomePopUp() {
        if (isElementDisplayed(welcomeMessage)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(welcomeMessage));
        } else {
            LOGGER.info("Welcome message is not displayed");
        }
    }
}
