# Lab 2 Web Server -- Project Report
**Author:** Ariana Porroche Llorén (874055)

**Date:** 08th October 2025

**Course:** Web Engineering


## Description of Changes
### Mandatory tasks
- Customized the **Whitelabel Error Page** by creating `error.html` in `src/main/resources/templates`, so the application displays a friendly error page instead of the default Spring Boot page.  
- Added a new **/time endpoint** in `TimeController.kt` to return the current server time as a structured JSON DTO (`TimeDTO`).  
- Implemented **TimeProvider interface** and `TimeService` with dependency injection, allowing flexible and testable time retrieval.  
- Added **extension function `toDTO()`** to transform `LocalDateTime` into `TimeDTO` cleanly.  
- Enabled **HTTP/2 and SSL** support by configuring a self-signed certificate in `application.yml` and using a PKCS12 keystore (`localhost.p12`) with environment variable `$env:KEYSTORE_PASSWORD`.  

### Bonus opportunities
7. **Implement Swagger/OpenAPI Documentation**
    - Implemented **Swagger/OpenAPI documentation** for the `/time` endpoint.  
    - Added **`SwaggerConfig.kt`** to configure OpenAPI metadata (title, version, description, contact, license).  
    - Updated **`TimeController.kt`** with `@Operation` and `@ApiResponse` annotations, including example response objects.  
    - Integrated **HTTP Bearer security scheme** in Swagger UI.  
    - Verified **Swagger UI** is accessible at `/swagger-ui/index.html` and API JSON spec at `/v3/api-docs`.  
    - Optional endpoints such as `/actuator` are hidden from Swagger automatically.  

    Documentation available at:  
        - Swagger UI: [https://localhost:8443/swagger-ui/index.html](https://localhost:8443/swagger-ui/index.html)  
        - OpenAPI JSON: [https://localhost:8443/v3/api-docs](https://localhost:8443/v3/api-docs)

9. **Use Profiles to Manage Configurations**
    - Implemented Spring Boot profiles (`dev`, `prod`) to manage environment-specific configurations.
    - Added profile-specific services:
        - `DevTimeService` returns a fixed deterministic time for development/testing.
        - `ProdTimeService` returns the current server time in production.
    - Created configuration files per profile:
        - `application-dev.yml`
        - `application-prod.yml`
    - Added integration tests using `@ActiveProfiles` to verify:
        - Correct profile-scoped bean is loaded.
        - Sensitive properties (like keystore password) are loaded from environment variables.
        - Profile-specific properties (server port, OpenAPI server URLs) are applied.
        - Test-time property overrides using `DynamicPropertySource`. 



## Technical Decisions
### Mandatory tasks
- Used **Spring Boot 3.5.3 with Kotlin 2.2.10** for modern language features and rapid development.  
- Chose **Thymeleaf** for rendering the custom error page because it integrates seamlessly with Spring Boot templates.  
- Configured **HTTP/2 and SSL** to improve performance and secure communication.  
- Used **dependency injection** and interfaces (`TimeProvider`) to allow future mocking/testing without changing controller logic.  
- Used **Jackson with jackson-module-kotlin** to serialize Kotlin data classes automatically.  
- Relied on **Ktlint** to enforce consistent Kotlin coding style and prevent build errors.  
- Managed sensitive information (**keystore password**) through environment variables instead of hardcoding.  

### Bonus opportunities
7. **Implement Swagger/OpenAPI Documentation**
    - Chose **Springdoc OpenAPI** for automatic documentation generation with Spring Boot.
    - Used annotations in controller for endpoint documentation and example responses rather than writing manual YAML/JSON files.
    - Configured **bearer authentication** to demonstrate secure endpoints.
    - Example responses included via **ExampleObject** to provide realistic API output.

9. **Use Profiles to Manage Configurations**
    - Decided to leverage Spring Boot **profiles** to isolate environment-specific settings (`dev`, `prod`, `test`) and profile-scoped beans.  
    - Tests ensure **correct bean selection** per profile and **sensitive data loading** from environment variables.  
    - Profiles simplify **deployment across different environments** without changing the codebase.  
    - `DynamicPropertySource` used to **override properties at test runtime**, ensuring reproducible test results.


    - Used environment variable $env:KEYSTORE_PASSWORD to avoid hardcoding sensitive SSL credentials.

Before executing (in PowerShell):
$env:KEYSTORE_PASSWORD = "secret"



## Learning Outcomes
[What you learned from this assignment]

## AI Disclosure
### AI Tools Used
- [List specific AI tools used]

### AI-Assisted Work
- [Describe what was generated with AI assistance]
- [Percentage of AI-assisted vs. original work]
- [Any modifications made to AI-generated code]

### Original Work
- [Describe work done without AI assistance]
- [Your understanding and learning process]