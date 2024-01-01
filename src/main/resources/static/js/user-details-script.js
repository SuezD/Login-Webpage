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
