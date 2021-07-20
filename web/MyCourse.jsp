<!-- category bar start -->
<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- category bar start -->

<div class="container-fluid mycourse">
    <div class="row">
        <div class="col-md-3">

            <div class="sidebar">

                <div class="sidebar-widget">
                    <div class="image">
                        <a href="#"
                           ><img src="assets/img/ads-2.jpg" alt="Image"
                              /></a>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <div class="tab-news">
                        <ul class="nav nav-pills nav-justified">
                            <li class="nav-item">
                                <a
                                    class="nav-link active"
                                    data-toggle="pill"
                                    href="#featured"
                                    >Featured</a
                                >
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#popular"
                                   >Popular</a
                                >
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="pill" href="#latest"
                                   >Latest</a
                                >
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div id="featured" class="container tab-pane active">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-1.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href="Lectures.jsp"
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-5.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                            <div id="popular" class="container tab-pane fade">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-1.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-2.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                            <div id="latest" class="container tab-pane fade">
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-5.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-4.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                                <div class="tn-news">
                                    <div class="tn-img">
                                        <img src="assets/img/news-350x223-3.jpg" />
                                    </div>
                                    <div class="tn-title">
                                        <a href=""
                                           >Lorem ipsum dolor sit amet consec adipis elit</a
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <div class="image">
                        <a href="https://htmlcodex.com"
                           ><img src="assets/img/ads-2.jpg" alt="Image"
                              /></a>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <h2 class="sw-title">News Category</h2>
                    <div class="category">
                        <ul>
                            <li><a href="">National</a><span>(98)</span></li>
                            <li><a href="">International</a><span>(87)</span></li>
                            <li><a href="">Economics</a><span>(76)</span></li>
                            <li><a href="">Politics</a><span>(65)</span></li>
                            <li><a href="">Lifestyle</a><span>(54)</span></li>
                            <li><a href="">Technology</a><span>(43)</span></li>
                            <li><a href="">Trades</a><span>(32)</span></li>
                        </ul>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <div class="image">
                        <a href="https://htmlcodex.com"
                           ><img src="assets/img/ads-2.jpg" alt="Image"
                              /></a>
                    </div>
                </div>

                <div class="sidebar-widget">
                    <h2 class="sw-title">Tags Cloud</h2>
                    <div class="tags">
                        <a href="">National</a>
                        <a href="">International</a>
                        <a href="">Economics</a>
                        <a href="">Politics</a>
                        <a href="">Lifestyle</a>
                        <a href="">Technology</a>
                        <a href="">Trades</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- category bar end -->

        <!-- =======  Courses Section ======= -->
        <div class="col-md-9 product-view">
            <div class="product-view-top">
                <div class="row">
                    <div class="col-md-3">
                        <div class="product-search">
                            <input type="email" placeholder="Search">
                            <button><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="product-short">
                            <div class="dropdown">
                                <div class="dropdown-toggle" data-toggle="dropdown">Product short by</div>
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
                                <div class="dropdown-toggle" data-toggle="dropdown">Instructor</div>
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
                                <div class="dropdown-toggle" data-toggle="dropdown">Category</div>
                                <div class="dropdown-menu dropdown-menu-right">

                                    <ul>
                                        <li >
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
                                               ><i class="fa fa-plus-square"></i>Software Engineering</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-female"></i>Art</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-child"></i>Graphic design</a
                                            >
                                        </li>
                                        <li >
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-tshirt"></i>Biology</a
                                            >
                                        </li>
                                        <li>
                                            <a class="nav-link dropdown-item" href="#"
                                               ><i class="fa fa-mobile-alt"></i>Physic</a
                                            >
                                        </li>
                                        <li >
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
            <section id="popular-courses" class="courses">
                <div class="container" data-aos="fade-up">
                    <div class="section-title">
                        <h2>Courses</h2>
                    </div>

                    <div class="row" data-aos="zoom-in" data-aos-delay="100">
                        <c:forEach items="${MY_COURSES}" var="list">
                            <div class="col-lg-3 col-md-4 d-flex align-items-stretch">
                                <div class="course-item">
                                    <img src="assets/img/${list.thumbnail}" class="img-fluid" alt="..." />
                                    <div class="course-content">
                                        <div
                                            class="d-flex justify-content-between align-items-center mb-3">

                                            <c:forEach items="${sessionScope.CATEGORY_LIST}" var="catelist" >
                                                <c:if test="${list.subjectCategoryID eq catelist.categoryID}">
                                                    <c:set value="${catelist.categoryName}" var="cate"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            <h4>
                                                ${cate}
                                            </h4>
                                            
                                        </div>

                                        <h3><a href="StartLearning?txtSubjectID=${list.subjectID}">${list.title}</a></h3>
                                        <p>
                                            ${list.briefInfo}.
                                        </p>
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" style="width: 25%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">25%</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
        </div>
        </section>
    </div>
</div>
<!-- End  Courses Section -->
<jsp:include page="WebFragment/footer.jsp"></jsp:include>