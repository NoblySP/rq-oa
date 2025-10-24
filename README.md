# ReliaQuest's Entry-Level Java Challenge

## Design Decisions

Here are a few of the technical decisions I made/considered while building this project:

* **Used `HashMap` instead of `List` for the database**
    * I used a `HashMap<UUID, Employee>` to store the mock data instead of a simple `List`. This provides much better performance ($O(1)$) for the `getEmployeeByUuid` endpoint, as it allows for a direct lookup by ID instead of iterating through a list ($O(n)$).

* **Exception Handling: Global vs. Controller-Specific**
    * I chose to implement a global exception handler using `@ControllerAdvice`. Even though the project has only one controller (where a local `@ExceptionHandler` would have worked), the global approach is a better design choice. It centralizes all error-handling logic and keeps the controller code clean.

* **Avoided implementing `EmployeeService`'s interface**
    * While using an `EmployeeService` interface is a better practice for larger applications, I felt that it would have added unnecessary boilerplate for a this simple project. Hence, I opted for just using the `EmployeeService` as a class.

* **Avoided returning `ResponseEntity` for successful operations in the Controller**
    * The `createEmployee` endpoint returns a plain `Employee` object. I considered returning a `ResponseEntity<Employee>` with an `HttpStatus.CREATED` (201) status because it is a more specific response description. However, I chose to stick to the return type specified in the original template (and its comments).


## Sample Requests

This section provides sample `curl` commands to test and interact with the live API endpoints. The `-s` flag is used for silent mode (to hide the progress meter) and `-i` is used to include the HTTP response headers (e.g. HTTP status code).

```bash
# Successful HTTP GET Request to fetch all employees. You can pipe it to `jq` for better readability.
curl -s http://localhost:8080/api/v1/employee/
curl -s http://localhost:8080/api/v1/employee/ | jq

# Successful HTTP POST request to create a new employee. 
curl -s -X POST http://localhost:8080/api/v1/employee/ -H "Content-Type: application/json" -d '{"uuid":"11111111-1111-1111-1111-111111111111","firstName":"Bob","lastName":"Watson","salary":65000,"age":28,"jobTitle":"Manager","email":"bob@company.com","contractHireDate":"2024-06-01T00:00:00Z","contractTerminationDate":null}' -i

# Unsuccessful HTTP POST request to create a new employee (provided you ran the previous command already).
curl -s -X POST http://localhost:8080/api/v1/employee/ -H "Content-Type: application/json" -d '{"uuid":"11111111-1111-1111-1111-111111111111","firstName":"Bob","lastName":"Watson","salary":65000,"age":28,"jobTitle":"Manager","email":"bob@company.com","contractHireDate":"2024-06-01T00:00:00Z","contractTerminationDate":null}' -i

# Successful HTTP GET Request for a specfic employee UUID.
curl -s http://localhost:8080/api/v1/employee/11111111-1111-1111-1111-111111111111 -i
# Unsuccessful HTTP GET Request for a specfic employee UUID.
curl -s http://localhost:8080/api/v1/employee/11111111-1111-1111-1111-111111111112 -i

```

---
---

Please keep the following in mind while working on this challenge:
* Code implementations will not be graded for **correctness** but rather on practicality
* Articulate clear and concise design methodologies, if necessary
* Use clean coding etiquette
  * E.g. avoid liberal use of new-lines, odd variable and method names, random indentation, etc...
* Test cases are not required

## Problem Statement

Your employer has recently purchased a license to top-tier SaaS platform, Employees-R-US, to off-load all employee management responsibilities.
Unfortunately, your company's product has an existing employee management solution that is tightly coupled to other services and therefore 
cannot be replaced whole-cloth. Product and Development leads in your department have decided it would be best to interface
the existing employee management solution with the commercial offering from Employees-R-US for the time being until all employees can be
migrated to the new SaaS platform.

Your ask is to expose employee information as a protected, secure REST API for consumption by Employees-R-US web hooks.
The initial REST API will consist of 3 endpoints, listed in the following section. If for any reason the implementation 
of an endpoint is problematic, the team lead will accept **pseudo-code** and a pertinent description (e.g. java-doc) of intent.

Good luck!

## Endpoints to implement (API module)

_See `com.challenge.api.controller.EmployeeController` for details._

getAllEmployees()

    output - list of employees
    description - this should return all employees, unfiltered

getEmployeeByUuid(...)

    path variable - employee UUID
    output - employee
    description - this should return a single employee based on the provided employee UUID

createEmployee(...)

    request body - attributes necessary to create an employee
    output - employee
    description - this should return a single employee, if created, otherwise error

## Code Formatting

This project utilizes Gradle plugin [Diffplug Spotless](https://github.com/diffplug/spotless/tree/main/plugin-gradle) to enforce format
and style guidelines with every build.

To format code according to style guidelines, you can run **spotlessApply** task.
`./gradlew spotlessApply`

The spotless plugin will also execute check-and-validation tasks as part of the gradle **build** task.
`./gradlew build`
