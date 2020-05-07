# Exception Java example

This Java project shows you how to catch custom exceptions. This project was done with Spring Boot 2 to take advantage of the annotation. 

# Exceptions

We have an ExceptionGlobalResponse to catch RuntimeException and Exception an annotation through the **@ControllerAdvice** annotation. We also created the YoungAgeException class that contains my own RuntimeException exception. 

## To throw customize exception

```java
//throw with object
throw YoungAgeException.of(young);
```
```java
//throw with specific message
throw new YoungAgeException("Age cannot exceed");
```
When that type of exception is thrown, ExceptionGlobalResponse catches because it has the **@ExceptionHandler** with this specific **YoungAgeException** annotation and displays a custom response.