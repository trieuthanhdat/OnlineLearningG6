
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>

    <!-- Main Slider Start -->
    <div class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">

                    <nav class="navbar bg-light">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="#"
                                   ><i class="fa fa-home"></i>Categories</a
                                >
                            </li>
                        <c:forEach items="${CATEGORY_LIST}" var="list">
                            <li class="nav-item">
                                <a class="nav-link" href="#"
                                   ><i class="fa fa-shopping-bag"></i>${list.categoryName}</a
                                >
                            </li>
                        </c:forEach> 
                    </ul>
                </nav>

            </div>
            <div class="col-md-6">
                <div class="header-slider normal-slider">
                    <c:forEach items="${SUBJECT_LIST}" var="list">
                        <div class="header-slider-item">
                            <img src="assets/img/${list.thumbnail}" alt="${list.thumbnail}" style="height: 430px" />
                            <div class="header-slider-caption">
                                <p>${list.title}</p>
                                <a class="btn" href="CourseDetail?txtSubjectID=${list.subjectID}"
                                   ><i class="fa fa-shopping-cart"></i>Enroll Now</a
                                >
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-md-3">
                <div class="header-img">
                    <div class="img-item">
                        <img src="assets/img/slide_01.jpg" />
                        <a class="img-text" href="">
                            <p>Some text goes here that describes the image</p>
                        </a>
                    </div>
                    <div class="img-item">
                        <img src="assets/img/slide_02.jpg" />
                        <a class="img-text" href="">
                            <p>Some text goes here that describes the image</p>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Main Slider End -->
<!-- Info -->
<div class="blankSeparator"></div>
<div class="container">
    <div class="info">
        <div class="one_third">
            <h2>New Freebie</h2>
            <p>
                Proximet is my latest <span class="red">responsive</span> Site
                Template freebie. Proximet Template is simple and clean template
                with a lot attention to detail. It is suitable for a lot of
                <span class="green">different</span> business and private uses.
            </p>
            <a
                href=""
                title=""
                class="buttonhome"
                >&rarr; View</a
            >
        </div>
        <div class="two_third lastcolumn">
            <div class="one_half">
                <h2>Portfolio News</h2>
                <p>
                    Proximet is my latest <span class="red">responsive</span> Site
                    Template freebie. Proximet Template is simple and clean template
                    with a lot attention to detail. It is suitable for a lot of
                    <span class="green">different</span> business and private uses.
                </p>
                <a
                    href=""
                    title=""
                    class="buttonhome"
                    >&rarr; View</a
                >
            </div>
            <div class="one_half lastcolumn">
                <h2>Proximet News</h2>
                <p>
                    Proximet is my latest <span class="red">responsive</span> Site
                    Template freebie. Proximet Template is simple and clean template
                    with a lot attention to detail. It is suitable for a lot of
                    <span class="green">different</span> business and private uses.
                </p>
                <a
                    href=""
                    title=""
                    class="buttonhome"
                    >&rarr; View</a
                >
            </div>
        </div>
    </div>
</div>
<!-- Info -->

<c:if test="${not empty MYSUBJECT_LIST}">
    <!-- Courses learning Section Begin -->
    <section class="latest-preview-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Courses you are learning</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="lp-slider owl-carousel">
                    <c:forEach items="${MYSUBJECT_LIST}" var="list">
                        <div class="col-lg-3">
                            <div class="lp-item">
                                <div
                                    class="lp-pic set-bg"
                                    data-setbg="assets/img/${list.thumbnail}"
                                    >
                                    <div class="review-loader">
                                        <div class="loader-circle-wrap">
                                            <div class="loader-circle">
                                                <span
                                                    class="circle-progress"
                                                    data-cpid="id"
                                                    data-cpvalue="75"
                                                    data-cpcolor="#c20000"
                                                    ></span>
                                                <div class="review-point">7.5</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="lp-text">
                                    <h6>
                                        <a href="#">${list.title}</a>
                                    </h6>
                                    <div class="progress">
                                        <div class="progress-bar" role="progressbar" aria-valuenow="70"
                                             aria-valuemin="0" aria-valuemax="100" style="width:70%">
                                            70%
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </div>
        </div>
    </section>
    <!-- Courses learning Section End -->
</c:if>


