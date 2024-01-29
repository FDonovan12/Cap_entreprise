<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${platform.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div>
    <div class="d-flex">
        <h1 class="mb-2">${platform.name}</h1>
        <img class="img-fluid" src="${platform.image}"  alt="">
    </div>
    <div>
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <a class="btn btn-success" href="${UrlRoute.URL_PLATFORM_EDIT}/${platform.id}">
                <i class="fa-solid fa-pen"></i>
            </a>
            <a class="btn btn-danger" >
                <i class="fa-regular fa-trash-can"></i>
            </a>
        </security:authorize>
        <h2 class="mb-3 mt-5">Description</h2>
        <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW_NEW}/${platform.id}">Laisser un avis sur ce jeu</a>
    </div>
    <c:set var="games" scope="request" value="${platform.games}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/games_table.jsp"/>
<%--    <%@ include file="../component/games_table.jsp" %>--%>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>

