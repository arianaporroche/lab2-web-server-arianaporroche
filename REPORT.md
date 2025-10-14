# Lab 2 Web Server -- Project Report
**Author:** Ariana Porroche Llorén (874055)

**Date:** 08th October 2025

**Course:** Web Engineering


## Description of Changes
### Mandatory tasks

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


## Technical Decisions


### Bonus opportunities
7. **Implement Swagger/OpenAPI Documentation**
    - Chose **Springdoc OpenAPI** for automatic documentation generation with Spring Boot.
    - Used annotations in controller for endpoint documentation and example responses rather than writing manual YAML/JSON files.
    - Configured **bearer authentication** to demonstrate secure endpoints.
    - Example responses included via **ExampleObject** to provide realistic API output.





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