$(function () {
  //defining all needed variables
  var $overlay = $(".overlay");
  var $mainPopUp = $(".main-popup");
  var $signIn = $("#sign-in-tab");
  var $register = $("#register-tab");
  var $formSignIn = $("form.sign-in-form");
  var $formRegister = $("form.register-form");

  $("#sign-in").on("click", function () {
    $overlay.addClass("visible");
    $mainPopUp.addClass("visible");
    $signIn.addClass("active");
    $register.removeClass("active");
    $formRegister.removeClass("move-left");
    $formSignIn.removeClass("move-left");
  });
  $overlay.on("click", function () {
    $(this).removeClass("visible");
    $mainPopUp.removeClass("visible");
  });
  $("#popup-close-button a").on("click", function (e) {
    e.preventDefault();
    $overlay.removeClass("visible");
    $mainPopUp.removeClass("visible");
  });

  $signIn.on("click", function () {
    $signIn.addClass("active");
    $register.removeClass("active");
    $formSignIn.removeClass("invisible");
    $formRegister.removeClass("visible");
  });

  $register.on("click", function () {
    $signIn.removeClass("active");
    $register.addClass("active");
    $formSignIn.addClass("invisible");
    $formRegister.addClass("visible");
  });

 
});
