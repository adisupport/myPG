
# HOW TO RUN APPLICATION

To Run These Application you must have MYSQL Installed in System if Not then Either you installed by self or USE DOCKER.

METHOD 1 : Setting Environment Variable Through `.properties` file.

Create a `env.properties` file then paste these 

```env.properties
DB_URL : TO SET SQL SERVER URL.
DB_USERNAME: TO SET USERNAME OF DATABASE.
DB_PASSWORD: TO SET PASSWORD FOR DATABASE.
PORT :CHANGE PORT OF APPLICATION SERVER. 
```
RUN These Command on command line.
```CMD
    java -jar your-app.jar --spring.config.additional-location=classpath:/env.properties
```

METHOD 2: Providing inside Terminal
UNIX System
```CMD
java -DDB_URL=jdbc:mysql://localhost:3306/mydb \
     -DDB_USERNAME=myuser \
     -DDB_PASSWORD=mypassword \
     -jar your-app.jar

```

## Window System

```CMD
set DB_URL=jdbc:mysql://localhost:3306/mydb
set DB_USERNAME=myuser
set DB_PASSWORD=mypassword
java -jar your-app.jar
```
