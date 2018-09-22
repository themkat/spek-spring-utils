# Spek Spring Utils
The goal of this project is to make testing Spring applications painless when using Spek as a testing framework. Obviously the name and initial goal of MockMVC testing comes from [spock-spring-utils](https://github.com/jarlehansen/spock-spring-utils). This started as me just hacking away in the evenings and learning more about Spring (out of need, because the hassle of using it with Spek bothered me), so it may have some issues. I still hope that it may evolve into something usable in the near future. So far only tested with Spring Boot, but should work on old Spring as well. I probably have to test it and maybe fix some dependency handling. 


## TODOs and missing functionality
This project is a work-in-progress, and things may change. Below is a list of bullet points I plan to investigate and implement. If you have any good solutions to these, please feel free to fork the repo and create a pull request :smile: 

* Restructure code base to be cleaner / more readable. This includes some DSL methods which are a little bit badly named now (especially in MockMvc parts where it turns confusing)
* Supporting only having one Spring application context for several specifications/tests
* Making the use of the springContext DSL more user-friendly. Examples include not having to write the package name every time, but instead just use the package it is used from.
* Checking up on memory issues. Some memory issues may happen when handling Spring application context manually. Some care should be made to see that it's not the case here (or if it is, fix it!)
* Adding option to use XML configuration without making the DSL suffer
* Registering beans manually



## Usage examples
Separate examples will be added here. To get started quickly, take a look in the test-folder for examples. Below will be different examples I will test on other package names in other modules for additional testing.


### Testing DAOs/JPA-repositories
...

### MockMVC
...