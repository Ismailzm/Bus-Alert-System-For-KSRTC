async function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("http://localhost:8080/api/users/login", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                email: email,
                password: password
            })

        });

        if (response.ok) {

            const user = await response.json();

            localStorage.setItem("userId", user.id);
            localStorage.setItem("username", user.name);

            showToast(" Login Successful");

            window.location.href = "dashboard.html";

        } else {

            showToast("❌ Invalid Email or Password");

        }

    } catch (error) {

        showToast("Cannot connect to Backend Server");
        console.log(error);

    }

}