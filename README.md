# web-crawler
Demo web crawler that produces page map for given domain.

# Environment

* Java 11 (OpenJDK distribution (openjdk 11.0.2))
* Gradle 5.5

# Development setup

Make sure that Lombok plugin is installed and `Enable annotation processing` option is enabled in IDE.  

# How to

* Clean project: ./gradlew clean
* Build project: ./gradlew build
* Run tests: ./gradlew clean test
* Run application: ./gradlew bootRun

# Business requirements

#### Requirement 1 
As a **Customer**  
I would like to get **mocked page map with links for given domain**   
so that I will **know page map initial contract**.  

#### Requirement 2 
As a **Customer**  
I would like to get **page map with all links from single URL (domain)**   
so that I will **know what sub-pages are available for given URL**.