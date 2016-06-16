# Berlin Prague trip

Let's make sure that we can get best deals at Goeuro for a trip from Berlin to Prague. 

## How to start? 

To run tests you need to have **Maven**, **Chrome** browser and **Java** installed on the machine. Tests are implemented to be running in Chrome
browser initiauly. Please make sure that **Chrome** and **Maven** executables are on your ```$PATH```. 

Running tests is quite straitforward:

```bash 
mvn clean test
```

After that **Chrome** instance should open and execute a couple of simple scenarios. 

## Design approach

Tests were created using Page Object pattern. Logic and selectors are located in Page Objects, 
in the test suites we just combine test scenarios. It allows to mantain a codebase easier and avoid code duplications. 









