# API testing automation framework for RESTful Services

## Framework Structure 
### Dependencies 
This api automation framework depends on following 
external libraries. 
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>4.3.1</version>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>6.14.3</version>
</dependency>
```

### Framework Project Structure Diagram
```
|-reports                        #  all the generated test execution reports are here 
|-pom.xml                        #  project object model file for the maven software
|-testng.xml                     #  TestNG configuration files for the test structures and groupings 
|-src
   |---test
         |----java               #  all the java source files needs to stored in this folder 
                |-[+]testcase    #  java class package, all test class will be stored here 
                |-[+]utility     #  java class package, all the utility class will be stored here 
                |-[+]commons     #  java class package, all the commons class wil be stored here 
         |----resources          #  resources folder, json files, xml files, excel files and reports 
                |-[d]payloads    #  all the json files used in tests are stored here 
                |-[d]xml         #  all the xml files used in tests are stored here 
|-.gitignore                     #  git ignore config file 
|-READMD.md                      #  you are currently viewing this file 
```

## Pre-requisites
* Download and install Chrome or Firefox browser  ( viewing report )
* Download and install JDK v1.8 + 
* Download and install Apache Maven v3.0+
* Download and install Git v2.0+ 

## Inner Works of the Framework 
This is a diagram that details the internal structure of our framework.  Mulitple parts of the
code works together to bring successful automated test executions. 
![screenshot](/images/Framework.png)

## Set-up Instructions 
You need to have following test execution set up 
![screenshot](/images/test_execution_setup.png)


## How to write Test Cases 

## How to run Tests 
All the test triggering is done through maven commands, this framework supports multiple different types of 
test executions such as smoke, regression, and end-to-end on different possible environment such as QA, Staging, and 
UAT. 
#### Executing specific tests 
If you would like to exeucte a specific test that are stated on testng.xml file
Choose from the following command: 

For invoking somke test"
```shell script
mvn test -Dtestof="Smoke"
```

If you would like to execute multiple different types of test stated on testng.xml file 
```shell script
mvn test -Dtestof="test1", "test2", "test3"
```

If you would like to execute a specific test on specific environment ( default=UAT ) 
```shell script
mvn test -Dtestof="E2E" -Denv="Staging"
```

## How to get Report 
All the test execution reports are avaialbe as HTML report on following folder after test execution 
```
 report
   |--[HTML] reports
```