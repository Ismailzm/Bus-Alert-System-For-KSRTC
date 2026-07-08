// ===============================
// Live Tracking Variables
// ===============================

let busNumber = "";

let busNotRunningShown = false;

let trackingInterval = null;

let routeLine = null;

let routeDrawn = false;

let currentLat = null;
let currentLng = null;

let animation = null;

// ===============================
// Create Map
// ===============================

const map = L.map("map").setView([15.3173, 75.7139], 7);

L.tileLayer(
    "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png",
    {
        attribution: "© OpenStreetMap"
    }
).addTo(map);

// ===============================
// Bus Icon
// ===============================

const busIcon = L.icon({

    iconUrl: "../Images/bus-marker.png",

    iconSize: [55,55],

    iconAnchor: [27,55],

    popupAnchor: [0,-50]

});

// ===============================
// Bus Marker
// ===============================

const marker = L.marker(

    [12.9716,77.5946],

    {

        icon: busIcon

    }

).addTo(map);

// Default Popup

marker.bindPopup("🚌 Live Bus");

// =====================================
// Smooth Bus Movement
// =====================================

function moveMarkerSmoothly(targetLat,targetLng){

    console.log("Moving marker to:", targetLat, targetLng);

    if(currentLat===null){

        currentLat=targetLat;
        currentLng=targetLng;

        marker.setLatLng([

            currentLat,

            currentLng

        ]);

        map.panTo([

            currentLat,

            currentLng

        ]);

        return;

    }

    if(animation){

        clearInterval(animation);

    }

    const steps=60;

    let step=0;

    const latStep=(targetLat-currentLat)/steps;

    const lngStep=(targetLng-currentLng)/steps;

    animation=setInterval(()=>{

        step++;

        currentLat+=latStep;

        currentLng+=lngStep;

        marker.setLatLng([

            currentLat,

            currentLng

        ]);

        map.panTo(

            [

                currentLat,

                currentLng

            ],

            {

                animate:false

            }

        );

        if(step>=steps){

            clearInterval(animation);

            currentLat=targetLat;

            currentLng=targetLng;

            marker.setLatLng([

                currentLat,

                currentLng

            ]);

        }

    },50);

}

// =====================================
// Load Live Tracking
// =====================================

async function loadTracking(selectedBus){

    if(selectedBus){

        busNumber = selectedBus;

    }

    try{

        const response = await fetch(
            `https://laudable-integrity-production-6b9e.up.railway.app/api/live/${busNumber}`
        );

        if(!response.ok){

            if(!busNotRunningShown){

                busNotRunningShown = true;

                showToast("The bus is not Running Now");

            }

            return;

        }

        const data = await response.json();

        console.log(data);

        // ===============================
        // Draw Route (Only Once)
        // ===============================

        await drawRoute(
            data.source,
            data.destination
        );

        // ===============================
        // Bus Information
        // ===============================

        document.getElementById("busNumber").innerHTML =
            data.busNumber;

        document.getElementById("currentDistrict").innerHTML =
            data.currentDistrict;

        document.getElementById("nextDistrict").innerHTML =
            data.nextDistrict;

        document.getElementById("eta").innerHTML =
            data.etaMinutes + " Minutes";

        document.getElementById("progressFill").style.width =
            data.progress + "%";

        document.getElementById("progressText").innerHTML =
            data.progress.toFixed(1) + "%";

        // ===============================
        // Delay Handling
        // ===============================

        if(data.delayed){

            document.getElementById("status").innerHTML =
                "⚠ DELAYED";

            document.getElementById("status").style.color =
                "#d32f2f";

           const delaySection = document.getElementById("delaySection");
const delayReason = document.getElementById("delayReason");
const delayMinutes = document.getElementById("delayMinutes");

if(delaySection){

    delaySection.style.display = "block";

}

if(delayReason){

    delayReason.innerHTML = data.delayReason;

}

if(delayMinutes){

    delayMinutes.innerHTML =
        data.delayMinutes + " Minutes";

}
            marker.bindPopup(
                `<b>🚌 ${data.busNumber}</b>
                <br><br>
                <span style="color:red;">
                ⚠ DELAYED
                </span>
                <br>
                Reason : ${data.delayReason}
                <br>
                Delay : ${data.delayMinutes} Minutes
                <br>
                ETA : ${data.etaMinutes} Minutes`
            );

        }
        else{

            document.getElementById("status").innerHTML =
                data.status;

            document.getElementById("status").style.color =
                "#4CAF50";

           const delaySection = document.getElementById("delaySection");

if(delaySection){

    delaySection.style.display = "none";

}

            marker.bindPopup(
                `<b>🚌 ${data.busNumber}</b>
                <br>
                Status : ${data.status}
                <br>
                ETA : ${data.etaMinutes} Minutes`
            );

        }

        // ===============================
        // Move Bus Marker
        // ===============================

        moveMarkerSmoothly(
            data.latitude,
            data.longitude
        );

    }
    catch(error){

        console.log(error);

    }

}

