
    <!--   Core JS Files   -->
    <script src="DashboardAssets/js/core/popper.min.js"></script>
    <script src="DashboardAssets/js/core/bootstrap.min.js"></script>
    <script src="DashboardAssets/js/plugins/smooth-scrollbar.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <!-- Edit/add/delete modal -->
    <!-- jquery
		============================================ -->
    <script src="DashboardAssets/js/vendor/jquery-1.12.4.min.js"></script>
    <!-- bootstrap JS
		============================================ -->
    <script src="DashboardAssets/js/bootstrap.min.js"></script>
    <!-- wow JS
		============================================ -->
    <script src="DashboardAssets/js/wow.min.js"></script>
    <!-- price-slider JS
		============================================ -->
    <script src="DashboardAssets/js/jquery-price-slider.js"></script>
    <!-- meanmenu JS
		============================================ -->
    <script src="DashboardAssets/js/jquery.meanmenu.js"></script>
    <!-- owl.carousel JS
		============================================ -->
    <script src="DashboardAssets/js/owl.carousel.min.js"></script>
    <!-- sticky JS
		============================================ -->
    <script src="DashboardAssets/js/jquery.sticky.js"></script>
    <!-- scrollUp JS
      
		============================================ -->
    <script src="DashboardAssets/js/jquery.scrollUp.min.js"></script>
    <!-- mCustomScrollbar JS
		============================================ -->
    <script src="DashboardAssets/js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="DashboardAssets/js/scrollbar/mCustomScrollbar-active.js"></script>
    <!-- metisMenu JS
		============================================ -->
    <script src="DashboardAssets/js/metisMenu/metisMenu.min.js"></script>
    <script src="DashboardAssets/js/metisMenu/metisMenu-active.js"></script>
    <!-- data table JS
		============================================ -->
    <script src="DashboardAssets/js/data-table/bootstrap-table.js"></script>
    <script src="DashboardAssets/js/data-table/tableExport.js"></script>
    <script src="DashboardAssets/js/data-table/data-table-active.js"></script>
    <script src="DashboardAssets/js/data-table/bootstrap-table-editable.js"></script>
    <script src="DashboardAssets/js/data-table/bootstrap-editable.js"></script>
    <script src="DashboardAssets/js/data-table/bootstrap-table-resizable.js"></script>
    <script src="DashboardAssets/js/data-table/colResizable-1.5.source.js"></script>
    <script src="DashboardAssets/js/data-table/bootstrap-table-export.js"></script>
    <!--  editable JS
		============================================ -->
    <script src="DashboardAssets/js/editable/jquery.mockjax.js"></script>
    <script src="DashboardAssets/js/editable/mock-active.js"></script>
    <script src="DashboardAssets/js/editable/select2.js"></script>
    <script src="DashboardAssets/js/editable/moment.min.js"></script>
    <script src="DashboardAssets/js/editable/bootstrap-datetimepicker.js"></script>
    <script src="DashboardAssets/js/editable/bootstrap-editable.js"></script>
    <script src="DashboardAssets/js/editable/xediable-active.js"></script>
    <!-- Chart JS
		============================================ -->
    <script src="DashboardAssets/js/chart/jquery.peity.min.js"></script>
    <script src="DashboardAssets/js/peity/peity-active.js"></script>
    <!-- tab JS
		============================================ -->
    <script src="DashboardAssets/js/tab.js"></script>
    <!-- plugins JS
		============================================ -->
    <script src="DashboardAssets/js/plugins.js"></script>
    <!-- main JS
		============================================ -->
    <script src="DashboardAssets/js/main.js"></script>
    <!-- tawk chat JS
		============================================ -->
    <script src="DashboardAssets/js/tawk-chat.js"></script>
    <!-- datatable JS 
    ============================================ -->

    <script
      src="https://code.jquery.com/jquery-latest.min.js"
      type="text/javascript"
    ></script>
    <script
      src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"
      type="text/javascript"
    ></script>
    <script
      src="https://cdn.datatables.net/1.10.11/js/dataTables.bootstrap.min.js"
      type="text/javascript"
    ></script>

    <script>
      $(document).ready(function () {
        $("#user-table").DataTable();
      });
    </script>
    <script>
      $(document).ready(function () {
        // Activate tooltip
        $('[data-toggle="tooltip"]').tooltip();

        // Select/Deselect checkboxes
        var checkbox = $('table tbody input[type="checkbox"]');
        $("#selectAll").click(function () {
          if (this.checked) {
            checkbox.each(function () {
              this.checked = true;
            });
          } else {
            checkbox.each(function () {
              this.checked = false;
            });
          }
        });
        checkbox.click(function () {
          if (!this.checked) {
            $("#selectAll").prop("checked", false);
          }
        });
      });
    </script>

    <script>
      var win = navigator.platform.indexOf("Win") > -1;
      if (win && document.querySelector("#sidenav-scrollbar")) {
        var options = {
          damping: "0.5",
        };
        Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
      }
    </script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="DashboardAssets/js/soft-ui-dashboard.min.js?v=1.0.2"></script>
  </body>
</html>
