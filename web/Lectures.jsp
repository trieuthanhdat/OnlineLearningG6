<jsp:include page="WebFragment/header.jsp"></jsp:include>
<jsp:include page="WebFragment/navbar.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Lesson content section -->
<section class="lesson-content">
    <div class="" id="video-section">
        <c:if test="${not empty CURRENT_LESSON.details.videoLink}">
            <div id="player" class="embed-responsive embed-responsive-21by9 z-depth-1-half">
                <iframe 
                    class="embed-responsive-item"
                    src="${CURRENT_LESSON.details.videoLink}"
                    allowfullscreen
                    ></iframe>
            </div>
            <input value="${CURRENT_LESSON.details.videoLink}" id="video-id" type="hidden" hidden/>

        </c:if>

        <div class="lesson-description">
            <h2>REVIEW ${CURRENT_LESSON.name}</h2>
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
                                            <c:forEach items="${TOPICS_LIST}" var="tpList">
                                                <li class="item cBtn">
                                                    <button class="btn">${tpList.name}</button>
                                                    <div class="smenu">
                                                        <c:forEach items="${LESSONS_LIST}" var="list">
                                                            <c:if test="${tpList.lessonID eq list.topicID}">
                                                                <a href="">${list.name}</a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </li>
                                            </c:forEach>


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
                class="closeBtn"
                id="closeBtn"
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
                            
                            <c:forEach items="${TOPICS_LIST}" var="tpList">
                                <li class="item" id="${tpList.name}">
                                    <a href="#${tpList.name}" class="btn">${tpList.name}</a>
                                    <div class="smenu">
                                        <c:forEach items="${LESSONS_LIST}" var="list">
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

<!-- Youtube Player API -->
<script>
    // 2. This code loads the IFrame Player API code asynchronously.
    var tag = document.createElement("script");
    var videoID = document.getElementById("video-id");

    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
        player = new YT.Player("player", {
            height: "720",
            width: "1280",
            videoId: videoID.value,
            playerVars: {
                playsinline: 1,
            },
            events: {
                onReady: onPlayerReady,
                onStateChange: onPlayerStateChange,
            },
        });
    }
    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        event.target.playVideo();
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    var done = false;
    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(stopVideo, 6000);
            done = true;
        }
    }
    function stopVideo() {
        player.stopVideo();
    }
</script>

<jsp:include page="WebFragment/footer.jsp"></jsp:include>