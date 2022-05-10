# Backend challenge
Welcome to Bankin's Java backend challenge.

You will be adding to the existing code of this project. Setting up the project requires Java 11 and IntelliJ CE (https://www.jetbrains.com/idea/download).

## Running the project
The project builds and runs in its initial state. You can start it by typing `./gradlew bootRun` in the console. In IntelliJ, you can run it by creating a new "Run configuration" for the Gradle project "challenge-backend" with the command `bootRun`. This will allow you to use breakpoints for debugging.

The command `./gradlew build` will build the project and run the unit tests.

## The challenge
The challenge consists in: 
* Integrating the `List account` endpoint from our Bridge API (https://docs.bridgeapi.io/v2018.06.15/reference#list-accounts). The calls are already partially implemented in the `BridgeClient.java` class.
* Using the payload returned by our API response, the endpoint `/mycontroller/myroute` from this project is supposed to return:
  * All the accounts of the user
  * The sum of the user's accounts which are of the type `CHECKING` or `SAVINGS`, rounded to the upper hundred 
⚠️ this business rule is important as it's an easy way to demonstrate unit testing

Notes:
* Most of the code is already in place, it's mostly a matter of fixing and renaming things. Please read the aforementionned `List account` documentation carefully.
* You're more than welcome to rename classes, methods, fields and REST routes
* Since there is a tiny bit of business logic to implement, some unit tests are most welcome
* **The credentials necessary for authenticating to the Bridge API will be provided to you in the email sent to you for the exercise. `client_id` and `client_secret` are to be configured in `application.yml`. The user's credentials are hardcoded in the `BridgeService.java` class.**
