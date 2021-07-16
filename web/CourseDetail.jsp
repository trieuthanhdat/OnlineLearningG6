<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="overlay"></div>
<!-- ======= Pricing Section ======= -->
<div class="pricing">
    <div class="container" id="pricing" data-aos="fade-up">
        <div class="row">
            <div class="col-lg-3 col-md-6">
                <div class="box">
                    <h3>Free</h3>
                    <h4><sup>$</sup>0<span> / month</span></h4>
                    <ul>
                        <li>Aida dere</li>
                        <li>Nec feugiat nisl</li>
                        <li>Nulla at volutpat dola</li>
                        <li class="na">Pharetra massa</li>
                        <li class="na">Massa ultricies mi</li>
                    </ul>
                    <div class="btn-wrap">
                        <a href="#" class="btn-buy">Buy Now</a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 mt-4 mt-md-0">
                <div class="box featured">
                    <h3>Business</h3>
                    <h4><sup>$</sup>19<span> / month</span></h4>
                    <ul>
                        <li>Aida dere</li>
                        <li>Nec feugiat nisl</li>
                        <li>Nulla at volutpat dola</li>
                        <li>Pharetra massa</li>
                        <li class="na">Massa ultricies mi</li>
                    </ul>
                    <div class="btn-wrap">
                        <a href="#" class="btn-buy">Buy Now</a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 mt-4 mt-lg-0">
                <div class="box">
                    <h3>Developer</h3>
                    <h4><sup>$</sup>29<span> / month</span></h4>
                    <ul>
                        <li>Aida dere</li>
                        <li>Nec feugiat nisl</li>
                        <li>Nulla at volutpat dola</li>
                        <li>Pharetra massa</li>
                        <li>Massa ultricies mi</li>
                    </ul>
                    <div class="btn-wrap">
                        <a href="#" class="btn-buy">Buy Now</a>
                    </div>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 mt-4 mt-lg-0">
                <div class="box">
                    <span class="advanced">Advanced</span>
                    <h3>Ultimate</h3>
                    <h4><sup>$</sup>49<span> / month</span></h4>
                    <ul>
                        <li>Aida dere</li>
                        <li>Nec feugiat nisl</li>
                        <li>Nulla at volutpat dola</li>
                        <li>Pharetra massa</li>
                        <li>Massa ultricies mi</li>
                    </ul>
                    <div class="btn-wrap">
                        <a href="#" class="btn-buy">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Pricing Section -->

