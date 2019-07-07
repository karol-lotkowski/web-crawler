# web-crawler
Demo web crawler that generates page map for given domain.

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

# API client (Swagger UI)

1. Run application: `./gradlew bootRun`  
2. Open `http://localhost:8080/swagger-ui.html` in browser window  
3. Define `domain` param and `Execute` request

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

#### Requirement 8 
As a **Consumer**  
I would like to **generate page map via HTTP request (REST API)**   
so that page maps **would be possible to generate over HTTP protocol**.

#### Requirement 9 
As a **Consumer**  
I would like to **have access to API documentation and be able to perform test requests**   
so that Developer **can familiar with API and test API**.

#### Requirement 10 
As a **Product Owner**  
I would like to **what can be improved in application**   
so that web crawler **will provide more business value for Customers and Developers**.

# Further extensions

1. Add more test cases.  
Extend current test classes with border cases (e.g. wrong input, exceptions). 

2. Timeout scraping in situation when page loads too long.

3. Handle exceptions on API level.  
Use `@ExceptionHandler` and generate proper error output.

4. Handle API requests in reactive way via `Spring WebFlux`.  

5. Map domain object `PageMap` to `DTO` object in API layer.

6. Provide validation on API params to be sure that valid domain address passed.

7. Extend output by more static content types (e.g. docs, scripts).

8. Include pages for which scraping error occurred and such page is excluded from final output.

9. Measure service/API performance (e.g. response time, resources consumption, #RPS/s).

10. Provide info about total scraping time per domain.

11. Next to the domain provide parameter to exclude some websites (e.g. blogs, pressroom, etc.)

12. Dockerise application to use unified runtime environment.  