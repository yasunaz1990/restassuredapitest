# API Testing Automation Framework
Lightweight API testing automation framework that utilizes RestAssured library for sending and 
parsing HTTP requests and responses.  It offers flexible command-based test environment and test 
type selection capabilities and automatically generates an HTML based test execution report after each test execution.  
Also, it updates the test case execution status of corresponding automated manual test cases in JIRA.


### Pre-requisites
You're system must have following tools and plugins to be able to use this framework. 
* Download and install Chrome or Firefox browser  ( viewing report )
* Download and install JDK v1.8 + 
* Download and install Apache Maven v3.0+
* Download and install Git v2.0+ 


### Project Dependencies 
This test automation framework depends on following 
external libraries.
- API Automation :  RestAssured Library 
- JSON data qury :  JsonPath Library
- Test case creation & management :   TestNG Library 
- Test Execution Report :  Extent Spark Report 
- Test Data Generation :  Faker Library 

### Project Folder Structure 
```
|-reports                        #  stores all the generated test execution reports 
|-pom.xml                        #  project object model file for the maven project configuration
|-testng.xml                     #  configuration files for the test structures and test case managements 
|-src
   |---test
         |----java               
                |-[+]testcase    #  all test class that contains test cases are here
                |-[+]utility     #  all the utility class are here
                |-[+]commons     #  all the commonly used class are here  
         |----resources         
                |-[d]payloads    #  all the json files used in tests are stored here 
              
|-.gitignore                     #  git ignore config file 
|-READMD.md                      #  you are currently viewing this file 
```


### Framework Class Diagrams 
![internals](/images/Framework.png)


### Set-up Instructions 
You can use this framework by integrating into existing Jenkins pipeline. Recommended jenkins set-up is as
follows:
![screenshot](/images/test_execution_setup.png)


### Test Triggering Commands
All the test triggering is done through maven commands, this framework supports multiple different types of 
test executions such as smoke, regression, and end-to-end on different possible environment such as QA, Staging, and 
UAT.

If you would like to exeucte a specific test that are stated on testng.xml file
Choose from the following command: 

For invoking Smoke test"
```
mvn test -DtestType="Smoke"
```

If you would like to execute a specific test on specific environment ( default=UAT ) 
```
mvn test -Dtestof="E2E" -Denv="Staging"
```

