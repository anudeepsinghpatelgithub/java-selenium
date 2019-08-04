# java-selenium 2 framework

# Prerequisite 
1. Make sure java and maven are installed
2. supported version of chrome/firefox should be installed


# How to run
1. Clone this prject
2. go to root directory of the cloned project
3. go to `resources` and update the value for parameters in TestNg.xml
```xml
<parameter name="fbEmail" value="" />
<parameter name="fbPassword" value="" />
<parameter name="whEmail" value="" />
<parameter name="whPassword" value="" />
<parameter name="whUserName" value="" />
```
3. go to `resources` folder and run `chmod 777 chromedriver`
4. and come to back to root directory again
5. run `mvn clean`
6. run `mvn install`


# Note:
1. If broswer is opening and url is not typed then it means we need to update/downgrade the chrome/firefox version. 
2. Selenium version(get it from pom.xml) and browser are not supported

# Upcomming...
1. HTML Reproter with screenshot and recording
2. Dockerzing these tests
3. Running in Headless mode
4. TestLink integrartion
5. CI/CD(Jenkins) integration
6. Email Alerts


