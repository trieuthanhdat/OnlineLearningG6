/***AVATAR SCRIPT***/

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      var fileurl = e.target.result;
      $(".profile-pic").attr("src", fileurl);
    };
    reader.readAsDataURL(input.files[0]);
  }
}
$(".file-upload").on("change", function () {
  readURL(this);
});
$(".upload-button").on("click", function () {
  $(".file-upload").click();
});
/***AVATAR SCRIPT***/
