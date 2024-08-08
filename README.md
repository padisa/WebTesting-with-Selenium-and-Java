# WebTesting-with-Selenium-and-Java

##Overview
This project demonstrates how to use Selenium and Java for automation of web app. It includes test cases and configuration for running tests in different browsers.

##Features
- Support for multiple browsers (Chrome, Firefox, Edge, Safari)
- TestNG framework for test execution
- Test automation examples for sample web app
- Generating report for final execution

##Prerequisites
Before you run this project, ensure you have met the following requirements:
- **Java Development Kit (JDK)**: Install JDK 11 or higher.
- **Maven**: Ensure Maven is installed for dependency management and build automation.
- **IDE**: Use an Integrated Development Environment like IntelliJ IDEA.

##Setup
Follow these steps to set up the project on your local machine:
1. **Clone repository**: *git clone git@github.com:yourUsername/WebTesting-with-Selenium-and-Java.git*
2. **Navigate to the project directory**: *cd WebTesting-with-Selenium-and-Java*
3. **Install dependencies**: Run command *mvn clean install -Dbrowser = chrome*
4. When running the test in IntelliJ you need to set configuration for WebDriver: *f.e. -Dbrowser = firefox*

##Project Structure
- **src/test/java/**: Contains the test cases and test suite configurations.
- **resources/Configurations/**: Contains configuration files for application settings and credentials.
- **pom.xml**: Maven configuration file that includes dependencies and build settings.