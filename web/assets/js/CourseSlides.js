/*  ---------------------------------------------------
    Template Name: Amin
    Description:  Amin magazine HTML Template
    Author: Colorlib
    Author URI: https://colorlib.com
    Version: 1.0
    Created: Colorlib
---------------------------------------------------------  */

"use strict";

(function ($) {
  /*------------------
        Preloader
    --------------------*/
  $(window).on("load", function () {
    $(".loader").fadeOut();
    $("#preloder").delay(200).fadeOut("slow");
  });

  /*------------------
        Background Set
    --------------------*/
  $(".set-bg").each(function () {
    var bg = $(this).data("setbg");
    $(this).css("background-image", "url(" + bg + ")");
  });
 
  

  /*------------------------
        Latest Review Slider
    --------------------------*/
  $(".lp-slider").owlCarousel({
    loop: true,
    margin: 0,
    items: 4,
    dots: true,
    nav: true,
    navText: [
      '<span class="slick-prev slick-arrow"></span>',
      '<span class="slick-next slick-arrow"></span>',
    ],
    smartSpeed: 1200,
    autoHeight: false,
    dotsEach: 2,
    autoplay: true,
    responsive: {
      320: {
        items: 1,
      },
      480: {
        items: 2,
      },
      768: {
        items: 3,
      },
      992: {
        items: 4,
      },
    },
  });
  
  /*------------------
        Circle Progress
    --------------------*/
  $(".circle-progress").each(function () {
    var cpvalue = $(this).data("cpvalue");
    var cpcolor = $(this).data("cpcolor");
    var cpid = $(this).data("cpid");

    $(this).append(
      '<div class="' + cpid + '"></div><div class="progress-value"></div>'
    );

    if (cpvalue < 100) {
      $("." + cpid).circleProgress({
        value: "0." + cpvalue,
        size: 40,
        thickness: 2,
        startAngle: -190,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    } else {
      $("." + cpid).circleProgress({
        value: 1,
        size: 40,
        thickness: 5,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    }
  });

  $(".circle-progress-1").each(function () {
    var cpvalue = $(this).data("cpvalue");
    var cpcolor = $(this).data("cpcolor");
    var cpid = $(this).data("cpid");

    $(this).append(
      '<div class="' + cpid + '"></div><div class="progress-value"></div>'
    );

    if (cpvalue < 100) {
      $("." + cpid).circleProgress({
        value: "0." + cpvalue,
        size: 60,
        thickness: 2,
        startAngle: -190,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    } else {
      $("." + cpid).circleProgress({
        value: 1,
        size: 60,
        thickness: 5,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    }
  });

  $(".circle-progress-2").each(function () {
    var cpvalue = $(this).data("cpvalue");
    var cpcolor = $(this).data("cpcolor");
    var cpid = $(this).data("cpid");

    $(this).append(
      '<div class="' + cpid + '"></div><div class="progress-value"></div>'
    );

    if (cpvalue < 100) {
      $("." + cpid).circleProgress({
        value: "0." + cpvalue,
        size: 200,
        thickness: 5,
        startAngle: -190,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    } else {
      $("." + cpid).circleProgress({
        value: 1,
        size: 200,
        thickness: 5,
        fill: cpcolor,
        emptyFill: "rgba(0, 0, 0, 0)",
      });
    }
  });
})(jQuery);
