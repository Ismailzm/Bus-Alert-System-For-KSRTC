function showToast(message,type="success"){

    let container=document.querySelector(".toast-container");

    if(!container){

        container=document.createElement("div");

        container.className="toast-container";

        document.body.appendChild(container);

    }

    const toast=document.createElement("div");

    toast.className="toast "+type;

    let icon="";

    switch(type){

        case "success":

            icon="✔ ";

            break;

        case "error":

            icon="✖ ";

            break;

        case "warning":

            icon="⚠ ";

            break;

        default:

            icon="ℹ ";

    }

    toast.innerHTML=icon+message;

    container.appendChild(toast);

    setTimeout(()=>{

        toast.style.animation="slideOut .4s forwards";

        setTimeout(()=>{

            toast.remove();

        },400);

    },3000);

}
