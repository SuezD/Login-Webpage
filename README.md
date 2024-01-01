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
- [x] Build Basic API
- [x] Setup MongoDB
- [x] Connect API to DB
- [x] Create HTML Webpage that connects to API
- [x] Add signup functionality to webpage
- [x] Add login functionality to webpage
- [ ] Add true token generation and fetching user info
- [x] Improve design of first page
- [x] Add 'My Profile' page where user can view and update info
- [ ] Improve design of second page

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
