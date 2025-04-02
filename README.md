# Spring Boot Replicate Client 🚧

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
![version](https://img.shields.io/badge/version-1.0.0-purple)


**🚧 Work in Progress 🚧**  
A Spring Boot client for interacting with the [Replicate API](https://replicate.com/). Inspired by the [Laravel Replicate PHP Client](https://github.com/halilcosdu/laravel-replicate), this library is currently under development and will soon provide a convenient way to manage and interact with Replicate services in Java.

---


## Coming Soon! 🚀

A comprehensive Spring Boot client for interacting with the [Replicate API](https://replicate.com/). This library provides a convenient way to manage and interact with Replicate services in Java.
This library is currently in active development. Stay tuned for the first release.


## Features

- 🏃‍♂️ **Run Models**: Execute predictions with any model available on Replicate
- 🤖 **Model Management**: Create, list, update, and delete models
- 🔄 **Versions**: Get information about model versions and their capabilities
- 📊 **Deployments**: Create and manage model deployments for production use
- 🎓 **Training**: Fine-tune models with your own data
- 📚 **Collections**: Browse curated model collections
- 🔒 **Authentication**: Built-in token authentication support
- 🪝 **Webhooks**: Configure webhooks for event notifications

## Installation

Add the dependency to your `pom.xml` if you're using Maven:

```xml
<dependency>
    <groupId>com.nilsw13</groupId>
    <artifactId>spring-replicate</artifactId>
    <version>1.0.0</version>
</dependency>
```

## ⚡ Quick Start
- 1 **Configure your Replicate API token in application.properties**
```properties
    replicate.api.token=your_replicate_api_token_here
```
- 2 **Usage Examples**
```java
// Run a Prediction
private final Replicate replicate;

public YourClassName(Replicate replicate) {
    this.replicate = replicate;
}
public void generateImage() {
    Prediction prediction = replicate.predictions()
            .create("stability-ai/sdxl")
            .input("prompt", "A cosmic landscape with vibrant nebulae")
            .input("negative_prompt", "blurry, distorted")
            .execute();

    System.out.println("Prediction ID: " + prediction.getId());
    System.out.println("Status: " + prediction.getStatus());
    System.out.println("Output: " + prediction.getOutput());
}
```


```java
    // List models
    ModelList models = replicate.models().list();
    models.getResults().forEach(model -> {
    System.out.println(model.getOwner() + "/" + model.getName());
    });
```







