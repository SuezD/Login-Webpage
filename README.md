# Login Webpage

Using this project to:
* Build a full web application (covers front-end, back-end, db and security)
* Better understand modern oauth2.0 flow

## Steps and Choices
1) Build API - using Springboot (over expressjs so that I can brush up on my java skills)
2) Setup DB - using mongoDB (over SQL since I've never used mongodb before)
3) Create HTML webpage
4) Add desired css and js

# Done
- [x] Securely storing hashed password and performing validation
- [x] Ability to login, signup, view profile, logout and delete account

## Yet to Implement
- [ ] Token generation (currently using random uuid)
- [ ] user info from token (or at least id from token to query db)

Currently cookie response value is just username

## Setup
### Start Mongo DB
Run the below command to start the MongoDB service
  ```
  brew services start mongodb-community
  ```
### Query DB
  ```
  mongosh
  user user-details
  show collections
  db.users.find()
  ```
### Run Springboot Application
  ```
  ./gradlew build
  ./gradlew bootRun
  ```
### Option 1: Visit Webpage
Visit http://localhost:8080/

### Option 2: Make a curl request
  ```
  curl -X POST -H "Content-Type: application/json" -d '{"username":"your_username","password":"your_password", "firstName":"your_firstname"}' http://localhost:8080/signup
  curl -X POST -H "Content-Type: application/json" -d '{"username":"your_username","password":"your_password"}' http://localhost:8080/login
  ```
