<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${platform.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div>
    <div class="d-flex justify-content-between">
<%--            <img class="col-4 img-fluid" src="${platform.image}"  alt="">--%>
        <div class="d-flex">
            <h1 class="mb-2">${platform.name}</h1>
            <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a class="btn btn-link text-warning" href="${UrlRoute.URL_PLATFORM_EDIT}/${platform.id}">
                        <i class="fa-solid fa-pen"></i>
                </a>
            </security:authorize>
        </div>
        <div class="ps-3 fs-3">Note moyenne ${jspUtils.getStringRating(reviewService.getRatingByObject(platform))}</div>
    </div>
    <c:set var="games" scope="request" value="${games}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/games_table.jsp"/>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>

