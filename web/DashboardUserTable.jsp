<jsp:include page="WebFragment/DashboardHeader.jsp"></jsp:include>
<jsp:include page="WebFragment/DashboardAddUserModal.jsp"></jsp:include>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">

                    </h4>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-hidden="true"
                        >
                        &times;
                    </button>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <input
                        type="button"
                        class="btn btn-default"
                        data-dismiss="modal"
                        value="Cancel"
                        />
                    <input type="submit" class="btn btn-info" value="Save" />
                </div>
            </form>
        </div>
    </div>
</div>
<!--END Edit Modal HTML -->
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Delete Employee</h4>
                    <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-hidden="true"
                        >
                        &times;
                    </button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete these Records?</p>
                    <p class="text-warning">
                        <small>This action cannot be undone.</small>
                    </p>
                </div>
                <div class="modal-footer">
                    <input
                        type="button"
                        class="btn btn-default"
                        data-dismiss="modal"
                        value="Cancel"
                        />
                    <input type="submit" class="btn btn-danger" value="Delete" />
                </div>
            </form>
        </div>
    </div>
</div>
<!-- END Delete Modal HTML -->

<jsp:include page="WebFragment/DashboardSidebar.jsp"></jsp:include>
    <!-- Navbar -->
    <main class="main-content mt-1 border-radius-lg">
        <nav
            class="
            navbar navbar-main navbar-expand-lg
            px-0
            mx-4
            shadow-none
            border-radius-xl
            "
            id="navbarBlur"
            navbar-scroll="true"
            >
            <div class="container-fluid py-1 px-3">
                <nav aria-label="breadcrumb">
                    <ol
                        class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5"
                        >
                        <li class="breadcrumb-item text-sm">
                            <a class="opacity-5 text-dark" href="javascript:;">Pages</a>
                        </li>
                        <li
                            class="breadcrumb-item text-sm text-dark active"
                            aria-current="page"
                            >
                            Tables
                        </li>
                    </ol>
                    <h6 class="font-weight-bolder mb-0">Users Table</h6>
                </nav>
                <div
                    class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4"
                    id="navbar"
                    >
                    <div class="ms-md-auto pe-md-3 d-flex align-items-center">
                        <div class="input-group">
                            <span class="input-group-text text-body"
                                  ><i class="fas fa-search" aria-hidden="true"></i
                                ></span>
                            <input
                                type="text"
                                class="form-control"
                                placeholder="Type here..."
                                />
                        </div>
                    </div>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item d-flex align-items-center">
                            <a
                                href="javascript:;"
                                class="nav-link text-body font-weight-bold px-0"
                                >
                                <i class="fa fa-user me-sm-1"></i>
                                <span class="d-sm-inline d-none">Sign In</span>
                            </a>
                        </li>
                        <li class="nav-item d-xl-none ps-3 d-flex align-items-center">
                            <a
                                href="javascript:;"
                                class="nav-link text-body p-0"
                                id="iconNavbarSidenav"
                                >
                                <div class="sidenav-toggler-inner">
                                    <i class="sidenav-toggler-line"></i>
                                    <i class="sidenav-toggler-line"></i>
                                    <i class="sidenav-toggler-line"></i>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item px-3 d-flex align-items-center">
                            <a href="javascript:;" class="nav-link text-body p-0">
                                <i
                                    class="fa fa-cog fixed-plugin-button-nav cursor-pointer"
                                    ></i>
                            </a>
                        </li>
                        <li class="nav-item dropdown pe-2 d-flex align-items-center">
                            <a
                                href="javascript:;"
                                class="nav-link text-body p-0"
                                id="dropdownMenuButton"
                                data-bs-toggle="dropdown"
                                aria-expanded="false"
                                >
                                <i class="fa fa-bell cursor-pointer"></i>
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end px-2 py-3 me-sm-n4"
                                aria-labelledby="dropdownMenuButton"
                                >
                                <li class="mb-2">
                                    <a
                                        class="dropdown-item border-radius-md"
                                        href="javascript:;"
                                        >
                                        <div class="d-flex py-1">
                                            <div class="my-auto">
                                                <img
                                                    src="DashboardAssets/img/team-2.jpg"
                                                    class="avatar avatar-sm me-3"
                                                    />
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <h6 class="text-sm font-weight-normal mb-1">
                                                    <span class="font-weight-bold">New message</span>
                                                    from Laur
                                                </h6>
                                                <p class="text-xs text-secondary mb-0">
                                                    <i class="fa fa-clock me-1"></i>
                                                    13 minutes ago
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li class="mb-2">
                                    <a
                                        class="dropdown-item border-radius-md"
                                        href="javascript:;"
                                        >
                                        <div class="d-flex py-1">
                                            <div class="my-auto">
                                                <img
                                                    src="DashboardAssets/img/small-logos/logo-spotify.svg"
                                                    class="avatar avatar-sm bg-gradient-dark me-3"
                                                    />
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <h6 class="text-sm font-weight-normal mb-1">
                                                    <span class="font-weight-bold">New album</span> by
                                                    Travis Scott
                                                </h6>
                                                <p class="text-xs text-secondary mb-0">
                                                    <i class="fa fa-clock me-1"></i>
                                                    1 day
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a
                                        class="dropdown-item border-radius-md"
                                        href="javascript:;"
                                        >
                                        <div class="d-flex py-1">
                                            <div
                                                class="
                                                avatar avatar-sm
                                                bg-gradient-secondary
                                                me-3
                                                my-auto
                                                "
                                                >
                                                <svg
                                                    width="12px"
                                                    height="12px"
                                                    viewBox="0 0 43 36"
                                                    version="1.1"
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    xmlns:xlink="http://www.w3.org/1999/xlink"
                                                    >
                                                <title>credit-card</title>
                                                <g
                                                    id="Basic-Elements"
                                                    stroke="none"
                                                    stroke-width="1"
                                                    fill="none"
                                                    fill-rule="evenodd"
                                                    >
                                                <g
                                                    id="Rounded-Icons"
                                                    transform="translate(-2169.000000, -745.000000)"
                                                    fill="#FFFFFF"
                                                    fill-rule="nonzero"
                                                    >
                                                <g
                                                    id="Icons-with-opacity"
                                                    transform="translate(1716.000000, 291.000000)"
                                                    >
                                                <g
                                                    id="credit-card"
                                                    transform="translate(453.000000, 454.000000)"
                                                    >
                                                <path
                                                    class="color-background"
                                                    d="M43,10.7482083 L43,3.58333333 C43,1.60354167 41.3964583,0 39.4166667,0 L3.58333333,0 C1.60354167,0 0,1.60354167 0,3.58333333 L0,10.7482083 L43,10.7482083 Z"
                                                    id="Path"
                                                    opacity="0.593633743"
                                                    ></path>
                                                <path
                                                    class="color-background"
                                                    d="M0,16.125 L0,32.25 C0,34.2297917 1.60354167,35.8333333 3.58333333,35.8333333 L39.4166667,35.8333333 C41.3964583,35.8333333 43,34.2297917 43,32.25 L43,16.125 L0,16.125 Z M19.7083333,26.875 L7.16666667,26.875 L7.16666667,23.2916667 L19.7083333,23.2916667 L19.7083333,26.875 Z M35.8333333,26.875 L28.6666667,26.875 L28.6666667,23.2916667 L35.8333333,23.2916667 L35.8333333,26.875 Z"
                                                    id="Shape"
                                                    ></path>
                                                </g>
                                                </g>
                                                </g>
                                                </g>
                                                </svg>
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <h6 class="text-sm font-weight-normal mb-1">
                                                    Payment successfully completed
                                                </h6>
                                                <p class="text-xs text-secondary mb-0">
                                                    <i class="fa fa-clock me-1"></i>
                                                    2 days
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="container-fluid py-4">
            <div class="row mx-1">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Manage <b>Users</b></h2>
                                </div>
                                <div class="col-sm-6">
                                    <a
                                        href="#addEmployeeModal"
                                        class="btn btn-success"
                                        data-toggle="modal"
                                        ><i class="material-icons">&#xE147;</i>
                                        <span>Add New Employee</span></a
                                    >
                                    <a
                                        href="#deleteEmployeeModal"
                                        class="btn btn-danger"
                                        data-toggle="modal"
                                        ><i class="material-icons">&#xE15C;</i>
                                        <span>Delete</span></a
                                    >
                                </div>
                            </div>
                        </div>
                        <table
                            class="table table-striped table-hover"
                            id="user-table"
                            cellspacing="0"
                            width="100%"
                            >
                            <thead>
                                <tr>
                                    <th>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="selectAll" />
                                            <label for="selectAll"></label>
                                        </span>
                                    </th>
                                    <th>ID</th>
                                    <th>Full Name</th>
                                    <th>Date Created</th>
                                    <th>Role</th>
                                    <th>status</th>
                                    <th>action</th>
                                </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${USER_LIST}" var="list">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input
                                                type="checkbox"
                                                id="checkbox${list.userID}"
                                                name="options[]"
                                                value="${list.userID}"
                                                />
                                            <label for="checkbox${list.userID}"></label>
                                        </span>
                                    </td>
                                    <td>${list.userID}</td>
                                    <td>
                                        <c:forEach items="${USERPROFILE_LIST}" var="prList">
                                            <c:if test="${prList.email eq list.email}">
                                                <c:if test="${empty prList.avartar}">
                                                    <c:set var="avatar" value="userAvatar.jpg"></c:set>
                                                </c:if>
                                                <c:if test="${not empty prList.avartar}">
                                                    <c:set var="avatar" value="${prList.avartar}"></c:set>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>

                                        <a href="#"><img
                                                src="DashboardAssets/img/Avatar/${avatar}"
                                                class="avatar"
                                                alt="Avatar"/>${list.fullName}</a>
                                    </td>
                                    <td>${list.createdate}</td>
                                    <td>
                                        <form action="UpdateUser?userID=${list.userID}" method="post">
                                            <select
                                                class="form-select"
                                                name="selectRole"
                                                aria-label="Default select example">

                                                <option hidden value="${list.role}">${list.role}</option>
                                                <option value="Admin">Admin</option>
                                                <option value="Sale">Sale</option>
                                                <option value="Marketing">Marketing</option>
                                                <option value="Expert">Expert</option>
                                                <option value="User">User</option>

                                            </select>
                                    </td>
                                    <td>

                                        <select
                                            class="form-select"
                                            name="selectStatus"
                                            aria-label="Default select example">
                                            <c:if test="${list.status eq true}">
                                                <c:set value="Activated" var="status"></c:set>
                                            </c:if>
                                            <c:if test="${list.status eq false}">
                                                <c:set value="Deactivated" var="status"></c:set>
                                            </c:if>
                                            <option hidden value="${list.status}">${status}</option>
                                            <option value="true">Activated</option>
                                            <option value="false">Deactivated</option>
                                        </select>
                                    </td>
                                    <td>
                                        <a
                                            href="#editEmployeeModal"
                                            class="edit pop"
                                            data-toggle="modal"
                                            pageTitle="${list.fullName} information"
                                            pageName="DashboardUserTable.jsp">
                                            <i
                                                class="material-icons"
                                                data-toggle="tooltip"
                                                title="Edit"
                                                pageName="DashboardUserTable.jsp"
                                                >&#xE254;</i></a>
                                        <button
                                            type="submit"
                                            class="delete btn"
                                            data-toggle="modal">Save</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="WebFragment/DashboardFooter.jsp"></jsp:include>
        <jsp:include page="WebFragment/DashboardConfiguration.jsp"></jsp:include>
            <script>
                $(function () {
                $(".pop").click(function () {
                var pageTitle = $(this).attr('pageTitle');
                        var pageName = $(this).attr('pageName');
                        $(".modal .modal-title").html(pageTitle);
                        $(".modal .modal-body").create(
<div class="form-group">
    <label>Name</label>
    <input type="text" class="form-control" required />
            </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" class="form-control" required />
                        </div>
                        <div class="form-group">
                        <label>Address</label>
                        <textarea class="form-control" required></textarea>
                    </div>
                <div class="form-group">
                <label>Phone</label>
                <input type="text" class="form-control" required />
                    </div>
                        );
                        $(".modal").modal("show");
                        $(".modal .modal-body").load(pageName);
                });
                });
            </script>
        <jsp:include page="WebFragment/DashboardEndBody.jsp"></jsp:include>