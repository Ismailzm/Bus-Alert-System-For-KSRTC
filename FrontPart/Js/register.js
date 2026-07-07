async function registerUser() {

    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("http://localhost:8080/api/users/register", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({

                name: name,
                email: email,
                password: password

            })

        });

        if (response.ok) {

            showToast("Registration Successful");

            window.location.href = "login.html";

        } else {

            showToast("❌ Registration Failed");

        }

    } catch (error) {

        showToast("Cannot connect to Backend Server");

        console.log(error);

    }

}