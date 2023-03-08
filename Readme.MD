# API tests
ScratchPayCodingAssesment has automation tests for the below mentioned APIs.
The test are written in BDD Jbehave (similar to Cucumber) framework with maven as build tool( and have the ability to generate Serenity Reports.
Here we have story files(automation test cases)are written in Plain text.
These story files then map to Java via StepDefinition files.
The StepDefinition files call the methods from the corresponding Step file methods.The actual call to the API happens at the Step file methods.

## API Details
1. Authentication API
 ```bash
https://qa-challenge-api.scratchpay.com/api/auth?email=<email>&password=<password>
```
2. Get List of Email Address API
```bash
https://qa-challenge-api.scratchpay.com/api/clinics/{practiceId}/{email}
```
3. Get List of Clinics API
```bash
https://qa-challenge-api.scratchpay.com/api/clinics?term=<term>
```

## Test Scenarios

1. Validation the Happy path for each API - with combination of valid data.
2. Validating the UnHappy path for each API - with combination of invalid data.

The story files in ``src/test/resources/stories`` have the Test scenarios written in Gherkin keywords.

## Running the tests

We can run the tests in 2 ways
1. JUnit Runner - A runner file is already created in ``` src/test/java/com/runner/TestRunner.java``` file.You can simply click the run icon next to the class.
2. Maven Command - 
   ```mvn clean compile integration-test serenity:aggregate -Dmetafilter=+Testing```

In the both the cases the tests run with the help of Meta tags(in this case "Testing") provided in the story files.

## Reports
Serenity Reports are generated only when you run through the maven commands
The reports can be found in ```target/site/serenity/index.html```


## Observations
1. ``` Scenario: Get a list of email addresses for a invalid practiceId ``` and
   ```Scenario: Get a list of email addresses without practiceId``` failed due to the invalid error messages in the API response
2. ```Scenario: Get Clinics for invalid term``` and ```Scenario: User tries to Get Clinics without any term``` failed due to error scenarios not being handled.
