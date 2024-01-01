document.addEventListener('DOMContentLoaded', function () {
    // Fetch user details when the page loads
    fetchUserDetails();
});

function fetchUserDetails() {
    fetch('/api/get-user-details') // Replace with your actual endpoint for getting user details
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
    document.getElementById('username-placeholder').innerText = userDetails.username;
    document.getElementById('password-placeholder').innerText = userDetails.password;
    document.getElementById('firstname-placeholder').innerText = userDetails.firstName;
    // Update other placeholders for additional user details
}
