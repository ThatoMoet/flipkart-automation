# Flipkart Test Automation

## Prerequisites
- Java 17
- Maven
- Chrome browser

## Setup
1. Clone the repository
2. Run `cd flipkart-utilities && mvn clean install`
3. Run `cd flipkart-automation-project`
4. Update `src/test/resources/config/config.properties` with your settings

## Run Tests
mvn test

## Run Specific Groups
mvn test -Dgroups=smoke
mvn test -Dgroups=regression


## Run Headless
Set `headless=true` in `config.properties`

## Reports
After running, open `test-output/ExtentReport.html` in a browser