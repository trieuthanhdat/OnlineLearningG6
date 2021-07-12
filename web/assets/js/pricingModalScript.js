const enrollModal = document.getElementById("pricing");
const enrollBtn = document.getElementById("enrollBtn");
const closeBtn = document.getElementById("popup-close-button");

const overlay = document.getElementById("overlay");
  
function showModal(){
    enrollModal.style.display = "block";
    showOverlay();
}
function showOverlay(){
    overlay.style.display = "block";
}
function closeOverlay(){
    overlay.style.display  = "none";
}
function closeModal(){
    enrollModal.style.display = "none";
    closeOverlay();
}

enrollBtn.addEventListener("click", showModal);
overlay.addEventListener("click", closeModal);
closeBtn.addEventListener("click", closeModal);