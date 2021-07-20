<%-- 
    Document   : SubjectDetails
    Created on : Jun 6, 2021, 3:09:45 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Subject</title>
        
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" href="css/navTabs.css"/>
    </head>
    <body>        
        <jsp:include page="web_fragments/OtherNavbar.jsp"/>
        
        <div class="container col-10">
            <div class="container text-center title mt-3" style="font-weight: bolder; font-size: 2rem">
                NEW SUBJECT
            </div>
            
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#overview" role="tab" aria-controls="overview" aria-selected="true">Overview</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#price-package" role="tab" aria-controls="price-package" aria-selected="false">Price Package</a>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">            
                <div class="tab-pane fade show active container" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                    <form action="AddNewSubject" method="POST">
                        <div class="row mb-3">
                            <div class="col-8">
                                <p class="mb-1" style="font-weight: bold;">Subject Title:</p>
                                <div class="col-10">
                                    <input type="text" class="form-control" name="txtSubjectTitle" value="${param.txtSubjectTitle}" required/>
                                </div>


                                <p class="mb-1" style="font-weight: bold;">Category:</p>
                                
                                <c:set var="categoryList" value="${requestScope.CATEGORY_LIST}"/>
                                <div class="col-10">
                                    <select class="form-select" name="txtCategoryID" required>
                                        <option value="" style="font-weight: bold;">Select a category</option>                                        
                                        <c:forEach var="dto" items="${categoryList}" varStatus="counter">
                                            <c:set var="currCategoryID">${dto.categoryID}</c:set>
                                            
                                            <c:if test="${param.txtCategoryID == currCategoryID}">
                                                <option value="${dto.categoryID}" selected>
                                                    ${dto.categoryName}
                                                </option>
                                            </c:if>
                                                
                                            <c:if test="${param.txtCategoryID != currCategoryID}">
                                                <option value="${dto.categoryID}">
                                                    ${dto.categoryName}
                                                </option>
                                            </c:if>
                                        </c:forEach>                                        
                                    </select>
                                </div>
                                
                                <p class="mb-1" style="font-weight: bold;">Owner:</p>
                                
                                <c:set var="expertList" value="${requestScope.EXPERT_LIST}"/>
                                <div class="col-10">
                                    <select class="form-select" name="txtOwnerID" required>
                                        <option value="" style="font-weight: bold;">Select an owner</option>
                                        <c:forEach var="dto" items="${expertList}" varStatus="counter">
                                            <c:if test="${param.txtOwnerID == dto.userID}">
                                                <option value="${dto.userID}" selected>
                                                    ${dto.userID} : ${dto.fullName}
                                                </option>
                                            </c:if>
                                            <c:if test="${param.txtOwnerID != dto.userID}">
                                                <option value="${dto.userID}">
                                                    ${dto.userID} : ${dto.fullName}
                                                </option>
                                            </c:if>
                                        </c:forEach>                                        
                                    </select>
                                </div>

                                <p class="mb-1" style="font-weight: bold;">Thumbnail Link:</p>
                                <div class="col-10 mb-3" style="display: inline-block; margin-right: 2%">
                                    <input type="text" id="thumbnail-link" class="form-control" name="txtThumbnailLink" value="${param.txtThumbnailLink}" required/>
                                </div>
                                <button type="button" class="btn btn-primary" onclick="loadImageFromLink()">Preview</button></br>   

                                <div class="col-10 mb-1">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="featured-checkbox" disabled>
                                        <label class="form-check-label" for="featured-checkbox">
                                            Featured Subject.
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <img id="thumbnail-preview" class="container-fluid col-4 p-0 Border" src=""/>
                        </div>

                        <div>  
                            <p class="mb-1" style="font-weight: bold;">Tag Line:</p>
                            <div>
                                <input type="text" class="form-control" name="txtTagLine" value="${param.txtTagLine}" required/>
                            </div>
                            
                            <div>
                                <p class="mb-1" style="font-weight: bold;">Brief Info:</p>
                                <textarea class="form-control" placeholder="Write something to briefly describe the subject." name="txtBriefInfo" required>${param.txtBriefInfo}</textarea>
                            </div>

                            <div>
                                <p class="mb-1" style="font-weight: bold;">Description:</p>
                                <textarea class="form-control" placeholder="Write something to describe the subject." name="txtDescription" style="min-height: 150px" required>${param.txtDescription}</textarea>
                            </div>
                        </div>   
                    
                        <div class="d-flex justify-content-end mt-4">
                            <button type="submit" class="btn btn-success mx-2">Save</button>            
                            <button type="button" class="btn btn-secondary mx-2">
                                <a href="ShowSubjects" style="text-decoration: none; color: #fff;">Back</a>
                            </button>
                        </div><br/>
                    </form>
                </div>

                <div class="tab-pane fade container" id="price-package" role="tabpanel" aria-labelledby="price-package-tab">
                    <p class="mb-1" style="font-weight: bold;">Default Packages:</p>
                    <table id="packages-table" class="table table-hover table-bordered text-center">
                        <thead>
                            <tr class="table-dark">
                                <th>#</th>
                                <th>Package</th>
                                <th>Duration</th>
                                <th>List Price</th>                    
                                <th>Sale Price</th>
                                <th>Description</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Standard Package (3 months)</td>
                                <td>3</td>
                                <td>1800</td>
                                <td>3600</td>
                                <td>This is a 3-month standard package.</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Standard Package (6 months)</td>
                                <td>6</td>
                                <td>3600</td>
                                <td>3600</td>
                                <td>This is a 6-month standard package.</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>Standard Package (unlimited)</td>
                                <td>0</td>
                                <td>7200</td>
                                <td>7200</td>
                                <td>This is an unlimited standard package.</td>
                            </tr>
                        </tbody>
                    </table>
                </div>            
            </div>
        </div>
        
        <script src="js/loadThumbnail.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
