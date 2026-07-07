
// =====================================
// Karnataka Districts
// =====================================

const districts = [

    "Bagalkote",
    "Ballari",
    "Belagavi",
    "Bengaluru",
    "Bidar",
    "Chamarajanagar",
    "Chikkaballapur",
    "Chikkamagaluru",
    "Chitradurga",
    "Dakshina Kannada",
    "Davanagere",
    "Dharwad",
    "Gadag",
    "Hassan",
    "Haveri",
    "Kalaburagi",
    "Kodagu",
    "Kolar",
    "Koppal",
    "Mandya",
    "Mysuru",
    "Raichur",
    "Ramanagara",
    "Shivamogga",
    "Tumakuru",
    "Udupi",
    "Uttara Kannada",
    "Vijayapura",
    "Yadgir"

];

function loadDistricts(){

    const source =
        document.getElementById("source");

    const destination =
        document.getElementById("destination");

    districts.forEach(district=>{

        source.innerHTML +=
            `<option value="${district}">${district}</option>`;

        destination.innerHTML +=
            `<option value="${district}">${district}</option>`;

    });

}
async function searchBus() {

    const source = document.getElementById("source").value.trim();
    const destination = document.getElementById("destination").value.trim();

    if (source === "" || destination === "") {
        showToast("Please enter Source and Destination");
        return;
    }

    try {

        const response = await fetch(
            `https://sincere-perfection-production-b375.up.railway.app/api/buses/search?source=${encodeURIComponent(source)}&destination=${encodeURIComponent(destination)}`
        );

        if (!response.ok) {
            throw new Error("Failed to fetch buses");
        }

        const buses = await response.json();

        const tableBody = document.getElementById("tableBody");

        tableBody.innerHTML = "";

        if (buses.length === 0) {

            tableBody.innerHTML =
                `<tr>
                    <td colspan="7">No buses found.</td>
                </tr>`;

            return;
        }

        buses.forEach(bus => {

    tableBody.innerHTML += `

    <tr>

        <td>${bus.busNumber}</td>

        <td>${bus.busName}</td>

        <td>${bus.source}</td>

        <td>${bus.destination}</td>

        <td>${bus.busType}</td>

        <td>${bus.scheduledDeparture}</td>

        <td>${bus.scheduledArrival}</td>

        <td>

            <button class="trackBtn"
                    onclick="trackBus('${bus.busNumber}')">

                🛰 Track Live

            </button>

        </td>

    </tr>

    `;

});

    }
    catch (error) {

        console.error(error);

        showToast("Unable to connect to the server.");

    }
    

}
async function trackBus(busNumber){

    try{

        await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/tracking/start?userId=${localStorage.getItem("userId")}&busNumber=${busNumber}`,

            {

                method:"POST"

            }

        );

    }
    catch(error){

        console.log(error);

    }

    window.location.href =
        "dashboard.html?bus=" + busNumber;

}
loadDistricts();
