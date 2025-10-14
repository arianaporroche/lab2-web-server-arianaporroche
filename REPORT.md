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



## Technical Decisions
### Mandatory tasks
- Used **Spring Boot 3.5.3 with Kotlin 2.2.10** for modern language features and rapid development.  
- Chose **Thymeleaf** for rendering the custom error page because it integrates seamlessly with Spring Boot templates.  
- Configured **HTTP/2 and SSL** to improve performance and secure communication.  
- Used **dependency injection** and interfaces (`TimeProvider`) to allow future mocking/testing without changing controller logic.  
- Used **Jackson with jackson-module-kotlin** to serialize Kotlin data classes automatically.  
- Relied on **Ktlint** to enforce consistent Kotlin coding style and prevent build errors.  
- Managed sensitive information (**keystore password**) through environment variables instead of hardcoding.  



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