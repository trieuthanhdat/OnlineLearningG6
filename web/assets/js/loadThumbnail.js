var image = document.getElementById("thumbnail-preview");

function loadImageFromLink() {
    var imageLink = document.getElementById("thumbnail-link").value;
    image.src = imageLink;
}


