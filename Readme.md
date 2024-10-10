
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




# AWS Setup
1. Set up Docker's apt repository.
```shell
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
```
2. Install docker packeges.
```
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```
3. Verify that the Docker Engine installation is successful by running the hello-world image.
```
sudo docker run hello-world
```

4. install docker-compose
```
sudo apt install docker-compose
```
5. Give Permission To /.mvnw
```
chmod +x ./mvnw

```
6. spining up server
```
clone this package i.e git clone github.com/adisupport/myPG.git
cd into that package cd myPG
sudo docker-compose up
```
