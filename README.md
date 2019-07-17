# ACME Corp. XtendM3 Extensions
A sample repository for Acme Corp XtendM3 Extensions

## Introduction
This repository is a sample repository for Acme Corp XtendM3 Extensions. The idea is to keep all customer's extension in one repository and collaborate to create, maintain and update Extensions in a central repository and move to customer's environment when needed.

This let's the developers to work with keeping track of modifications to the extensions as well as a big advantage of being able to build, test and run them locally with a mocked M3 environment and create unit test cases to ensure the quality of the developed extensions 

## Setup
The project is a standard Maven project using Mockito and JUnit 4. The source directory structure is similiar to any other Maven directory structure except for the Groovy source roots like below  

```
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── groovy
    │   │   ├── CustomerCreditCheckP1.groovy
    │   │   └── CustomerCreditCheckP2.groovy
    │   ├── java
    │   └── resources
    │       └── metadata.yaml
    └── test
        ├── groovy
        │   └── CustomerCreditCheckTest.groovy
        └── java
```


### Prerequisites
- An IDE of choice, e.g. Eclipse, Intellij IDEA, etc.
- Git
- Groovy SDK version 2.5.6
- Groovy plugin for IDE
- Editorconfig plugin for IDE
- A terminal of choice Command Prompt, PowerShell, Cygwin or any *nix based terminal

### Instructions
To set up the project locally, perform the following:
- Clone/Download the latest version of project from repository
- Import Maven project project
	- On Eclipse there's an option of importing Maven projects directly
	- On choose either New Project from existing sources or Import project

## Issues
When setting up this project for the first time you might run into some of the known problems 

### Missing Artifact
If you're seeing `Missing artifact com.infor.m3:xtendm3-sdks:jar:<version>` then it means that you're either using the XtendM3 SDK ahead of release or are using the wrong version. Take a look at the [official SDK repository](https://oxford.awsdev.infor.com/Erfan.Yousefi/xtendm3-sdk) and make sure you've gone through all the steps before setting this project up. 


### NoSuchMethodError when running unit tests
When running unit tests for the first time in Eclipse, you might see an error like this:

```
java.lang.NoSuchMethodError: org.junit.platform.launcher.Launcher.execute(Lorg/junit/platform/launcher/TestPlan;[Lorg/junit/platform/launcher/TestExecutionListener;)V
	at org.eclipse.jdt.internal.junit5.runner.JUnit5TestReference.run(JUnit5TestReference.java:89)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:41)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:541)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:763)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:463)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:209)
```

This is due to Eclipse choosing JUnit 5 as the test runner instead of JUnit 4. To fix this issue open the Run Configurations and select JUnit 4 as your Test Runner.