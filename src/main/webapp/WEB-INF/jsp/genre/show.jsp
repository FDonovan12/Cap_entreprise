<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${genre.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div>
    <div class="d-flex">
        <h1 class="mb-2">${genre.name}</h1>
        <div class="ps-3 fs-3">${jspUtils.getStringRating(reviewService.getRatingByObject(genre))}</div>
<%--        <img class="img-fluid" src="${genre.image}"  alt="">--%>
    </div>
    <div>
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <a class="btn btn-success" href="${UrlRoute.URL_GENRE_EDIT}/${genre.id}">
                <i class="fa-solid fa-pen"></i>
            </a>
<%--            <a class="btn btn-danger" >--%>
<%--                <i class="fa-regular fa-trash-can"></i>--%>
<%--            </a>--%>
        </security:authorize>
    </div>
    <c:set var="games" scope="request" value="${genre.games}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/games_table.jsp"/>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>

