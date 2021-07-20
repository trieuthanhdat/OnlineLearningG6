$(document).ready(function () {
  $("select").on("change", function () {
    var struct = $(".structure").find(":selected").attr("data-list");
    var serv = $(".service").find(":selected").attr("data-list");
    $(".project").each(function () {
      var classList = $(this).attr("class").split(/\s+/);
      //console.log(classList);
      if (struct === "all" && serv === "all") {
        $(".project").show();
      } else if (struct === "all" || serv === "all") {
        $(this).toggle(classList.includes(struct) || classList.includes(serv));
      } else {
        $(this).toggle(classList.includes(struct) && classList.includes(serv));
      }
    });
  });
});
