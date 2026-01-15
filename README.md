# MobiOffice Automation 📱

Automated testing framework for the MobiOffice mobile application using Appium, Java, TestNG, and Maven.

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Test Data](#test-data)
- [How to Run Tests](#how-to-run-the-tests)
- [Test Reports](#test-reports)

## 🎯 Project Overview

This project provides end-to-end test automation for the MobiOffice mobile application, covering key functionalities such as file management, document operations, and user workflows.

## 🛠 Tech Stack

- **Java** - Programming language
- **Maven** - Build and dependency management
- **Appium** - Mobile automation framework
- **TestNG** - Testing framework
- **Selenium** - WebDriver for automation

## 📂 Project Structure

```
MobiOfficeAutomation/
├── .idea/                          # IntelliJ IDEA configuration
├── .mvn/                           # Maven wrapper files
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mobioffice/
│   │   │           ├── pages/      # Page Object Model classes
│   │   │           └── utils/      # Utility classes
│   │   │               ├── enums/  # Enum definitions
│   │   │               └── Config.java
│   │   └── resources/              # Configuration files
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── mobioffice/
│       │           └── tests/      # Test classes
│       │               ├── BaseTest.java
│       │               └── MobiOfficeE2ETest.java
│       └── resources/
│           └── log4j2.xml          # Logging configuration
├── target/                         # Build output and reports
├── pom.xml                         # Maven dependencies
└── README.md                       # Project documentation
```

## ✅ Prerequisites

Before running the tests, ensure you have the following installed:

### Required Software

1. **Java Development Kit (JDK) 17+**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **Node.js 18+** (required for Appium)
   ```bash
   node -v
   ```

4. **Appium Server 2.0+**
   ```bash
   npm install -g appium
   appium -v
   ```

5. **Android SDK** (via Android Studio)
    - Android SDK Platform Tools
    - Android SDK Build Tools
    - Android Emulator

6. **Appium UiAutomator2 Driver**
   ```bash
   appium driver install uiautomator2
   ```
7. **Put your email and password in the Enum class**
```
 Go to src/main/java/com/mobioffice/utils/enums/TestUser.java
```
### Environment Variables (macOS)

Add the following to your `~/.zshrc` or `~/.bash_profile`:

```bash
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/emulator
export PATH=$PATH:$ANDROID_HOME/platform-tools
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/tools/bin
```

Apply changes:
```bash
source ~/.zshrc
```

## 📥 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/MobiOfficeAutomation.git
   cd MobiOfficeAutomation
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify Appium installation**
   ```bash
   appium driver list
   ```

## ⚙️ Configuration

The main configuration is handled in the `Config.java` file located at:
```
src/main/java/com/mobioffice/utils/Config.java
```

Key configuration parameters include device capabilities, Appium server URL, and application paths.

## 📊 Test Data

### Required Files

The test scenario validates the presence of specific files in the "Recent Files" section. Ensure the test account has the following files opened recently:

| File Name | Type | Location |
|-----------|------|----------|
| `AttachFilePDF.pdf` | PDF | Cloud |
| `demo.docx` | Word | Local |
| `Slideshow.pptx` | PPT | Cloud |
| `Bold Italic Underline.docx` | Word | Cloud |

## 🚀 How to Run the Tests

### Step 1: Start Appium Server

Open a terminal and run:

```bash
appium
```

Ensure the server is running on `http://127.0.0.1:4723`.

### Step 2: Start Android Emulator

List available emulators:
```bash
emulator -list-avds
```

Start an emulator:
```bash
emulator -avd YOUR_AVD_NAME
```

Or start from Android Studio: **Tools → Device Manager → Run Emulator**

### Step 3: Execute Tests

Open a separate terminal in the project root directory.

#### Option A: Run on Default Emulator (emulator-5554)

If you are using the default Android emulator, simply run:

```bash
mvn clean test
```

#### Option B: Run on a Specific Device (Recommended)

To run the test on a specific device/emulator, pass its UDID using the `-Dudid` parameter:

```bash
mvn clean test -Dudid="YOUR_DEVICE_UDID"
```

**Examples:**

- For a specific emulator:
  ```bash
  mvn clean test -Dudid="emulator-5556"
  ```

- For a real device:
  ```bash
  mvn clean test -Dudid="R58M45Y7H"
  ```

**Find your device UDID:**
```bash
adb devices
```

## 📈 Test Reports

After execution, TestNG generates reports in the `target` directory:

- **HTML Report**: `target/surefire-reports/index.html`
- **XML Report**: `target/surefire-reports/testng-results.xml`

To view the HTML report:
```bash
open target/surefire-reports/index.html
```

## 🐛 Troubleshooting

### Common Issues

1. **Appium server not running**
    - Ensure Appium is installed: `npm install -g appium`
    - Start server: `appium`

2. **Device not detected**
    - Check connected devices: `adb devices`
    - Restart adb: `adb kill-server && adb start-server`

3. **Build failures**
    - Clean Maven cache: `mvn clean`
    - Update dependencies: `mvn clean install -U`

4. **Emulator not starting**
    - Check available AVDs: `emulator -list-avds`
    - Verify ANDROID_HOME: `echo $ANDROID_HOME`

## 📝 Notes

- Tests are designed to run on Android devices and emulators
- Ensure stable internet connection for cloud file operations
- Test account credentials should be kept secure and not committed to version control
- The framework follows Page Object Model design pattern for maintainability



