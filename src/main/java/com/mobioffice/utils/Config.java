package com.mobioffice.utils;

public class Config {

    public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";

    public static final String DEVICE_NAME = System.getProperty("udid", "emulator-5554");
    public static final String PLATFORM_NAME = "Android";
    public static final String AUTOMATION_NAME = "UiAutomator2";

    public static final String APP_PACKAGE = "com.mobisystems.office";
    public static final String APP_ACTIVITY = "com.mobisystems.office.files.FileBrowser";

}
