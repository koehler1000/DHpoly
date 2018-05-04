## Test Plan

## 1. Introduction

### 1.1 Purpose

The purpose of the Iteration Test Plan is to gather all of the information necessary to plan and control the test effort for a given
iteration. 
It describes the approach to testing the software, and is the top-level plan generated and used by managers to direct the test effort.

This Test Plan for the DHPoly supports the following objectives:

- Identifies the items that should be targeted by the tests.


-  Identifies the motivation for and ideas behind the test areas to be covered.



- Outlines the testing approach that will be used.



- Identifies the required resources and provides an estimate of the test efforts.



- Lists the deliverable elements of the test project


### 1.2 Scope

In this document we describe thefollowing levels of testing:
• Unit Testing
• User Interface Testing

### 1.3 Intended Audience

### 1.4 Document Terminology and Acronyms

| Acronym | Definition                          |
| ------- | ----------------------------------- |
| SRS     | Software Requirements Specification |
| UCD     | Use Case Diagram                    |

### 1.5 References

| Name          | Link                                                                              |
| ------------- | --------------------------------------------------------------------------------- |
| DHPoly-Blog   | [Link](https://dhpoly.wordpress.com/)                                             |
| DHPoly-Github | [Link](https://github.com/koehler1000/DHpoly)                                     |
| Use-Cases     | [Link](https://github.com/koehler1000/DHpoly/tree/master/documentation/use-cases) |

### 1.6 Document Structure

## 2. Evaluation Mission and Test Motivation

The purpose behind testing is to make sure, your project isalways working properly.

### 2.1 Background

If at some point your code contains flaws and you keepprogramming, your code will eventually not run anymore, at some point. 
And once you are there the only way out is to load a recent version from your repository,which is related to investing a lot of time and work.
So that we will hopefully not have that problem, we test ourcode so that we know, whether it is running correctly. 
Additionally that way wewill (hopefully) never publish a not working version of DHPoly.

### 2.2 Evaluation Mission

We invented testing in our project right away after the start. 
The reason for that is stated above. 
It is necessary to always haveworking code, so that flaws can get recognized in an early stage and be fixed withouta huge amount of investments.

### 2.3 Test Motivators

The motivators for our testing are the following ones:
• Quality risks
• Functional risks
• Technical risks
• Not working use cases
• Not working user interfaces

## 3. Target Test Items

Items for testing: 
• Use cases 
• Server-Client connection and data transmission
• User interfaces

## 4. Outline of Planned Tests

In this section we will provide a high level overview of our testing.

### 4.1 Outline of Test Inclusions

The major sector will be game logic.

### 4.2 Outline of other candidates for potential inclusion

We might possibly make use of testing regarding the User Interface.

### 4.3 Outline of Test Exclusions 

Server and client communication tests are currently not planed.

## 5. Test Approach

Our test approach is thinking about the most important functionalities and testing them by implementing tests.

### 5.1 Initial Test-Idea Catalogs and other reference sources

to be done

### 5.2 Testing and Techniques and Types

#### 5.2.1 Data and Database Integrity Testing

n/a

#### 5.2.2 Function testing

|                        | Description                                     |
| ---------------------- | ----------------------------------------------- |
| Technique Objective    | Client and server communication                 |
| Technique              | Use-Case Scenario                               |
| Oracles                | Using JUnit Tests                               |
| Required Tools         | Eclipse with JUnit, TeamCity, SonarLint, GitHub |
| Success Criteria       | positive tests                                  |
| Special Considerations | n/a                                             |

#### 5.2.3 Business Cycle Testing

n/a

#### 5.2.4 User Interface Testing

n/a

#### 5.2.5 Performance

n/a

#### 5.2.6 Load Testing

n/a

#### 5.2.7 Stress Testing

n/a

#### 5.2.8 Volume Testing

n/a

#### 5.2.9 Security and Access Control Testing

n/a

#### 5.2.10 Failover and Recovery Testing

n/a

#### 5.2.11 Configuration Testing

n/a

#### 5.2.12 Installation testing

n/a

## 6. Entry and Exit Criteria

### 6.1 Test Plan

#### 6.1.1 Test Plan Entry Criteria

We don't have any entry criteria for testing. The only mandatory condition is that the code can be compiled. We test at any given time.

#### 6.1.2 Test Plan Exit Criteria

The exit criteria is full filled once a test has finished or manually stopped.

#### 6.1.3 Suspension and resumption criteria

Our suspension criteria is that we interrupt a test as soon as a certain time limit is exceeded.

### 6.2 Test Cycles

to be done

#### 6.1.1 Test Cycle Entry Criteria

to be done

#### 6.2.2 Test Cycle Exit Criteria

The criteria is full filled as soon as all test have ended successfully.

#### 6.2.3 Test Cycle abnormal termination

to be done

## 7. Deliverables

to be done

### 7.1 Test Evaluation Summaries

to be done

### 7.2 Reporting on Test Coverage

to be done

### 7.3 Perceived Quality Reports

to be done

### 7.4 Incident Logs and Change Requests

to be done

### 7.5 Smoke Test Suite and supporting Test Scripts

to be done

### 7.6 Additional work products

to be done

#### 7.6.1 Detailed Test Results

to be done

#### 7.6.2 Additional automated functional Test Scripts

to be done

#### 7.6.3 Test Guidelines

to be done

#### 7.6.4 Traceability Matrices

to be done

## 8. Testing Workflow

Our testing workflow is specified as follows:
- think about a szenario
- write a test
- implement that test
- run the test
- if necessary, fix problems

## 9. Environmental Needs

to be done

### 9.1 Base System Hardware

Our base hardware for the system is not a lot, which is a good thing.
So the only thing needed is:
- laptop

### 9.2 Base Software Elements in the Test Environment

The same thing as in 9.1 goes for the software.
The only necessary software is:
- IDE with JUnit (Eclipse for us)

### 9.3 Productivity and Support Tools

We only use one tool for better/faster testing and that is:
- Sonar Lint (which is integrated in our IDE Eclipse)

### 9.4 Test Environment Configurations

to be done

## 10. Responsibilities, Staffing and Training Needs

to be done

### 10.1 People and Roles

to be done

### 10.2 Staffing and Training Needs

to be done

## 11. Iteration Milestones

n/a

## 12. Risks, Dependencies, Assumptions and Constraints

One risk is, to destroy already written tests while refactoring code.
Also when chaning data types of certain objects within the code, it might also be that a test does not end successfully anymore.
Furthermore it is very hard to write tests, if there are a lot of dependencies between objects and methods (which is the case fairly often).

## 13. Management Process and Procedures

to be done

### 13.1 Measuring and Assessing the Extent of Testing

to be done

### 13.2 Assessing the deliverables of this Test Plan

to be done

### 13.3 Problem Reporting, Escalation and Issue Resolution

to be done

### 13.4 Managing Test Cycles

to be done

### 13.5 Traceability Strategies

to be done

### 13.6 Approval and Signoff

to be done
