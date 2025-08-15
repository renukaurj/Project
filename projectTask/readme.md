## Tech Stack

- **Java 11+**
- **Gradle** (Build tool)
- **Selenium WebDriver**
- **TestNG** (Testing framework)
- **ChromeDriver** (for running tests in Chrome)

## Tasks
-----------------------------------------------------------------------------------------------------------------
#  Task 1
-- Create Test Automation Framework for a large enterprise web application.
   To implement a feature that verifies the integrity of all links on a given web page,
   identifying broken links (HTTP 4xx or 5xx errors) for reporting.
-----------------------------------------------------------------------------------------------------------------
#  Task 2
-- You're automating a highly secure, JavaScript-heavy web application(e.g, a banking portal)
   where traditional methods like element sendKeys("text") are either blocked, ineffective, or intentionally
   disabled to prevent automation. 
   You are tasked with validating the fields and entering text into a textbox(e.g., username, search field)
   without using sendKeys().
-----------------------------------------------------------------------------------------------------------------
#  Task 3
-- You're automating UI tests for a web application that behaves inconsistently across different environments
   (e.g., uat,production). Your team needs a screenshot capture utility that:
   1. Automatically takes full-page screenshots when a test fails.
   2. Saves screenshots with:
            Dynamic names(e.g., with timestamp and testmethod name)
            Organised folder structure
   3. Works with your test framework(e.g., TestNG).
   4. Is reusable across multiple test classes.

-----------------------------------------------------------------------------------------------------------------
#  Task 4
-- How would you write a custom ExpectedCondition to wait for:
   1. A spinner to disappear
   2. A specific element to change its color or state
-----------------------------------------------------------------------------------------------------------------


##  Project Structure

project-root/
├── build.gradle
├── README.md
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── base/                  # WebDriver setup, TestBase, Hooks, etc.
│   │   │   ├── config/                # Configuration readers, environment setup
│   │   │   ├── pageObject/            # Page classes with WebElements and actions
│   │   │   ├── tests/                 # All test classes
│   │   │   │   ├── BrokenLinks.java
│   │   │   │   ├─  FullScreenshotTask3.java
│   │   │   │   ├─  Task4.java
│   │   │   │   ├── SpinnerTask4.java
│   │   │   │   └── TextWithoutSendKeysTask2.java
│   │   │   └── utils/                 # Reusable utility classes like JSExecutor, WaitUtils
│   │   └── resources/
│   │       ├── testData/              # JSON test data
│   │       │   └── data.json
│   │       ├── configurationURL.yaml  # Environment URLs and other config
│   │       └── testng.xml             # TestNG suite config file

## Run Test

- src-->test-->resources-->testng.xml
- right click on testng.xml
- click on run option
