## Quickstart_Backend GUIDE 

*This code is to be used as a backend quickstart code for projects and exercises given 
in flow 2 and 3 in the third semester of the study program “AP degree in Computer Science” at CPH Business Lyngby.* 

Projects which are expected to use this start-code are projects that require all, or most of the following technologies:
- *JPA and REST*
- *Testing, including database test*
- *Testing, including tests of REST-API's*
- *CI and CONTINUOUS DELIVERY*

### Preconditions
In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines.
You will also need to build, test and deploy, locally with maven and remotely with maven controlled by Github actions.
Your docker environment should be up and running. 

- Before you commit your project to the repository do a mvn clean test via the terminal to see if your docker environment is up and running.


### Clone code 
From github link: https://github.com/solskinIsak/Quickstart_backend 

### Git repository 
Delete existing git repository and create your own git repository and upload to your own github account. 

Delete the existing git repository by using this command: rm -fr .git  

### Github Actions
In order to be able to get github actions up and running correctly you need to set some secrets on the repository: 

- *REMOTE_USER*
This should have the value of your tomcat user 

- *REMOTE_PW*
This should have the value of your password for your tomcat user 

You can find your values in the tomcat-users.xml file on Digital Ocean.

## CHANGES TO BE MADE IN THE CODE

Open the files with Intellij IDEA 2021.2.4

### SDK
Remember to set project SDK to java 11. 

### pom.xml file 

<groupId>INSERT UNIQUE ID - example: com.domain</groupId>
<artifactId>INSERT NAME OF PROJECT</artifactId>
<version>1.0.1</version>
<packaging>war</packaging>

<name>INSERT NAME OF PROJECT</name>

<remote.server>https:// INSERT YOUR URL/tomcat/manager/text</remote.server>
<db.name>INSERT YOUR DB NAME</db.name>

Check that there are no multiple dependencies. 


### mavenworkflow.yml
Check the code as listed below is set to the name of your main branch: 

EXAMPLE: 

branches: 
 - main

### persistence.xml
Make sure to insert the name of your database as below:
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/INSERTYOURDBNAMEHERE?serverTimezone=UTC"/>

you might want to change the testdatabase too:

<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/INSERTTESTDBNAMEHERE?serverTimezone=UTC"/>

#### Tomcat configuration
- *Open build configuration tab*
- *Click on the + icon* 
- *Choose Tomcat Server → local* 
- *Click the fix button on build and choose war_exploded* 
- *Set the url to your desired local url* 
- *Press apply + ok* 

The url that is set here should match the frontend application when running on local 
If the project is not to run on local remember to change accordingly. 

### Maven delegate IDE
Open the maven settings tab 
Go to “Build, Execution, Development”
Open Build tools → Maven → Runner 

Make sure the “Delegate IDE build/run actions to maven” box is ticked


### Class examples in the project:
In the project, there are multiple examples to be used as templates:
Entities -> EntityExample needs to be changed if you want to use it
Facades -> FacadeExample needs to be changed if you want to use it
