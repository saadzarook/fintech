# Fintech Application

A Spring Boot-based fintech application that integrates with the Stripe payment gateway to manage transactions and payment intents. The project follows best practices in API development, security, testing, and documentation using Swagger (Springfox).

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Configuration](#configuration)
- [Swagger API Documentation](#swagger-api-documentation)
- [Testing](#testing)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Introduction

This application is a basic fintech platform that allows users to create and manage payment intents using the Stripe API. It includes secure, RESTful endpoints to handle payment-related operations and follows modern development practices for scalability and reliability.

## Features

- Integration with **Stripe** for payment processing
- RESTful API for creating payment intents
- **Spring Boot** architecture
- Comprehensive **unit and integration tests** with **JUnit 5** and **Mockito**
- Secure storage of API keys using environment variables
- Deployed with embedded **Tomcat** server

## Technologies Used

- **Java 22** 
- **Spring Boot** 3.x.x 
- **Stripe API** (Payment processing)
- **JUnit 5** (Testing)
- **Mockito** (Mocking in unit tests)
- **Gradle** (Build tool)

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 22** or later
- **Gradle**
- A **Stripe** account with API keys
- **Git** (for version control)

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/fintech-application.git
    cd fintech-application
    ```

2. **Set up Stripe API Keys:**

   You need to set up environment variables for Stripe API keys.

   For Unix/Mac:

    ```bash
    export STRIPE_API_KEY=your_stripe_secret_key
    export STRIPE_PUBLISHABLE_KEY=your_stripe_publishable_key
    ```

   For Windows:

    ```cmd
    set STRIPE_API_KEY=your_stripe_secret_key
    set STRIPE_PUBLISHABLE_KEY=your_stripe_publishable_key
    ```

3. **Build the project:**

   If you're using the Gradle wrapper, run:

    ```bash
    ./gradlew clean build
    ```

   Alternatively, you can use Gradle if it's installed globally:

    ```bash
    gradle clean build
    ```

## Running the Application

After building the project, you can run the application with:

```bash
./gradlew bootRun
