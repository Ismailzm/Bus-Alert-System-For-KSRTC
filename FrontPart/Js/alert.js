async function createAlert(){

    const source=document.getElementById("source").value.trim();

    const destination=document.getElementById("destination").value.trim();

    const busType=document.getElementById("busType").value;

    if(source==="" || destination===""){

        showToast("Please enter Source and Destination");

        return;

    }

    try{

        const response=await fetch("http://localhost:8080/api/alerts",{

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

            document.getElementById("message").innerHTML="✅ Alert Created Successfully";

            document.getElementById("source").value="";

            document.getElementById("destination").value="";

        }
        else{

            document.getElementById("message").innerHTML="❌ Failed to Create Alert";

        }

    }
    catch(error){

        console.log(error);

        showToast("Server Error");

    }

}