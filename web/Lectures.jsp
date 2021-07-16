<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
    <!-- Lesson content section -->
    <section class="lesson-content">
        <div class="" id="video-section">
            <div class="embed-responsive embed-responsive-21by9 z-depth-1-half">
                <iframe
                    class="embed-responsive-item"
                    src="https://www.youtube.com/embed/v64KOxKVLVg"
                    allowfullscreen
                    ></iframe>
            </div>

            <div class="lesson-description">
                <h2>Review section part 1(lesson)</h2>
                <ul class="nav nav-tabs">
                    <li>
                        <a data-toggle="tab" href="#search" class="active">Search</a>
                    </li>
                    <li id="anchor-content-tab">
                        <a data-toggle="tab" href="#content" id="anchor-content">Content</a>
                    </li>
                    <li><a data-toggle="tab" href="#announcement">Announcement</a></li>
                    <li><a data-toggle="tab" href="#overView">OverView</a></li>
                </ul>

                <div class="tab-content">
                    <div id="search" class="tab-pane fade in active show">
                        <h3>Search</h3>
                        <h4>Start a new search, lectures, lessons or resources</h4>
                        <!-- Search Section -->
                        <div class="input-group">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="Search this blog"
                                />
                            <div class="input-group-append">
                                <button
                                    class="btn btn-secondary"
                                    type="button"
                                    style="z-index: 0 !important"
                                    >
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div id="content" class="tab-pane fade">
                        <!-- Dropdown content Section -->

                        <div class="dropdown-lessons menu1" id="dropdown-lesson">
                            <div class="w-100 aos-init aos-animate">
                                <div class="">
                                    <div class="middle">
                                        <h2>Course Contents</h2>
                                        <p>Total length: 10 hours</p>
                                        <div class="menu">
                                            <ul>
                                                <li class="item cBtn">
                                                    <button class="btn">Chapter I</button>
                                                    <div class="smenu">
                                                        <a href="">Post</a>
                                                        <a href="">Picture</a>
                                                    </div>
                                                </li>

                                                <li class="item cBtn">
                                                    <button class="btn">chapterII</button>
                                                    <div class="smenu">
                                                        <a href="">New</a>
                                                        <a href="">Sent</a>
                                                        <a href="">Spam</a>
                                                    </div>
                                                </li>

                                                <li class="item cBtn">
                                                    <button class="btn">chapterIII</button>
                                                    <div class="smenu">
                                                        <a href="">Password</a>
                                                        <a href="">Language</a>
                                                    </div>
                                                </li>

                                                <li class="item">
                                                    <button href="#" id="collapseBtn" class="btn">
                                                        Show All
                                                    </button>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End  Dropdown content Section -->
                    </div>
                    <div id="announcement" class="tab-pane fade">
                        <h3>Announcement: no announcement posted yet</h3>
                        <p>
                            The instructor hasn?t added any announcements to this course
                            yet. Announcements are used to inform you of updates or
                            additions to the course.
                        </p>
                    </div>
                    <div id="overView" class="tab-pane fade">
                        <h3>OverView</h3>
                        <p>
                            Description: Lorem ipsum dolor sit amet consectetur adipisicing
                            elit. Praesentium commodi amet blanditiis laborum reiciendis quo
                            id dolor ea iste aperiam, quae saepe ipsa libero veritatis?
                            Perferendis quidem inventore vero porro.
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- dropdown content  section-->

        <div class="dropdown-content-lessons h100" id="dropdown-lesson-toggle">
            <div class="crossSymbol" style="    display: flex;
                 justify-content: flex-end;
                 transform: translateY(42px);">
                <a
                    href="javascript:void(0)"
                    class="closingbtn"
                    id="closingBtn"
                    onclick="closeNav()"
                    >&times;</a
                >
            </div>
            <div class="w-100 aos-init aos-animate">
                <div class="">
                    <div class="middle">
                        <h2>Course Contents</h2>
                        <p>Total length: 10 hours</p>
                        <div class="menu">
                            <ul>
                                <li class="item" id="chapterI">
                                    <a href="#chapterI" class="btn"> Chapter I </a>
                                    <div class="smenu">
                                        <a href="">Post</a>
                                        <a href="">Picture</a>
                                    </div>
                                </li>

                                <li class="item" id="chapterII">
                                    <a href="#chapterII" class="btn">chapterII</a>
                                    <div class="smenu">
                                        <a href="">New</a>
                                        <a href="">Sent</a>
                                        <a href="">Spam</a>
                                    </div>
                                </li>

                                <li class="item" id="chapterIII">
                                    <a href="#chapterIII" class="btn">chapterIII</a>
                                    <div class="smenu">
                                        <a href="">Password</a>
                                        <a href="">Language</a>
                                    </div>
                                </li>
                                <li class="item">
                                    <a href="#" class="btn">collapse All</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- End  Dropdown content Section -->
        <div class="dropdown-content-lessons">
            <button
                type="button"
                class="btn btn-default btn-arrow-left"
                id="slideInBtn"
                >
                Content
            </button>
        </div>
    </section>
    <!-- End lesson content section -->

<jsp:include page="WebFragment/footer.jsp"></jsp:include>