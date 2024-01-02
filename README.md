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
  show dbs
  use user-details
  show collections
  db.users.find()
  db.users.deleteMany({})
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
## Troubleshooting
On occassion the browser won't pull the latest script.js file. In this case, you'll need to force a browser refresh (cmd+shift+r on Mac and CTRL+F5 on windows). [More details here]([url](https://stackoverflow.com/questions/3951187/javascript-file-not-updating-no-matter-what-i-do#:~:text=Type%20in%20the%20full%20address,should%20run%20as%20you%20expect.)https://stackoverflow.com/questions/3951187/javascript-file-not-updating-no-matter-what-i-do#:~:text=Type%20in%20the%20full%20address,should%20run%20as%20you%20expect.)
