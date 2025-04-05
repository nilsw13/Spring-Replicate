# Spring Boot Replicate Client 🚧

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![version](https://img.shields.io/badge/version-1.0.0-purple)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nilsw13_Spring-Boot-Replicate&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nilsw13_Spring-Boot-Replicate)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nilsw13_Spring-Boot-Replicate&metric=coverage&cachebuster=123)](https://sonarcloud.io/summary/new_code?id=nilsw13_Spring-Boot-Replicate)

The spring-boot-replicate package is a  Spring Boot client for the Replicate AI platform. Built specifically for the Java ecosystem, it leverages Spring's powerful dependency injection and autoconfiguration capabilities to seamlessly integrate Replicate's AI services into your Spring Boot applications.
This client offers a comprehensive set of interfaces to interact with all major Replicate API endpoints through a clean, type-safe Java API. The client provides specialized services for each Replicate resource:

Account management: Access account information and settings
Collections: Discover and explore curated model collections
Deployments: Set up and maintain production API endpoints for models
Hardware: View available compute resources for running models
Models & Versions: Create, retrieve, and manage AI models and their versions
Predictions: Execute model inferences and process results
Training: Fine-tune models with custom datasets
Webhooks: Configure webhooks for event notifications

⚠️ IMPORTANT: The package requires proper configuration through Spring's application properties system. The client will only activate when the replicate.api-token property is correctly set in your application.properties or application.yml file. Without this configuration, the client will not initialize, and you won't be able to interact with the Replicate API.
Features

🏃‍♂️ Run Models: Execute predictions with any model available on Replicate
🤖 Model Management: Create, list, update, and delete models
🔄 Versions: Get information about model versions and their capabilities
📊 Deployments: Create and manage model deployments for production use
🎓 Training: Fine-tune models with your own data
📚 Collections: Browse curated model collections
🪝 Webhooks: Configure webhooks for event notifications
---


## Installation

Add the dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>io.github.nilsw13</groupId>
    <artifactId>spring-boot-replicate</artifactId>
    <version>1.0.0</version>
</dependency>
```

## ⚡ Quick Start

## ⚠️ MANDATORY CONFIGURATION: The client requires that you configure your Replicate API token in application.properties or application.yml. Without this configuration, the library will not activate:
```properties
# Required - The client will not function without this property
replicate.api-token=your_replicate_api_token_here

# Optional - Defaults to https://api.replicate.com/v1
replicate.api-url=https://api.replicate.com/v1
```

The Spring Boot autoconfiguration system will automatically detect these properties and initialize the Replicate client with them. Once configured, you can inject the client into your Spring components as shown in the examples below.

## Usage
```java
@Service
public class MyService {
    private final Replicate replicate;

    public MyService(Replicate replicate) {
        this.replicate = replicate;
    }

    // Use the client to interact with the Replicate API
}
```

## Available Methods
```java
replicate.account().get();
replicate.collections().get(String collectionSlug);
replicate.collections().list();
replicate.deployments().list();
replicate.deployments().create(DeploymenConfiguration configuration);
replicate.deployments().createDeploymentPrediction(String deploymentOwner, String deploymentName);
replicate.deployments().get(String deploymentOwner, String deploymentName);
replicate.deployments().update(String deploymentOwner, String deploymentName, DeploymentConfiguration changes);
replicate.deployments().delete(String deploymentOwner, String deploymentName);
replicate.hardware().list();
replicate.models().create(Model data);
replicate.models().createModelPrediction(String modelOwner, String modelName);
replicate.models().get(String modelOwner, String modelName);
replicate.models().delete(String modelOwner, String modelName);
replicate.models().list();
replicate.models().listModelVersions(String modelOwner, String modelName);
replicate.models().getModelVersion(String modelOwner, String modelName, String versionId);
replicate.models().deleteVersion(String modelOwner, String modelName, String version);
replicate.models().getModelReadme(String modelOwner, String modelName);
replicate.predictions().list();
replicate.predictions().get(String id);
replicate.predictions().create(String modelVersion);
replicate.predictions().cancel(String id);
replicate.trainings().create(String modelOwner, String modelName, String modelVersion);
replicate.trainings().cancel(String trainingId);
replicate.trainings().get(String trainingId);
replicate.trainings().list();
replicate.webhooks().getDefaultSecretSigningWebhook();
```

Reference: https://replicate.com/docs/reference/http




## Usage Examples**
```java

@RestController
public class DemoController {
    private final Replicate replicate;

    public DemoController(Replicate replicate) {
        this.replicate = replicate;
    }

    @GetMapping("/models")
    public ModelList listModels() {
        return replicate.models().list();
    }

    @GetMapping("/account")
    public Account get() {
        return replicate.account().get();
    }
}

```

## Response Handling
All API calls return strongly-typed response objects that represent the data returned by the Replicate API. These objects provide convenient methods for accessing the response data:

```java
// Get a prediction
Prediction prediction = replicate.predictions().get("123456");

// Access properties
String id = prediction.getId();
String status = prediction.getStatus();
Map<String, Object> output = prediction.getOutput();
```


## Error Handling
The client throws ReplicateApiException for API-related errors, which encapsulates HTTP response details from failed API calls:
```java
try {
Prediction prediction = replicate.predictions().get("invalid-id");
} catch (ReplicateApiException e) {
// Access standard exception details
String message = e.getMessage();
Throwable cause = e.getCause();

// Access HTTP-specific details
int statusCode = e.getStatusCode();     // HTTP status code of the failed request
String responseBody = e.getResponseBody(); // Raw response body from the API

// The toString() method includes status code and response info
logger.error("API error occurred: {}", e.toString());
        }
```

This exception preserves the complete context of the failed API call, making it easier to diagnose and handle specific error conditions from the Replicate API.




## License
The MIT License (MIT). Please see License File for more information.




