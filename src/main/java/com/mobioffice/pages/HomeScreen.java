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

    public List<String> getAllFiles() {
        return fluentWaitUntilVisible(fileNames);
    }

    public void handleWelcomePopUp() {
        if (isElementDisplayed(welcomeMessage)) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(welcomeMessage));
        } else {
            LOGGER.info("Welcome message is not displayed");
        }
    }
}
