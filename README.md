### Company Management Repository

Simple GUI app for storing feedbacks about employees and company logs.

## Running application

Make sure that data.db file with sample data is in folder where application is running.
Otherwise, application will run, but you won't be able to login because database will be empty and won't have any users.

Sample user to login: 
* email: *blake@mail.com*
* password: *abcd*

## Building application

### With binaries

For JavaFX, I used [javafx gradle plugin](https://github.com/openjfx/javafx-gradle-plugin). 
It includes corresponding binaries to the platform it is built on.
For example if you build app on Windows it will run only on Windows.

### Using specific binaries

To build app with specific JavaFX binaries comment out gradle plugin in *build.gradle* and add needed dependencies. For example:

    implementation group: 'org.openjfx', name: 'javafx-web', version: "17-ea+11", classifier: 'win'

[Here](https://repo.maven.apache.org/maven2/org/openjfx/) are all available binaries.

*Note that you may also need to add module-info file to project for this to work*

### Platform independent

To make cross-platform build add to *build.gradle* file:

    configuration = 'compileOnly'

and run with needed modules. For example:

    PS C:\Users\user\Downloads> java --module-path C:\Users\user\Downloads\javafx17\javafx-sdk-17.0.1\lib --add-modules javafx.controls,javafx.fxml -jar app.jar

You can download JavaFX SDK [here](https://gluonhq.com/products/javafx/)


## Technologies

 - JavaFX 11
 - JUnit 5
 - SQLite JDBC

## GUI

Feedbacks table:  
![FeedbacksScr](https://user-images.githubusercontent.com/78920002/152587043-4f47ad7d-784a-4e2b-83e8-7f93acd9cf54.png)

Logs Table:  
![LogsScr](https://user-images.githubusercontent.com/78920002/152587067-4757953c-d20d-4773-9222-5ddc8b8eed04.png)

Add feedback:  
![AddFeedbackScr](https://user-images.githubusercontent.com/78920002/152586970-fbba6aee-693b-4c2c-ac19-c9af3fef3c95.png)
