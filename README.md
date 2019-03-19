# Sky CoreGaming Test

This is my attempt at taking test provided by Sky Betting Java Server Side Engineer position.


# Contents

This repository is separated into two parts:

 - Server
 - Client

I have added basic JSON communication protocol under `http://localhost:8008/json`. 
GSON was used for more comfortable work with JSON formatted responses and requests. On top of that, RestAssured was used to test `/json` endpoit due to how easy it is to test RESTful APIs with it.

### Requirements

-   Git
-   Java 8 (JDK 8).
-   Maven 3.5.4.
-   Ability to view *.xlsx files.

# How to use

To run, simply import project as Maven project to your Eclipse environment. This will result in having 3 folders added:

 - Client
 - Server
 - Exercise

### Client
Client contains `src/test/java` and `src/main/java` packages, in which tests and main client are stored respectively.

To run tests, simply Right-click on CommunicationTest.java and Run as JUnit test. **Server has to be running beforehand**

### Server
Server contains `src/test/java` with `com.cg.Constructors` and `com.cg.Server` packages. `Constructors` package contains `RandomValues.java` class, that has been found by me on StackOverflow. I found that implementation of weighted probability elegant and efficient. It has been reused because in real world, we have access to internet and we are able to look up solutions as such. Re-usability of code is one of the most important postulates and that is why I decided to reuse previously mentioned class. JAVADOC contains reference to thread on StackOverflow.

Additionally, server has two endpoints:

 - /serve
 - /json

Response type differs depending on which endpoint has been used.

# Conclusion
This project is not to be considered a fully deployed product, nor as full extend of my knowledge. It is to show my capabilities of backend development, familiarity with such subjects as JSON, unit testing, Restful services, and such. 

Thanks for your time! :)