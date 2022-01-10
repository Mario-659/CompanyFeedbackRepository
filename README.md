##Running application

Make sure that data.db file with sample data is in folder where application is running.
Otherwise, application will run, but you won't be able to login because database will be empty and won't have any users.

Sample user to login: 
* email: *blake@mail.com*
* password: *abcd*

## Building application

### With binaries

To include JavaFX I used [javafx gradle plugin](https://github.com/openjfx/javafx-gradle-plugin). 
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