<!-- Category News Start-->
<div class="cat-news">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Most Enrolled Courses</h2>
                <div class="row cn-slider">
                    <div class="col-md-6">
                        <div class="cn-img">
                            <img src="assets/img/news-350x223-1.jpg" />
                            <div class="cn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="cn-img">
                            <img src="assets/img/news-350x223-2.jpg" />
                            <div class="cn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="cn-img">
                            <img src="assets/img/news-350x223-3.jpg" />
                            <div class="cn-title">
                                <a href="">Lorem ipsum dolor sit</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Category News End-->

<!-- Courses recommended Section Begin -->
<section class="latest-preview-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Recommend for you</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="lp-slider owl-carousel">

                <c:forEach items="${SUBJECT_LIST}" var="list">
                    <div class="col-lg-3">
                        <div class="lp-item">
                            <div
                                class="lp-pic set-bg"
                                data-setbg="assets/img/${list.thumbnail}"
                                >
                                <div class="review-loader">
                                    <div class="loader-circle-wrap">
                                        <div class="loader-circle">
                                            <span
                                                class="circle-progress"
                                                data-cpid="id"
                                                data-cpvalue="75"
                                                data-cpcolor="#c20000"
                                                ></span>
                                            <div class="review-point">7.5</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="lp-text">
                                <h6>
                                    <a href="#"
                                       >${list.title}</a
                                    >
                                </h6>
                                <ul>
                                    <li><i class="fa fa-clock-o"></i> Aug 01, 2019</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</section>
<!-- Courses recommend Section End -->


<!-- Top Categories Section Begin -->
<section class="latest-preview-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Top categories</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="lp-slider owl-carousel">
                <c:forEach items="${CATEGORY_LIST}" var="list">
                    <a href="" class="col-sm card lp-item">
                        <div class="card-body">${list.categoryName}</div>
                    </a>
                </c:forEach>

            </div>

        </div>
    </div>
</section>
<!-- Top Categories Section End -->

<!-- =======  Courses Section ======= -->
<section id="popular-courses" class="courses">
    <div class="container" data-aos="fade-up">
        <div class="section-title">
            <h2>Courses</h2>
        </div>

        <div class="row" data-aos="zoom-in" data-aos-delay="100">
            <c:forEach items="${SUBJECT_LIST}" var="list">
                <c:forEach items="${CATEGORY_LIST}" var="catelist">
                    <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                        <c:set var="catename" value="${catelist.categoryName}"></c:set>
                    </c:if>
                </c:forEach>
                <div class="col-lg-4 col-md-6 d-flex align-items-stretch">
                    <div class="course-item">
                        <img src="assets/img/${list.thumbnail}" class="img-fluid" alt="..." />
                        <div class="course-content">
                            <div
                                class="d-flex justify-content-between align-items-center mb-3"
                                >
                                <h4>${catename}t</h4>
                                <p class="price"></p>
                            </div>

                            <h3><a href="course-details.html">${list.title}</a></h3>
                            <p>
                                ${list.briefInfo}.
                            </p>
                            <div
                                class="
                                trainer
                                d-flex
                                justify-content-between
                                align-items-center
                                "
                                >
                                <div class="trainer-profile d-flex align-items-center">
                                    <img
                                        src="assets/img/trainers/trainer-1.jpg"
                                        class="img-fluid"
                                        alt=""
                                        />
                                    <span>Antonio</span>
                                </div>
                                <div class="trainer-rank d-flex align-items-center">
                                    <i class="bx bx-user"></i>&nbsp;50 &nbsp;&nbsp;
                                    <i class="bx bx-heart"></i>&nbsp;65
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Course Item-->
            </c:forEach>

        </div>
    </div>
</section>
<!-- End  Courses Section -->

<!-- Home Content Part - Box Two ==================================================
================================================== -->
<div class="container clients">
    <div class="sepContainer"></div>
    <h2>Our Clients</h2>
    <div class="one_sixth"><img src="assets/img/user_01.png" alt="" /></div>
    <!-- end one_sixth -->
    <div class="one_sixth"><img src="assets/img/user_02.png" alt="" /></div>
    <!-- end one_sixth -->
    <div class="one_sixth"><img src="assets/img/user_03.png" alt="" /></div>
    <!-- end one_sixth -->
    <div class="one_sixth"><img src="assets/img/user_01.png" alt="" /></div>
    <!-- end one_sixth -->
    <div class="one_sixth"><img src="assets/img/user_02.png" alt="" /></div>
    <!-- end one_sixth -->
    <div class="one_sixth lastcolumn">
        <img src="assets/img/user_03.png" alt="" />
    </div>
    <!-- end one_sixth lastCol -->
</div>
<!-- end container -->

<jsp:include page="WebFragment/footer.jsp"></jsp:include>