<main id="main">
    <!-- ======= Breadcrumbs ======= -->
    <div class="breadcrumbs" data-aos="fade-in">
        <div class="container">
            <h2>${CURRENT_SUBJECT.title}</h2>
            <p>
                ${CURRENT_SUBJECT.briefInfo}.
            </p>
        </div>
    </div>
    <!-- End Breadcrumbs -->

    <!-- ======= Cource Details Section ======= -->
    <section id="course-details" class="course-details">
        <div class="container" data-aos="fade-up">
            <div class="row">
                <div class="col-lg-8">
                    <img
                        src="assets/img/course-details.jpg"
                        class="img-fluid"
                        alt=""
                        />
                    <h3>Description</h3>
                    <p>
                        ${CURRENT_SUBJECT.briefInfo}.
                    </p>
                </div>
                <div class="col-lg-4">
                    <div
                        class="
                        course-info
                        d-flex
                        justify-content-between
                        align-items-center
                        "
                        >
                        <h5>Professor</h5>
                        <p><a href="#">(${CURRENT_SUBJECT.ownerID})</a></p>
                    </div>

                    <div
                        class="
                        course-info
                        d-flex
                        justify-content-between
                        align-items-center
                        "
                        >
                        <h5>Course Fee</h5>
                        <c:forEach items="${CURRENT_SUBJECT.packages}" var="paList">
                            <p>
                                ${paList.listPrice-paList.salePrice}VND<br>

                            </p>
                        </c:forEach>
                    </div>

                    <div
                        class="
                        course-info
                        d-flex
                        justify-content-between
                        align-items-center
                        "
                        >
                        <h5>Available Seats</h5>
                        <p>${CURRENT_SUBJECT.numOfLessons}</p>
                    </div>
                    <div class="course-info d-flex align-items-center">
                        <input
                            type="button"
                            class="btn btn-primary btn-block"
                            id="enrollBtn"
                            value="Enroll now"
                            />
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- End Cource Details Section -->

    <!-- Dropdown content Section -->
    <section class="container aos-init aos-animate">
        <div class="row">
            <div class="middle col-lg-8">
                <h2>Course Contents</h2>
                <p>Total length: 10 hours</p>
                <div class="menu">
                    <ul>
                        
                        <c:forEach items="${TOPIC_LIST}" var="tpList">
                            <li class="item" id="chapterI">
                                <a href="#chapterI" class="btn"> ${tpList.name} </a>
                                <div class="smenu">
                                    <c:forEach items="${SUBLESSON_LIST}" var="list">
                                        <c:if test="${tpList.lessonID eq list.topicID}">
                                            <a href="">${list.name}</a>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </li>
                        </c:forEach>

                        <li class="item">
                            <a href="#" class="btn">collapse All</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <!-- End  Dropdown content Section -->

    <!-- Student also enroll courses -->
    <section class="container aos-init aos-animate">
        <div class="sidebar-option col-lg-8">
            <div class="hardware-guides">
                <div class="section-title">
                    <h3>Student also enrolled</h3>
                </div>
                <div class="trending-item">
                    <div class="ti-pic">
                        <img src="assets/img/trending/trending-1.jpg" alt="" />
                    </div>
                    <div class="ti-text">
                        <h6>
                            <a href="#"
                               >A Monster Prom poster got hijacked for a Papa Roach
                                concert...</a
                            >
                        </h6>
                        <ul>
                            <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                            <li>$12</li>
                        </ul>
                    </div>
                </div>
                <div class="trending-item">
                    <div class="ti-pic">
                        <img src="assets/img/trending/trending-6.jpg" alt="" />
                    </div>
                    <div class="ti-text">
                        <h6>
                            <a href="#"
                               >Facebook wants to read your thoughts with its
                                augmented...</a
                            >
                        </h6>
                        <ul>
                            <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                            <li>$12</li>
                        </ul>
                    </div>
                </div>
                <div class="trending-item">
                    <div class="ti-pic">
                        <img src="assets/img/trending/trending-7.jpg" alt="" />
                    </div>
                    <div class="ti-text">
                        <h6>
                            <a href="#"
                               >This gaming laptop with a GTX 1660 Ti and 32GB of RAM is
                                down...</a
                            >
                        </h6>
                        <ul>
                            <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                            <li>$12</li>
                        </ul>
                    </div>
                </div>
                <div class="trending-item">
                    <div class="ti-pic">
                        <img src="assets/img/trending/trending-8.jpg" alt="" />
                    </div>
                    <div class="ti-text">
                        <h6>
                            <a href="#"
                               >Jalopy developer is making a game where you 'build
                                stuff...</a
                            >
                        </h6>
                        <ul>
                            <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                            <li>$12</li>
                        </ul>
                    </div>
                </div>

                <!-- more items here -->
                <div class="more-items smenu" id="more-items">
                    <div class="trending-item">
                        <div class="ti-pic">
                            <img src="assets/img/trending/trending-5.jpg" alt="" />
                        </div>
                        <div class="ti-text">
                            <h6>
                                <a href="#"
                                   >A Monster Prom poster got hijacked for a Papa Roach
                                    concert...</a
                                >
                            </h6>
                            <ul>
                                <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                                <li>$12</li>
                            </ul>
                        </div>
                    </div>
                    <div class="trending-item">
                        <div class="ti-pic">
                            <img src="assets/img/trending/trending-6.jpg" alt="" />
                        </div>
                        <div class="ti-text">
                            <h6>
                                <a href="#"
                                   >Facebook wants to read your thoughts with its
                                    augmented...</a
                                >
                            </h6>
                            <ul>
                                <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                                <li>$12</li>
                            </ul>
                        </div>
                    </div>
                    <div class="trending-item">
                        <div class="ti-pic">
                            <img src="assets/img/trending/trending-7.jpg" alt="" />
                        </div>
                        <div class="ti-text">
                            <h6>
                                <a href="#"
                                   >This gaming laptop with a GTX 1660 Ti and 32GB of RAM is
                                    down...</a
                                >
                            </h6>
                            <ul>
                                <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                                <li>$12</li>
                            </ul>
                        </div>
                    </div>
                    <div class="trending-item">
                        <div class="ti-pic">
                            <img src="assets/img/trending/trending-8.jpg" alt="" />
                        </div>
                        <div class="ti-text">
                            <h6>
                                <a href="#"
                                   >Jalopy developer is making a game where you 'build
                                    stuff...</a
                                >
                            </h6>
                            <ul>
                                <li><i class="far fa-clock"></i> Aug 01, 2019</li>
                                <li>$12</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- showmore btn -->
                <ul>
                    <li class="item" >
                        <a id="show-btn" class="btn">Continue</a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
    <!-- Student also enroll courses -->

    <!-- ======= Cource Details Tabs Section ======= -->
    <section id="cource-details-tabs" class="cource-details-tabs">
        <div class="container" data-aos="fade-up">
            <div class="row">
                <div class="col-lg-9 mt-4 mt-lg-0">
                    <div class="tab-content">
                        <div class="tab-pane active show" id="tab-1">
                            <div class="row">
                                <div class="col-lg-8 details order-2 order-lg-1">
                                    <h3>Architecto ut aperiam autem id</h3>
                                    <p class="font-italic">
                                        Qui laudantium consequatur laborum sit qui ad sapiente
                                        dila parde sonata raqer a videna mareta paulona marka
                                    </p>
                                    <p>
                                        Et nobis maiores eius. Voluptatibus ut enim blanditiis
                                        atque harum sint. Laborum eos ipsum ipsa odit magni.
                                        Incidunt hic ut molestiae aut qui. Est repellat minima
                                        eveniet eius et quis magni nihil. Consequatur dolorem
                                        quaerat quos qui similique accusamus nostrum rem vero
                                    </p>
                                </div>
                                <div class="col-lg-4 text-center order-1 order-lg-2">
                                    <img
                                        src="assets/img/course-details-tab-1.png"
                                        alt=""
                                        class="img-fluid"
                                        />
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab-2">
                            <div class="row">
                                <div class="col-lg-8 details order-2 order-lg-1">
                                    <h3>Et blanditiis nemo veritatis excepturi</h3>
                                    <p class="font-italic">
                                        Qui laudantium consequatur laborum sit qui ad sapiente
                                        dila parde sonata raqer a videna mareta paulona marka
                                    </p>
                                    <p>
                                        Ea ipsum voluptatem consequatur quis est. Illum error
                                        ullam omnis quia et reiciendis sunt sunt est. Non
                                        aliquid repellendus itaque accusamus eius et velit ipsa
                                        voluptates. Optio nesciunt eaque beatae accusamus lerode
                                        pakto madirna desera vafle de nideran pal
                                    </p>
                                </div>
                                <div class="col-lg-4 text-center order-1 order-lg-2">
                                    <img
                                        src="assets/img/course-details-tab-2.png"
                                        alt=""
                                        class="img-fluid"
                                        />
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab-3">
                            <div class="row">
                                <div class="col-lg-8 details order-2 order-lg-1">
                                    <h3>Impedit facilis occaecati odio neque aperiam sit</h3>
                                    <p class="font-italic">
                                        Eos voluptatibus quo. Odio similique illum id quidem non
                                        enim fuga. Qui natus non sunt dicta dolor et. In
                                        asperiores velit quaerat perferendis aut
                                    </p>
                                    <p>
                                        Iure officiis odit rerum. Harum sequi eum illum corrupti
                                        culpa veritatis quisquam. Neque necessitatibus illo
                                        rerum eum ut. Commodi ipsam minima molestiae sed
                                        laboriosam a iste odio. Earum odit nesciunt fugiat sit
                                        ullam. Soluta et harum voluptatem optio quae
                                    </p>
                                </div>
                                <div class="col-lg-4 text-center order-1 order-lg-2">
                                    <img
                                        src="assets/img/course-details-tab-3.png"
                                        alt=""
                                        class="img-fluid"
                                        />
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab-4">
                            <div class="row">
                                <div class="col-lg-8 details order-2 order-lg-1">
                                    <h3>
                                        Fuga dolores inventore laboriosam ut est accusamus
                                        laboriosam dolore
                                    </h3>
                                    <p class="font-italic">
                                        Totam aperiam accusamus. Repellat consequuntur iure
                                        voluptas iure porro quis delectus
                                    </p>
                                    <p>
                                        Eaque consequuntur consequuntur libero expedita in
                                        voluptas. Nostrum ipsam necessitatibus aliquam fugiat
                                        debitis quis velit. Eum ex maxime error in consequatur
                                        corporis atque. Eligendi asperiores sed qui veritatis
                                        aperiam quia a laborum inventore
                                    </p>
                                </div>
                                <div class="col-lg-4 text-center order-1 order-lg-2">
                                    <img
                                        src="assets/img/course-details-tab-4.png"
                                        alt=""
                                        class="img-fluid"
                                        />
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab-5">
                            <div class="row">
                                <div class="col-lg-8 details order-2 order-lg-1">
                                    <h3>
                                        Est eveniet ipsam sindera pad rone matrelat sando reda
                                    </h3>
                                    <p class="font-italic">
                                        Omnis blanditiis saepe eos autem qui sunt debitis porro
                                        quia.
                                    </p>
                                    <p>
                                        Exercitationem nostrum omnis. Ut reiciendis repudiandae
                                        minus. Omnis recusandae ut non quam ut quod eius qui.
                                        Ipsum quia odit vero atque qui quibusdam amet. Occaecati
                                        sed est sint aut vitae molestiae voluptate vel
                                    </p>
                                </div>
                                <div class="col-lg-4 text-center order-1 order-lg-2">
                                    <img
                                        src="assets/img/course-details-tab-5.png"
                                        alt=""
                                        class="img-fluid"
                                        />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- End Cource Details Tabs Section -->
</main>
<!-- End #main -->


<jsp:include page="WebFragment/footer.jsp"></jsp:include>
