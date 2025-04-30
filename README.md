## Chapter 1:
* Created the initial project structure using Spring Initializr;
* Wrote a controller class to handle the request to the home page;
* Defined a view template to display the home page;
* Wrote a simple test class to verify the functionality of the home page controller.
* Successfully ran the project

## Chapter 2:
* Created domain models
* Created Views
* Created Controllers 
* Added validation

## Chapter 3.1:
![img.png](img.png)
* Prepared storage objects (added ID)
* Added H2 database, disabled database name generation
* Added spring-boot-starter-jdbc
* Created interfaces for repositories
* Implemented the interfaces (@Repository, injected JdbcTemplate via constructor)
* Added script files scheme.sql and data.sql (executed at startup)
* Created methods for saving data; saving complex data this way requires a lot of code
* Used repositories in controllers  