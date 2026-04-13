
## About Me
Hi, I am Abhishek Vaidya, I have 8 plus years of experience in AUtomation testing using technologies like Selenium Webdriver and Java.


# Test Automation Framework

This project is a Java-based Test Automation Framework designed to support scalable, maintainable, and efficient automated testing. It follows modern best practices including data-driven testing, cloud execution, and headless browser support.


## Author

- [@AbhiVaidya1](https://github.com/AbhiVaidya1)
- EmailAddress: vaidyaabhi20@gmail.com


## Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/AbhiVaidya1)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)



## Prerequisites

Ensure the following are installed:

- **Java 11**
- **Maven** (3.x or above)
- **IDE** (IntelliJ / Eclipse recommended)
- **Internet connection** (for LambdaTest execution)

## Features

- **Test Execution**
Supports execution via:
Local machine
LambdaTest cloud platform
Configurable execution using Maven CLI parameters
- **Data-Driven Testing**
Supports multiple formats:
CSV (OpenCSV)
JSON (Gson)
Excel (Apache POI)
- **Headless Execution**
Faster execution using headless browser mode
- **Cloud Testing**
Seamless integration with LambdaTest
Cross-browser and parallel execution support
- **Reporting & Logging**
Extent Reports for detailed HTML reports
Log4j for structured logging

## Technologies used
The framework is built using:

- Java 11
- TestNG – Test execution & management
- Maven – Build & dependency management
- Selenium WebDriver – UI automation
- LambdaTest – Cloud-based cross-browser testing
- Extent Reports – Rich HTML reporting
- Log4j – Logging
- Faker – Test data generation
- OpenCSV, Gson, Apache POI – Data-driven testing support

## Setup Instructions

**Clone the Repository:**

```bash
  git clone https://github.com/AbhiVaidya1/Test-Automation-Framework.git

  cd Test-Automation-Framework
```
**Install Java**    
Verify installation:
```bash
java -version
```
Expected output should show Java 11.

**Install Maven**  
Required: Maven 3.x or above

Verify installation:
```bash
mvn -version
```

## Run Tests
- **Run Locally**
```bash mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=false```
- **Run in Headless Mode**
 ```bash mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true```
- **Run on LambdaTest Cloud**
```bash mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false -X```

## Reporting
- **Extent Report**
Generated after execution
- File location:
report.html
- Provides:Test summary
Pass/Fail status
Screenshots (if configured)

## Logging
Logs are generated using Log4j
- Location:
logs/
Useful for debugging and execution tracing

## Configure LambdaTest (For Cloud Execution)

If you plan to run tests on LambdaTest, set your credentials:

- Option 1: Environment Variables (Recommended)
export LT_USERNAME=your_username
export LT_ACCESS_KEY=your_access_key
- Option 2: Windows (Command Prompt)
set LT_USERNAME=your_username
set LT_ACCESS_KEY=your_access_key

## Integrated the project with GitHub Actions
This automation framework  is integrated with github actions.
The tests will be executed at 11.30 OM IST every single day.

The reports will be archieved in gh-pages branch
You can view the reports at:
https://abhivaidya1.github.io/Test-Automation-Framework/report.html
