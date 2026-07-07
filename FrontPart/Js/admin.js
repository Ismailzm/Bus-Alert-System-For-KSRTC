// =========================
// Load All Buses
// =========================

// =========================
// Load All Buses
// =========================


let editMode = false;

let selectedBusNumber = "";

let busToDelete = "";




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

async function loadBuses() {

    try {

        const response = await fetch("https://sincere-perfection-production-b375.up.railway.app/api/buses");

        const buses = await response.json();

        const table = document.getElementById("busTable");

        table.innerHTML = "";

        buses.forEach(bus => {

            table.innerHTML += `

            <tr>

                <td>${bus.busNumber}</td>

                <td>${bus.busName}</td>

                <td>${bus.source}</td>

                <td>${bus.destination}</td>

                <td>${bus.busType}</td>

               <td>

    <button onclick="editBus('${bus.busNumber}')">
        ✏️ Edit
    </button>

    <button onclick="deleteBus('${bus.busNumber}')"
            style="background:#d32f2f;margin-top:8px;">
        🗑 Delete
    </button>

</td>

<td>

    <button
        onclick="startTrip('${bus.busNumber}')"
        class="startTripBtn">

        🟢 Start Trip

    </button>

    <button
        onclick="endTrip('${bus.busNumber}')"
        class="endTripBtn">

        🔴 End Trip

    </button>

    <button
        onclick="reverseTrip('${bus.busNumber}')"
        class="reverseTripBtn">

        🔄 Reverse Trip

    </button>

</td>

            </tr>

            `;

        });

    }
    catch(error){

        console.log(error);

        showToast("Unable to load buses.");

    }

}

// =========================
// Open Delete Modal
// =========================

function deleteBus(busNumber){

    busToDelete = busNumber;

    document.getElementById("deleteMessage").innerHTML =
        "Are you sure you want to delete <b>" + busNumber + "</b>?";

    document.getElementById("deleteModal").style.display="flex";

}


// =========================
// Close Delete Modal
// =========================

function closeDeleteModal(){

    document.getElementById("deleteModal").style.display="none";

}

// =========================
// Confirm Delete
// =========================

async function confirmDelete(){

    try{

        const response = await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/buses/${busToDelete}`,

            {

                method:"DELETE"

            }

        );

        if(response.ok){

            closeDeleteModal();

        showToast("✅ Bus Deleted Successfully");

            loadBuses();

        }
        else{

            showToast("Failed to Delete Bus");

        }

    }
    catch(error){

        console.log(error);

    }

}

async function editBus(busNumber){


    document.getElementById("busForm").scrollIntoView({

    behavior: "smooth",

    block: "start"

});

document.getElementById("busName").focus();

    try{

        const response = await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/buses/${busNumber}`

        );

        const bus = await response.json();

        document.getElementById("busNumber").value = bus.busNumber;
        document.getElementById("busNumber").disabled = true;

        document.getElementById("busName").value = bus.busName;

        document.getElementById("source").value = bus.source;

        document.getElementById("destination").value = bus.destination;

        document.getElementById("departure").value =
            bus.scheduledDeparture;

        document.getElementById("arrival").value =
            bus.scheduledArrival;

        document.getElementById("busType").value =
            bus.busType;

        document.getElementById("saveBtn").innerHTML =
            "💾 Update Bus";

        editMode = true;

        selectedBusNumber = bus.busNumber;

    }
    catch(error){

        console.log(error);

    }

}



async function saveBus(){

    if(editMode){

        updateBus();

    }
    else{

        addBus();

    }

}
// =========================
// Add New Bus
// =========================



