# IPL UI Automation Framework

## Overview ##

This project is a Selenium WebDriver-based automation framework developed to test key UI functionalities of the IPL
website.

The framework follows the Page Object Model (POM) design pattern and includes reusable components, data-driven testing,
cross-browser support, and reporting features.

---

## Features ##

- Page Object Model (POM) architecture
- Data-driven testing using JSON files
- Cross-browser support (Chrome & Edge)
- Dynamic waits using Explicit Waits
- Extent and Allure Reports integration
- Screenshot capture on test failure
- Reusable utility classes
- Clean and modular framework design
- TestNG-based execution

---

## Tech Stack ##

- Java
- Selenium WebDriver
- TestNG
- Maven
- Jackson (for JSON parsing)
- Extent Reports

---

## Test Scenarios Covered ##

### 1. Footer Links Verification ###

- Navigate to IPL homepage
- Scroll to footer section
- Verify footer sections (TEAM, ABOUT, GUIDELINES, CONTACT)
- Validate footer links

---

### 2. Team Details Verification ###

- Navigate to Teams section
- Verify team logos are displayed
- Hover on team cards
- Validate winning years shown on hover

---

### 3. Points Table Validation ###

- Navigate to Points Table section
- Retrieve top-ranked team
- Validate matches played and points

---

### 4. Search Functionality (News) ###

- Navigate to News section
- Search for "Auction 2026"
- Verify relevant articles appear in results

---

## Setup Instructions

### 1. Clone the repository ###

git clone 

---

### 2. Import Project ###

- Open in IntelliJ / Eclipse
- Import as Maven project
- Wait for dependencies to download

---

### 3. Install dependencies ###

    mvn clean install

---

## How to Run Tests ##

### Using Maven ###

    mvn test -Pchrome
    mvn test -Pedge

---

## Using TestNG XML ##
### Execution Flow

1. Test execution is triggered using the selected TestNG XML file.
2. The XML file passes the `browser` parameter (`chrome` or `edge`) to the test classes.
3. The `BaseTest` class receives this parameter and calls `DriverFactory.initDriver(browser)`.

### Chrome Execution
`testng-chrome.xml`
- `DriverFactory` uses **WebDriverManager** to automatically download and configure the latest compatible ChromeDriver.
- Chrome browser is launched.
- The application URL is opened.
- Test cases are executed.

### Edge Execution
`testng-edge.xml`
- `DriverFactory` first attempts to use **WebDriverManager** to download and configure EdgeDriver.
- If WebDriverManager fails (e.g., version mismatch or network issue):
    - It falls back to a locally available EdgeDriver (stored in the project).
- Edge browser is launched.
- The application URL is opened.
- Test cases are executed.

---

## Cross Browser Support ##

Supported browsers:

- Google Chrome
- Microsoft Edge

---

## Reports ##

### Extent Report ###

Generated at: target/reports/index.html

---

## Allure Reports

Allure provides interactive and detailed test reports with step-level insights, attachments, and execution trends.

Generate at : target/allure-results

---

## Logging ##

Implemented using Log4j2
Logs stored at: /target/logs/framework.log

---

## Screenshot Feature ##

- Screenshots are automatically captured when a test fails
- Saved in the `target/reports` folder
- Attached to the Extent Report for easier debugging

---

## Git & Project Hygiene ##

Ignored unnecessary files:

- /target/
- /reports/
- /allure-results/
- /logs/
- Clean and structured commits