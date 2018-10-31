# Spek Spring Utils
The goal of this project is to make testing Spring applications painless when using Spek as a testing framework. Obviously the name and initial goal of MockMVC testing comes from [spock-spring-utils](https://github.com/jarlehansen/spock-spring-utils). This started as me just hacking away in the evenings and learning more about Spring (out of need, because the hassle of using it with Spek bothered me), so it may have some issues. I still hope that it may evolve into something usable in the near future. So far only tested with Spring Boot, but should work on old Spring as well. I probably have to test it and maybe fix some dependency handling, as well as cleaning the code. 

## NOTE: This is an early version and may have issues. Feel free to report any issues you may have if you try it! Note that there may be some obvious improvements that can be done in the code, so take that into consideration before reporting. This project used Spek2, so there may be (and probably are) incompatibilities to the old version of Spek.


## TODOs and missing functionality
This project is a work-in-progress, and things may change. Below is a list of bullet points I plan to investigate and implement. If you have any good solutions to these, please feel free to fork the repo and create a pull request :smile: 

* Clean the Gradle scripts. I have never used Gradle beyond simple experiments, so I may have some weirdness in there. Let me know, and I will learn something new :) 
* More tests to document usage. 
* Supporting only having one Spring application context for several specifications/tests (if needed)
* Making the use of the springContext DSL more user-friendly. Examples include not having to write the package name every time, but instead just use the package it is used from.
* Checking up on memory issues. Some memory issues may happen when handling Spring application context manually. Some care should be made to see that it's not the case here (or if it is, fix it!). Close should have fixed most of these, especially in newer versions of Spring.
* Adding option to use XML configuration without making the DSL suffer


## Maven / Gradle depenency
Want to try this testing library in your project? Feel free to :) Either by building the latest version on Github yourself, or by using the dependencies from the Bintray repo.

### Maven dependency
You first need to add the Bintray repository for this project:
```xml
    <repositories>
        <repository>
            <id>spek-spring-utils</id>
            <url>https://dl.bintray.com/themkat/spek-spring-utils</url>
        </repository>
    </repositories>
```

Then simply add the dependency like normal:
```xml
        <dependency>
            <groupId>net.themkat.spek.spring.utils</groupId>
            <artifactId>spek-spring-utils</artifactId>
            <version>0.1.0</version>
        </dependency>
```


### Gradle dependency
Like Maven, you first need to add the Bintray repository for this project:
```
repositories {
    maven {
        url  "https://dl.bintray.com/themkat/spek-spring-utils" 
    }
}
```

Then simply add the dependency like normal:
```
testCompile("net.themkat.spek.spring.utils:spek-spring-utils:0.1.0")
```


## Dependencies needed
This project includes no Spring related dependencies, because Spring is notorious for version-incompatibilities. The following dependencies are needed:
* org.springframework:spring-test
* org.springframework:spring-context
* org.springframework:spring-tx

Will probably be updated as Spring Boot dependencies was used for this project. If you use Spring Boot, then you can use the following dependencies:
* org.springframework.boot:spring-boot-starter-test
* org.springframework.boot:spring-boot-starter-data-jpa

You will also need the Kotlin standard library and the Kotlin-reflect package. 


### Running the Spek tests with Maven
The easiest way to run the tests is without a doubt the Spek plugin for IntelliJ IDEA, but you may want it as part of your build pipeline in CI systems (or maybe you just enjoy the terminal like me ;) ). To be able to run your Spek tests using `mvn clean install`, `mvn test` or something similar, you will have to add a Test Engine. Spek 2 uses JUnit 5, so the natural choice for me is to add a dependency to that engine (the version is simply there as an example):

```xml
		<dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>5.3.1</version>
			<scope>test</scope>
		</dependency>
```

You may also want to configure the Maven Surefire plugin to pick up your tests if you use a different naming strategy than Test at the end of the name. I prefer to use Spec at the end of the names in my tests (because I read them as specifications almost more than tests). To make Surefire plugin pick up your tests, you can add the following to your build plugins (Surefire version added just as an example):

```xml
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.0</version>
                <configuration>
                    <includes>
                        <include>**/*Spec.*</include>
                    </includes>
                </configuration>
            </plugin>
```


## Usage examples
Separate examples will be added here. To get started quickly, take a look in the test-folder for examples. Different examples will be added below.


### Testing DAOs/JPA-repositories
...

### MockMVC
...
