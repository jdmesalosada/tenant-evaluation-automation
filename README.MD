# Selenium Test Automation Framework with Gradle

This project is a scalable test automation framework built with **Java**, **Selenium WebDriver** and **Gradle**. 

---

## Tech Stack

- **Java 17** 
- **Selenium WebDriver**
- **Gradle**
- **JUnit**
- **Allure** or native reporting (optional)
---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/jdmesalosada/tenant-evaluation-automation.git
cd tenant-evaluation-automation
```

### 2. Run the tests

```bash
gradlew clean test -Denv=qa
```

Short Questions
Answer these in a separate file or within code comments:
#### 1. How would you integrate this test suite into a CI/CD pipeline (e.g., GitHub Actions,
   Jenkins)?

```
In order to integrate this test suite with jenkins I would create a jenkins pipeline with build parameters to run whether manually or automatically based on a trigger event.
```


#### 2. What would be your approach to scaling this framework for a large application?

```
I would separate the code in different layers: data layer, business layer, utilities and page objects, also I would add a logger as log4j in order to improve the observability of the tests.
It is possible I would create a base package in order to reuse logic that is repetitive accross the application.
```

#### 3. What quality metrics would you track and report on as a QA Leader?

```
I would track the test automation coverage: % of total test cases automated.
How much time we spend running the regression.
How many critical bugs we found in production 
```