// =====================================
// Draw Route
// =====================================

async function drawRoute(source,destination){

    if(routeDrawn){

        return;

    }

    try{

        const response = await fetch(

            `https://laudable-integrity-production-6b9e.up.railway.app/api/routes?source=${encodeURIComponent(source)}&destination=${encodeURIComponent(destination)}`

        );

        const route = await response.json();

        const latLngs = [];

        route.forEach(district=>{

            switch(district){

                case "Bengaluru":
                    latLngs.push([12.9716,77.5946]);
                    break;

                case "Tumakuru":
                    latLngs.push([13.3409,77.1010]);
                    break;

                case "Chitradurga":
                    latLngs.push([14.2251,76.3983]);
                    break;

                case "Davanagere":
                    latLngs.push([14.4663,75.9238]);
                    break;

                case "Haveri":
                    latLngs.push([14.7951,75.3991]);
                    break;

                case "Gadag":
                    latLngs.push([15.4315,75.6355]);
                    break;

                case "Koppal":
                    latLngs.push([15.3450,76.1548]);
                    break;

                case "Ballari":
                    latLngs.push([15.1394,76.9214]);
                    break;

                case "Ramanagara":
                    latLngs.push([12.7219,77.2810]);
                    break;

                case "Mandya":
                    latLngs.push([12.5239,76.8953]);
                    break;

                case "Mysuru":
                    latLngs.push([12.2958,76.6394]);
                    break;

                case "Belagavi":
                    latLngs.push([15.8497,74.4977]);
                    break;

                case "Hubballi":
                    latLngs.push([15.3647,75.1240]);
                    break;

                case "Mangaluru":
                    latLngs.push([12.9141,74.8560]);
                    break;

            }

        });

        if(routeLine){

            map.removeLayer(routeLine);

        }

        routeLine = L.polyline(

            latLngs,

            {

                color:"#1565C0",

                weight:6,

                opacity:0.85

            }

        ).addTo(map);

        // Only zoom once
        if(!routeDrawn){

            map.fitBounds(routeLine.getBounds());

            routeDrawn = true;

        }

    }
    catch(error){

        console.log(error);

    }

}

// =====================================
// Start Live Tracking
// =====================================

function startLiveTracking(busNo){

    busNumber = busNo;

    // Reset everything for a new bus
    routeDrawn = false;

    currentLat = null;

    currentLng = null;

    if(animation){

        clearInterval(animation);

    }

    if(trackingInterval){

        clearInterval(trackingInterval);

    }

    loadTracking(busNo);

    trackingInterval = setInterval(()=>{

        loadTracking(busNo);

    },3000);

}

// =====================================
// Back Button
// =====================================

function goBack(){

    if(trackingInterval){

        clearInterval(trackingInterval);

    }

    window.location.href="dashboard.html";

}
