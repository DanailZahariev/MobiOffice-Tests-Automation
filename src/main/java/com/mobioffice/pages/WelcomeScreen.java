package com.mobioffice.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WelcomeScreen extends BaseScreen {

    private final By acceptBtn = AppiumBy.id("com.mobisystems.office:id/button_start");
    private final By skipBtn = AppiumBy.id("com.mobisystems.office:id/btn_skip");
    private final By xBtn = AppiumBy.id("com.mobisystems.office:id/close");

    public WelcomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public void handleOnboarding() {
        try {
            if (isElementDisplayed(acceptBtn)) {
                click(acceptBtn);
            }
        } catch (Exception e) {
            LOGGER.info("Onboarding is not displayed");
        }

        try {

            if (isElementDisplayed(skipBtn)) {
                click(skipBtn);
            }
        } catch (Exception e) {
            LOGGER.info("Skip button is not displayed");
        }

        try {
            if (isElementDisplayed(xBtn)) {
                click(xBtn);
            }
        } catch (Exception e) {
            LOGGER.info("Close button is not displayed");
        }
    }
}