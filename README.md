# Parking System
A command line app for managing the parking system. 
This app uses Java to run and stores the data in Mysql DB.


## System setup

These instructions will get you a copy of the project up and running on your local machine for development, testing and production purposes.
See deployment for notes on how to deploy the project on remote servers.

### Prerequisites

What things you need to install the software and how to install them

- Java 1.8
- Maven 3.6.2
- Mysql 8.0.17

### Installing tools

A step by step series of examples that tell you how to get a development environment running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html

3.Install MySql:

https://dev.mysql.com/downloads/mysql/

After downloading the mysql 8 installer and installing it, you will be asked to configure the password for the default `root` account.
This code uses the default root account to connect and the password can be set as `rootroot`. If you add another user/credentials make sure to change the same in the code base.

4.Create Databases :

Post installation of MySQL, Java and Maven, you will have to set up the tables and data in the data base.
For this, please run the sql commands present in the `Data.sql` file under the `resources` folder in the code base.

5.Set up your developing environement :
Finally, you will be ready to import the code into an IDE of your choice and run the App.java to compile, debug or launch the application.

### Testing

The application has been designed with 2 sets of tests.

1.Unit tests :
Tests grouped by classes to validate that their methods work properly when they are standalone.
Test methods are stored in the src/test/java with the same directory structure as the corresponding sources in src/main/java.
To run these tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn test`

2.Integration tests :
Tests to validate that classes interact with each other properly.
These tests are stored in the src/test/integration package.
To run these tests from maven, go to the folder that contains the pom.xml file and execute the below command.

`mvn verify`

3.Testing tools :
There are also some classes which are used only by tests classes but not by productive code.
These classes are store in the src/test/testingtools package.

### Starting App

When all tests are successful, you are ready to install the software in order to be executed as standalone program.
For this call the command below and it will prepare a JAR file and let you know its location.

`mvn install`
  
That's it, you can now start the application with the following command (replace <JAR_FILE> with the effective file name):

`java -jar <JAR_FILE>`
 

## Deployment

This section will be completed when the first production server is available.

