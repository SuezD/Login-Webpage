# Login Webpage

Using this project to:
* Build a full web application (covers front-end, back-end, db and security)
* Better understand modern oauth2.0 flow

# Plan
To build a web application that includes the following features
- [x] Login
- [x] Sign Up
- [x] View Profile
- [x] Logout
- [x] Delete Account

The site should be secured as follows
- [ ] Accessed through HTTPS
- [ ] Upon login, user session should be maintained through a token stored in session cookie
- [x] Passwords should be hashed, salted and peppered (using bcypt function)

# Setup Using Docker
## Prerequisites
* [Docker](https://docs.docker.com/engine/install/)

## Set gradle as executable
For Mac
```chmod +x ./gradlew```

For Windows
```TBA```
## Build project
``` ./gradlew build ```
## Create Docker Image (optional)
``` docker build -t myapp .```
## Run Containers (and build image)
This will start a container for the application and mongodb
``` docker-compose up --build```

# Setup Without Docker
## Prerequisites
* Mongodb
* Gradle
* Java 17

## Start Mongo DB
Run the below command to start the MongoDB service
  ```
  brew services start mongodb-community
  ```
## Query DB (optional)
  ```
  mongosh
  show dbs
  use user-details
  show collections
  db.users.find()
  db.users.deleteMany({})
  ```
## Run Springboot Application
  ```
  ./gradlew build
  ./gradlew bootRun
  ```
## Option 1: Visit Webpage
Visit http://localhost:8080/

# Troubleshooting
On occassion the browser won't pull the latest script.js file. In this case, you'll need to force a browser refresh (cmd+shift+r on Mac and CTRL+F5 on windows). [More details here]([url](https://stackoverflow.com/questions/3951187/javascript-file-not-updating-no-matter-what-i-do#:~:text=Type%20in%20the%20full%20address,should%20run%20as%20you%20expect.)https://stackoverflow.com/questions/3951187/javascript-file-not-updating-no-matter-what-i-do#:~:text=Type%20in%20the%20full%20address,should%20run%20as%20you%20expect.)

# User Interface
Login Page                 |  Signup Page
:-------------------------:|:-------------------------:
![](/assets/loginUI.png)   |  ![](/assets/signupUI.png)
