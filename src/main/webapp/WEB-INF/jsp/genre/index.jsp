<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Genre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="d-flex justify-content-start">
    <h1>Genre</h1>
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a class="btn btn-link text-success fs-2" href="${UrlRoute.URL_GENRE_NEW}">
            <i class="fa-solid fa-circle-plus"></i>
        </a>
    </security:authorize>
</div>

<c:set var="contents" scope="request" value="${genres}"/>
<c:set var="contentUrl" scope="request" value="${UrlRoute.URL_GENRE}"/>
<c:set var="contentUrlEdit" scope="request" value="${UrlRoute.URL_GENRE_EDIT}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/table.jsp"/>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
