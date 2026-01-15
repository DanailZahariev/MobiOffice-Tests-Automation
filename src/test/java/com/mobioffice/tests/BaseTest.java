package com.mobioffice.tests;

import com.mobioffice.pages.WelcomeScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import static com.mobioffice.utils.Config.*;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(PLATFORM_NAME);
        options.setAutomationName(AUTOMATION_NAME);
        options.setDeviceName(DEVICE_NAME);
        options.setUdid(DEVICE_NAME);
        options.setAppPackage(APP_PACKAGE);
        options.setAppActivity(APP_ACTIVITY);
        options.setNoReset(false);
        options.setAutoGrantPermissions(true);

        try {
            driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Appium Server URL is invalid: " + APPIUM_SERVER_URL, ex);
        }
        new WelcomeScreen(driver).handleOnboarding();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
