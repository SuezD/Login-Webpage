document.addEventListener('DOMContentLoaded', function () {
    // Fetch user details when the page loads
    fetchUserDetails();
});

function fetchUserDetails() {
    fetch('/user-details', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    }) 
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Failed to fetch user details');
        }
    })
    .then(data => displayUserDetails(data))
    .catch(error => console.error('Error:', error));
}

function displayUserDetails(userDetails) {
    document.getElementById('username').innerText = userDetails.username;
    document.getElementById('firstname').innerText = userDetails.firstName;
}

function logout() {
    // Clear cookies (example: using document.cookie)
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

    // Redirect to the main page
    window.location.href = "/";
}

function deleteAccount() {
    // Assuming you are using some backend server (e.g., Node.js with Express) to handle MongoDB operations
    // Send a request to your backend to delete the account from MongoDB
    // Example using Fetch API
    fetch('/delete-account', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    .then(response => response.text())
    .then(data => {
        // Handle the response as needed
        console.log(data);

        // Clear cookies
        document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

        // Redirect to the main page
        window.location.href = "/";
    })
    .catch(error => {
        console.error('Error deleting account:', error);
        // Handle errors appropriately
    });
}

