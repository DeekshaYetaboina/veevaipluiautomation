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
- Extent Reports integration
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

    mvn test

---

### Using TestNG XML ###

For Chrome: testng-chrome.xml

For Edge: testng-edge.xml


---

## Cross Browser Support ##

Supported browsers:

- Google Chrome
- Microsoft Edge

Configured using TestNG parameter:

```xml

<parameter name="browser" value="chrome"/>
```

---

## Extent Report ##

Generated at: /reports/index.html

---

## Screenshot Feature ##

- Screenshots are automatically captured when a test fails
- Saved in the `/reports` folder
- Attached to the Extent Report for easier debugging  