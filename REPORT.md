# Lab 2 Web Server -- Project Report
**Author:** Ariana Porroche Llorén (874055)

**Date:** 14th October 2025

**Course:** Web Engineering


## Description of Changes
### Mandatory tasks
- Customized the **Whitelabel Error Page** by creating `error.html` in `src/main/resources/templates`, so the application displays a friendly error page instead of the default Spring Boot page.  
- Added a new **/time endpoint** in `TimeController.kt` to return the current server time as a structured JSON DTO (`TimeDTO`).  
- Implemented **TimeProvider interface** and `TimeService` with dependency injection, allowing flexible and testable time retrieval.  
- Added **extension function `toDTO()`** to transform `LocalDateTime` into `TimeDTO` cleanly.  
- Enabled **HTTP/2 and SSL** support by configuring a self-signed certificate in `application.yml` and using a PKCS12 keystore (`localhost.p12`) with environment variable `$env:KEYSTORE_PASSWORD`.  

### Bonus opportunities
3. **Verify Response Compression**
    - Implemented integration tests to check **HTTP response compression** when the client requests it:
        - The test sends a GET request to `/time` with the header `Accept-Encoding: gzip`.
        - Asserts that the response includes the header `Content-Encoding: gzip`, confirming compression.
        - Ensures that the header `Vary: Accept-Encoding` is always present, which is necessary for correct HTTP caching behavior.
        - Additional test confirms that small responses not meeting the compression threshold are not compressed, while still returning the `Vary` header.

| Nº execution        | Without compression | With compression    |
|---------------------|---------------------|---------------------|
| 1                   | 0.002149 s          | 0.002938 s          |
| 2                   | 0.002392 s          | 0.003858 s          |
| 3                   | 0.003101 s          | 0.003254 s          |
| 4                   | 0.002108 s          | 0.003178 s          |
| 5                   | 0.002790 s          | 0.003310 s          |
| TOTAL               |                     |                     |

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
- Used environment variable `$env:KEYSTORE_PASSWORD` to avoid hardcoding sensitive SSL credentials.

### Bonus opportunities
3. **Verify Response Compression**
    - Decided to test **HTTP response compression** via integration tests, verifying:
        - The `Content-Encoding: gzip` header is present when requested.
        - The `Vary: Accept-Encoding` header is always returned.
        - Small responses below the compression threshold are not compressed, preserving correctness.

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


## Learning Outcomes
Through this lab, I learned how to build and test a Spring Boot application with advanced features such as SSL, HTTP/2, compression, and Swagger documentation.
I also practiced dependency injection, profile-based configuration management, and automated testing strategies using Kotlin and JUnit.

## AI Disclosure
### AI Tools Used
- OpenAI ChatGPT (GPT-5)

### AI-Assisted Work
- Used AI to debug build and test errors, understand Spring Boot annotations, and clarify documentation syntax (Swagger/OpenAPI and compression tests).
- AI was also used to help structure the report and generate example code snippets for integration testing.
- Approx. 30% of the total work was AI-assisted; all generated content was verified and corrected manually.

### Original Work
- Implemented controllers, services, profiles, SSL configuration, and integration tests independently.
- Gained a deeper understanding of Spring Boot testing, compression mechanisms, and performance analysis with curl.
- All final debugging, configuration, and report integration were done manually after verifying AI suggestions.
