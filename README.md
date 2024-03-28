# YugiBank Accounts

This is a microservice which handles accounts of YugiBank. This service uses Rest interface for communication and exposes multiple rest endpoints to add, update, fetch and delete accounts
Please refer commit history of this readme file for incremental changes made to this service

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [API endpoints](#api-endpoints)
- [Commit History](#commit-history)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17 or later
- [Spring](https://spring.io/projects/spring-boot) framework
- Maven for building the project

## Getting Started

Follow these steps to set up and run the Spring security Learner Microservice:

1. Clone this repository:

   ```shell
   git clone https://github.com/yr5913/yugibank-accounts.git
   cd yugibank-accounts
2. Build the microservice:
   ```shell
   mvn clean install

3. Run the microservice:
   ```shell
   java -jar target/yugibank-accounts.jar

## API Endpoints
`POST /api/create`: Create a new customer.

## Commit History
- Branch: start
1. initial commit
2. added test hello endpoint
3. made yml configurations for h2 database
4. added required database schemas using schema.sql
5. added repositories and entities for accounts and customers
6. added dto classes
7. added post endpoint for creating a customer
8. added get endpoint for getting customer details and put method to update the account and customer details
9. added delete endpoint for deleting customer and account details
10. added global exception handler for handling all types of exceptions