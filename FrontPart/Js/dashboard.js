// ==============================
// Check Login
// ==============================

if (!localStorage.getItem("userId")) {
    window.location.href = "index.html";
}



// ==============================
// Load Dashboard
// ==============================

function loadDashboard(){

fetch("https://laudable-integrity-production-6b9e.up.railway.app/api/dashboard")

.then(response => response.json())

.then(data =>{

document.getElementById("users").innerHTML=data.totalUsers;

document.getElementById("buses").innerHTML=data.totalBuses;

document.getElementById("alerts").innerHTML=data.matchedAlerts;

document.getElementById("notifications").innerHTML=data.totalNotifications;

})

.catch(error=>{

console.log(error);

showToast("Failed to load dashboard");

});

}

loadDashboard();

// ==============================
// Search Bus
// ==============================

async function searchBus(){

try{

const source=prompt("Enter Source");

if(!source) return;

const destination=prompt("Enter Destination");

if(!destination) return;

const response=await fetch(`https://laudable-integrity-production-6b9e.up.railway.app/api/buses/search?source=${source}&destination=${destination}`);

const data=await response.json();

if(data.length===0){

document.getElementById("result").innerHTML="<h3>No Bus Found</h3>";

return;

}

document.getElementById("result").innerHTML="<pre>"+JSON.stringify(data,null,2)+"</pre>";

}
catch(error){

console.log(error);

showToast("Search Failed");

}

}

// ==============================
// Create Alert
// ==============================

async function createAlert(){

try{

const source=prompt("Enter Source");

if(!source) return;

const destination=prompt("Enter Destination");

if(!destination) return;

const busType=prompt("Enter Bus Type (VOLVO / AC / NON_AC)");

if(!busType) return;

const response=await fetch("https://laudable-integrity-production-6b9e.up.railway.app/api/alerts",{

method:"POST",

headers:{

"Content-Type":"application/json"

},

body:JSON.stringify({

userId:Number(localStorage.getItem("userId")),

source:source,

destination:destination,

busType:busType

})

});

if(response.ok){

showToast("Alert Created Successfully");

loadDashboard();

}
else{

showToast("Failed To Create Alert");

}

}
catch(error){

console.log(error);

showToast("Server Error");

}

}

// ==============================
// Notifications
// ==============================

async function loadNotifications(){

try{

const response=await fetch(
`https://laudable-integrity-production-6b9e.up.railway.app/api/notifications/user/${localStorage.getItem("userId")}`
);

const data=await response.json();

if(data.length===0){

document.getElementById("result").innerHTML="<h3>No Notifications</h3>";

return;

}

document.getElementById("result").innerHTML="<pre>"+JSON.stringify(data,null,2)+"</pre>";

}
catch(error){

console.log(error);

showToast("Unable to Load Notifications");

}

}

// ==============================
// Welcome User
// ==============================

const username=localStorage.getItem("username");

if(username){

document.getElementById("welcomeUser").innerHTML="👋 Welcome, "+username;

}

// ==============================
// Logout
// ==============================

function logout(){

localStorage.clear();

window.location.href="index.html";

}

// =======================================
// Live Tracking From Search Page
// =======================================

const urlParams = new URLSearchParams(window.location.search);

const trackingBus = urlParams.get("bus");

if(trackingBus){

    document.getElementById("trackingContainer").style.display = "block";

    setTimeout(function(){

        map.invalidateSize();

        startLiveTracking(trackingBus);

    },300);

}


// =======================================
// Delay Notification
// =======================================

async function openDelayModal(){

    document.getElementById("delayModal").style.display = "flex";

    await loadBusNumbers();

}

function closeDelayModal(){

    document.getElementById("delayModal").style.display = "none";

}

function toggleOtherReason(){

    const reason =
        document.getElementById("delayReason").value;

    if(reason === "Other"){

        document.getElementById("otherReason").style.display =
            "block";

    }
    else{

        document.getElementById("otherReason").style.display =
            "none";

    }

}

// =======================================
// Load Bus Numbers
// =======================================

async function loadBusNumbers(){

    try{

        const response = await fetch(
            "https://laudable-integrity-production-6b9e.up.railway.app/api/buses"
        );

        const buses = await response.json();

        const dropdown =
            document.getElementById("delayBusNumber");

        dropdown.innerHTML = "";

        buses.forEach(bus=>{

            dropdown.innerHTML += `

                <option value="${bus.busNumber}">

                    ${bus.busNumber}

                </option>

            `;

        });

    }
    catch(error){

        console.log(error);

        showToast("Unable to Load Bus Numbers");

    }

}


// =======================================
// Submit Delay Report
// =======================================

async function submitDelayReport(){

    let reason =
        document.getElementById("delayReason").value;

    if(reason === "Other"){

        reason =
            document.getElementById("otherReason").value.trim();

    }

    const report = {

        userId:Number(localStorage.getItem("userId")),

        busNumber:
            document.getElementById("delayBusNumber").value,

        reason:reason,

        delayMinutes:Number(
            document.getElementById("delayMinutes").value
        )

    };

    try{

        const response = await fetch(

            "https://laudable-integrity-production-6b9e.up.railway.app/api/delay",

            {

                method:"POST",

                headers:{

                    "Content-Type":"application/json"

                },

                body:JSON.stringify(report)

            }

        );

        if(response.ok){

            showToast("🚨 Delay Report Submitted");

            closeDelayModal();

        }
        else{

            showToast("Unable to Submit Report");

        }

    }
    catch(error){

        console.log(error);

        showToast("Server Error");

    }

}
