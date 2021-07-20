const filterBtn = document.getElementById("filterBtn");
const accordion = document.getElementById("accordion");
const searchContent = document.getElementById("searched-content");


function ShowFilterSection(){
    searchContent.classList.toggle("col-lg-12");
    //searchContent.classList.add("col-lg-9");
    //accordion.style.width = "100%";
    accordion.classList.toggle("col-lg-3");
    accordion.classList.toggle("hidePanel");
}

filterBtn.addEventListener("click",ShowFilterSection);