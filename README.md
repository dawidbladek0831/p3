# Git Platform Provider Repository Fetcher
A Spring Boot application that retrieves repository information from git platform providers via a REST API endpoint.

## Table of Contents
* [General Information](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Run](#run)
* [Usage](#usage)
* [Test](#test)
* [Room for Improvement](#room-for-improvement)

## General Information
- This application provides a REST API endpoint to fetch repository information

## Technologies Used
- Spring Boot 3.5
- Java 21

## Features
- Single endpoint for repository information retrieval
- Strategy pattern implementation for different Git providers(extensible architecture)

## Setup
1. Clone the repository
2. Create _.env_ file base on _.env.example_ in _resources_ folder

## Run
1. Build the project
    
       $ ./gradlew :service:git_platform:build

2. Run the application

       $ ./gradlew :service:git_platform:bootRun

## Test
Run test
      
      $ ./gradlew :service:git_platform:test --rerun

## Usage
The main endpoint of the application
   
      GET /api/v1/providers/{provider}/users/{username}/repositories
   
Make example request by curl

      $ curl http://localhost:8080/api/v1/providers/GITHUB/users/dawidbladek0831/repositories

or use [Postman Workspaces](https://www.postman.com/dawidbladek0831/p3/overview)


## Room for Improvement
Areas for improvement:
- Support for more Git providers (GitLab, Bitbucket)
- Swagger/OpenAPI documentation
