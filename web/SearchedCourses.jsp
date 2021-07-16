<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<jsp:include page="WebFragment/PopUpSignInRegister.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- =======  Filter Section ======= -->
<section class="container">
    <c:set value="${SEARCH_COURSES_LIST}" var="list"/>
    <div class="col-md-12 product-view">
        <div class="product-view-top">
            <div class="row">
                <h2>${fn:length(list)} results for "${param.txtCourseName}"</h2>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="product-search">
                        <input type="email" placeholder="Search" />
                        <button><i class="fa fa-search"></i></button>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="product-short">
                        <div class="dropdown">
                            <div class="dropdown-toggle" data-toggle="dropdown">
                                Product short by
                            </div>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a href="#" class="dropdown-item">Newest</a>
                                <a href="#" class="dropdown-item">Popular</a>
                                <a href="#" class="dropdown-item">Most sale</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="product-price-range">
                        <div class="dropdown">
                            <div class="dropdown-toggle" data-toggle="dropdown">
                                Instructor
                            </div>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a href="#" class="dropdown-item">$0 to $50</a>
                                <a href="#" class="dropdown-item">$51 to $100</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="product-price-range">
                        <div class="dropdown">
                            <div class="dropdown-toggle" data-toggle="dropdown">
                                Category
                            </div>
                            <div class="dropdown-menu dropdown-menu-right">
                                <ul>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-home"></i>Categories</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-shopping-bag"></i>Business</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-plus-square"></i>Software
                                            Engineering</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-female"></i>Art</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-child"></i>Graphic design</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-tshirt"></i>Biology</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-mobile-alt"></i>Physic</a
                                        >
                                    </li>
                                    <li>
                                        <a class="nav-link dropdown-item" href="#"
                                           ><i class="fa fa-microchip"></i>Mathemetics</a
                                        >
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ======= END Filter Section ======= -->

<!--========== Searched courses =========-->
<section class="container">
    <div class="aos-init aos-animate col-lg-5">

    </div>
    <c:if test="${not empty list}">
        <div class="aos-init aos-animate col-lg-7">
            <div class="sidebar-option">
                <div class="hardware-guides">
                    <c:forEach items="${list}" var="item">
                        <a href="" class="trending-item">
                            <div class="ti-pic">
                                <img src="assets/img/${item.thumbnail}" alt="" />
                            </div>
                            <div class="ti-text">
                                <h6>
                                    <div href="#">${item.title}</div>
                                </h6>
                                <ul>
                                    <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                                    <li>total 10hrs - ${item.numOfLessons} lessons</li>
                                </ul>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${empty list}">
        <div class="aos-init aos-animate col-lg-12">
            <div class="sidebar-option">
                <div class="hardware-guides" style="text-align: center;">
                    <h2 style="color:red;">Sorry we don't have such course, please search something else!</h2>

                </div>
            </div>
        </div>
    </c:if>
</section>
<!--==========END Searched courses =========-->
<!-- Single News End-->
<jsp:include page="WebFragment/footer.jsp"></jsp:include>