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
I would like to get **page map with all links from single domain URL**   
so that I will **know what sub-pages are available for given URL**.

#### Requirement 3 
As a **Customer**  
I would like to get **page map with all external links from single domain URL**   
so that I will **know what external links are available for given URL**.

#### Requirement 4 
As a **Customer**  
I would like to get **page map with links to static content from single domain URL**   
so that I will **know what static content (images, pdfs) is available for given URL**.

#### Requirement 5 
As a **Customer**  
I would like to get **page map for all pages under given domain URL**   
so that I will **know links and static content for each sub-page within given domain**.

#### Requirement 6 
As a **Customer**  
I would like to get **page map 50% faster**   
so that I can **generate more page maps in the same time period**.

#### Requirement 7 
As a **Product Owner**  
I would like to **control used resources (CPU & memory) during scraping process**   
so that web crawler **re-uses resources (threads) and do not over-uses resources**.