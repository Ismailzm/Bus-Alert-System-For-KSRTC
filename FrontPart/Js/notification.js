async function loadNotifications(){

    const userId = localStorage.getItem("userId");

    try{

        const response = await fetch(
            `https://laudable-integrity-production-6b9e.up.railway.app/api/notifications/user/${userId}`
        );

        const notifications = await response.json();

        const container =
            document.getElementById("notificationContainer");

        container.innerHTML="";

        if(notifications.length===0){

            container.innerHTML="<h2>No Notifications Available</h2>";

            return;

        }

        notifications.forEach(notification=>{

            container.innerHTML += `

            <div class="notification">

                <h3>🚌 Bus Alert</h3>

                <p>${notification.message}</p>

                <button
    class="deleteBtn"
    onclick="deleteNotification(${notification.id})">

    🗑 Delete

</button>

            </div>

            

            `;

        });

    }
    catch(error){

        console.log(error);

        showToast("Unable to Load Notifications");

    }

}

loadNotifications();

async function deleteNotification(id){

    if(!confirm("Delete this notification?")){

        return;

    }

    try{

        const response = await fetch(

            `https://laudable-integrity-production-6b9e.up.railway.app/api/notifications/${id}`,

            {

                method:"DELETE"

            }

        );

        if(response.ok){

            showToast("Notification Deleted");

            loadNotifications();

        }
        else{

            showToast("Unable to Delete");

        }

    }
    catch(error){

        console.log(error);

    }

}

async function clearAllNotifications(){

    if(!confirm("Clear all notifications?")){

        return;

    }

    try{

        const response = await fetch(

            `https://laudable-integrity-production-6b9e.up.railway.app/api/notifications/user/${localStorage.getItem("userId")}`,

            {

                method:"DELETE"

            }

        );

        if(response.ok){

            showToast("All Notifications Cleared");

            loadNotifications();

        }
        else{

            showToast("Unable to Clear");

        }

    }
    catch(error){

        console.log(error);

    }

}
