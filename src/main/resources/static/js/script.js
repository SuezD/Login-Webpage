// add disabled attribute to login and signup buttons
// enable buttons once required data is entered
// define password regex (contain numbers, characters, length)
var reponse = document.getElementById('response');

function login() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const loginData = {
        username: username,
        password: password
    };

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    })
    .then(response => {
        if (response.ok) {
            // Redirect to user-details.html on successful login
            window.location.href = '/my-profile';
        }
            return response.text();
    })
    .then(data => {
        // Display API response on failure
        response.innerText = data;
    })
    .catch(error => console.error('Error:', error));
}

function signup() {
    const username = document.getElementById('signup-username').value;
    const password = document.getElementById('signup-password1').value;
    const password2 = document.getElementById('signup-password2').value;
    const firstname = document.getElementById('signup-firstname').value;

    if (!valid(username, password, password2)){
        return
    }

    const signupData = {
        username: username,
        password: password,
        firstName: firstname
    };

    fetch('/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(signupData),
    })
    .then(response => {
        if (response.ok) {
            // Redirect to user-details.html on successful login
            window.location.href = '/my-profile';
        } else {
            return response.text();
        }
    })
    .then(data => {
        // Display API response on failure
        document.getElementById('response').innerText = data;
    })
    .catch(error => console.error('Error:', error));
}

function valid(username, password1, password2){
    if(username == "" || password1 == "" || password2 == ""){
        document.getElementById('response').innerText = "All fields must be filled out";
        return false;
    }
    if(password1 != password2){
        document.getElementById('response').innerText = "Passwords do not match";
        return false;
    }
    return true
}

function showTab(tabName) {
    var loginContainer = document.getElementById('login-container');
    var signupContainer = document.getElementById('signup-container');

    if (tabName === 'login') {
        loginContainer.style.display = 'block';
        signupContainer.style.display = 'none';
    } else if (tabName === 'signup') {
        loginContainer.style.display = 'none';
        signupContainer.style.display = 'block';
    }
    document.getElementById('response').innerText = "";
}
