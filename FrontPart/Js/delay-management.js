// ======================================
// Load Delay Reports
// ======================================

async function loadReports(){

    try{

        const response = await fetch(
            "http://localhost:8080/api/delay/pending"
        );

        const reports = await response.json();

        const table =
            document.getElementById("reportTable");

        table.innerHTML = "";

        if(reports.length === 0){

            table.innerHTML = `

            <tr>

                <td colspan="6">

                    No Pending Reports

                </td>

            </tr>

            `;

            return;

        }

        reports.forEach(report=>{

            table.innerHTML += `

            <tr>

                <td>${report.busNumber}</td>

                <td>${report.reason}</td>

                <td>${report.delayMinutes} Minutes</td>

                <td>${report.reportedAt.replace("T"," ")}</td>

                <td>${report.status}</td>

                <td>

                    <button
                        class="approveBtn"
                        onclick="approveReport(${report.id})">

                        ✅ Approve

                    </button>

                    <button
                        class="rejectBtn"
                        onclick="rejectReport(${report.id})">

                        ❌ Reject

                    </button>

                </td>

            </tr>

            `;

        });

    }
    catch(error){

        console.log(error);

        showToast("Unable to Load Reports");

    }

}

// ======================================
// Approve Report
// ======================================

async function approveReport(id){

    try{

        const response = await fetch(

            `http://localhost:8080/api/delay/approve/${id}`,

            {

                method:"PUT"

            }

        );

        if(response.ok){

            showToast("✅ Delay Approved");

            loadReports();

        }
        else{

            showToast("Unable to Approve");

        }

    }
    catch(error){

        console.log(error);

    }

}

// ======================================
// Reject Report
// ======================================

async function rejectReport(id){

    try{

        const response = await fetch(

            `http://localhost:8080/api/delay/reject/${id}`,

            {

                method:"PUT"

            }

        );

        if(response.ok){

            showToast("❌ Delay Rejected");

            loadReports();

        }
        else{

            showToast("Unable to Reject");

        }

    }
    catch(error){

        console.log(error);

    }

}

// ======================================
// Initial Load
// ======================================

loadReports();