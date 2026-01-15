package com.mobioffice.pages;

import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BaseScreen {

    protected AndroidDriver driver;
    protected WebDriverWait wait;
    protected final Logger LOGGER = LogManager.getLogger(this.getClass());

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void click(By locator) {
        waitToBeClickable(locator);
    }

    protected void fillInput(By locator, String text) {
        waitForVisibility(locator);
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        waitForVisibility(locator);
        return find(locator).getText();
    }

    protected boolean isElementDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    private void waitToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected List<String> fluentWaitUntilVisible(By locator) {
        FluentWait<AndroidDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(d -> {
            List<String> resultList = new ArrayList<>();
            List<WebElement> elements = d.findElements(locator);
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    resultList.add(element.getText());
                }
            }
            return resultList;
        });
    }
}
