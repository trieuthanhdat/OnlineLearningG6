<!-- ======= Pricing Section ======= -->
<div class="pricing">
    <div class="container" id="pricing" data-aos="fade-up">
        <div class="row" style="display: -ms-flexbox;
             display: flex;
             -ms-flex-wrap: wrap;
             flex-wrap: nowrap;
             margin-right: -15px;
             margin-left: -15px;
             flex-direction: row;
             align-content: stretch;
             justify-content: space-between;">
            <c:forEach items="${CURRENT_SUBJECT.packages}" var="packages">
                <div class="col-lg-3 col-md-6">
                    <div class="box">
                        <h3>${packages.name}</h3>
                        <c:if test="${packages.accessduration gt 0}" >
                            <c:set var="type" value="${packages.accessduration} month"></c:set>
                        </c:if>
                        <c:if test="${packages.accessduration eq 0}" >
                            <c:set var="type" value="unlimited"></c:set>
                                <span class="advanced">Advanced</span>
                        </c:if>

                        <h4>
                            <sup>$</sup>${packages.salePrice}

                            <span> / ${type}</span>
                        </h4>
                        <ul style="min-height: 200px">
                            <li class="na">${packages.listPrice}$</li>
                            <li>${packages.description}</li>
                        </ul>
                        <div class="btn-wrap">
                            <a href="#" class="btn-buy">Buy Now</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!-- End Pricing Section -->