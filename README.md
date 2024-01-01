# Login Webpage

Using this project to:
* Build a full web application (covers front-end, back-end, db and security)
* Better understand modern oauth2.0 flow

## Steps and Choices
1) Build API - using Springboot (over expressjs so that I can brush up on my java skills)
2) Setup DB - using mongoDB (over SQL since I've never used mongodb before)
3) Create HTML webpage
4) Add desired css and js

## TO DO
- [ ] Build API
- [ ] Setup DB
- [ ] Create HTML Webpage that connects to API
- [ ] Add signup functionality to webpage
- [ ] Add login functionality to webpage
- [ ] Improve design
- [ ] Add 'My Profile' page where user can view and update info

## Setup
### Start Mongo DB
Run the below command to start the MongoDB service
  ```
  brew services start mongodb-community
  ```
### Run Springboot Application
  ```
  ./gradlew build
  ./gradlew bootRun
  ```
### Make a curl request
  ```
  curl -X POST -H "Content-Type: application/json" -d '{"username":"your_username","password":"your_password", "firstName":"your_firstname"}' http://localhost:8080/signup
  curl -X POST -H "Content-Type: application/json" -d '{"username":"your_username","password":"your_password"}' http://localhost:8080/login
  ```