async function addBus() {

    const bus = {

        busNumber: document.getElementById("busNumber").value.trim(),

        busName: document.getElementById("busName").value.trim(),

        source: document.getElementById("source").value.trim(),

        destination: document.getElementById("destination").value.trim(),

        scheduledDeparture: document.getElementById("departure").value,

        scheduledArrival: document.getElementById("arrival").value,

        busType: document.getElementById("busType").value
       

    };

    if (
        bus.busNumber === "" ||
        bus.busName === "" ||
        bus.source === "" ||
        bus.destination === "" ||
        bus.scheduledDeparture === "" ||
        bus.scheduledArrival === ""
    ) {

      showToast("Please fill all fields.");

        return;

    }

    try {

        const response = await fetch("https://sincere-perfection-production-b375.up.railway.app/api/buses", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(bus)

        });

        if (response.ok) {

            showToast(" Bus Added Successfully");

            document.getElementById("busNumber").value = "";
            document.getElementById("busName").value = "";
            document.getElementById("source").value = "";
            document.getElementById("destination").value = "";
            document.getElementById("departure").value = "";
            document.getElementById("arrival").value = "";






            

            loadBuses();

        } else {

            const errorText = await response.text();

            showToast("This Bus Alreday Exists !  Please add a Bus with Different Bus Number");

        }

    } catch (error) {

        console.log(error);

        showToast("Cannot connect to backend.");

    }

}


// =========================
// Update Bus
// =========================

async function updateBus(){

    const bus={

        busNumber:selectedBusNumber,

        busName:document.getElementById("busName").value.trim(),

        source:document.getElementById("source").value.trim(),

        destination:document.getElementById("destination").value.trim(),

        scheduledDeparture:document.getElementById("departure").value,

        scheduledArrival:document.getElementById("arrival").value,

        busType:document.getElementById("busType").value

    };

    try{

        const response=await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/buses/${selectedBusNumber}`,

            {

                method:"PUT",

                headers:{

                    "Content-Type":"application/json"

                },

                body:JSON.stringify(bus)

            }

        );

        if(response.ok){

            showToast("✅ Bus Updated Successfully");

            clearForm();

            document.getElementById("busNumber").disabled=false;

            editMode=false;

            selectedBusNumber="";

            document.getElementById("saveBtn").innerHTML="➕ Add Bus";

            loadBuses();

        }
        else{

            showToast("Failed to Update Bus");

        }

    }
    catch(error){

        console.log(error);

        showToast("Server Error");

    }

}

// =========================
// Clear Form
// =========================

function clearForm(){

    document.getElementById("busNumber").value="";

    document.getElementById("busName").value="";

    document.getElementById("source").value="";

    document.getElementById("destination").value="";

    document.getElementById("departure").value="";

    document.getElementById("arrival").value="";

    document.getElementById("busType").value="VOLVO";

}

// =========================
// Initial Load
// =========================

loadDistricts();

loadBuses();

// =========================
// Start Trip
// =========================

async function startTrip(busNumber){

    try{

        const response = await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/trips/start/${busNumber}`,

            {

                method:"POST"

            }

        );

        if(response.ok){

            showToast("🟢 Trip Started Successfully");

        }

        else{

            const msg = await response.text();

            showToast(msg);

        }

    }

    catch(error){

        console.log(error);

        showToast("Unable to Start Trip");

    }

}



// =========================
// End Trip
// =========================

async function endTrip(busNumber){

    try{

        const response = await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/trips/end/${busNumber}`,

            {

                method:"POST"

            }

        );

        if(response.ok){

            showToast("🔴 Trip Ended Successfully");

        }

        else{

            const msg = await response.text();

            showToast(msg);

        }

    }

    catch(error){

        console.log(error);

        showToast("Unable to End Trip");

    }

}

// =========================
// Reverse Trip
// =========================

async function reverseTrip(busNumber){

    try{

        const response = await fetch(

            `https://sincere-perfection-production-b375.up.railway.app/api/trips/reverse/${busNumber}`,

            {

                method:"POST"

            }

        );

        if(response.ok){

            showToast("🔄 Reverse Trip Started");

            loadBuses();

        }
        else{

            const msg = await response.text();

            showToast(msg);

        }

    }
    catch(error){

        console.log(error);

        showToast("Unable to Reverse Trip");

    }

}

