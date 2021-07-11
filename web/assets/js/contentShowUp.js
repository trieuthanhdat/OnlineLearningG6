const contentBtn = document.querySelectorAll(".cBtn");
const collapseBtn = document.getElementById("collapseBtn");
const subMenu = document.querySelectorAll(".menu1 .smenu");

contentBtn.forEach(function (item) {
  item.addEventListener("click", function () {
    subMenu.forEach(function (subItem) {
      if (subItem.parentElement === item) {
        subItem.classList.toggle("active");
      }
    });
  });
});

collapseBtn.addEventListener("click", function () {
  var txt = collapseBtn.textContent == "Show All" ? "collapse All" : "Show All";

  subMenu.forEach(function (item) {
    item.classList.add("active");

    console.log(document.querySelectorAll("#dropdown-lesson .active").length);
    if (
      subMenu.length ==
      document.querySelectorAll("#dropdown-lesson .active").length
    ) {
      collapseBtn.textContent = "collapse All";
    }
    if (txt === "collapse All") {
      item.classList.add("active");
    } else if (txt === "Show All") {
      item.classList.remove("active");
    }
    collapseBtn.textContent = txt;
  });
});
// =================================================
const slideInBtn = document.getElementById('slideInBtn');
const content = document.getElementById('dropdown-lesson-toggle');
const closingBtn = document.getElementById('closingBtn');
const videoSection =document.getElementById('video-section');
const contenTab = document.getElementById('anchor-content-tab');
const topNav = document.getElementById('top-navbar');
const firstTopBar= document.getElementById('first-topBar');

function showContent(){
  content.classList.add("col-lg-3");
  slideInBtn.style.display = "none";
  closingBtn.style.fontSize = "36px";
  videoSection.classList.add('col-lg-9');
  topNav.classList.add('col-lg-9');
  firstTopBar.classList.add('col-lg-9');
  contenTab.style.display = 'none';
}
function closeContent(){
  content.classList.remove("col-lg-3");
  slideInBtn.style.display = "block";
  closingBtn.style.fontSize = "0px";
  videoSection.classList.remove('col-lg-9');
  topNav.classList.remove('col-lg-9');
  firstTopBar.classList.remove('col-lg-9');
  contenTab.style.display = 'block';
}


slideInBtn.addEventListener('click',showContent);

function closeNav(){
  closingBtn.style.fontSize = "0px";
  closeContent();
}
