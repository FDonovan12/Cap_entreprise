<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Editeur"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="d-flex justify-content-start">
    <h1>Éditeur</h1>
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a class="btn btn-link text-success fs-2" href="${UrlRoute.URL_PUBLISHER_NEW}">
            <i class="fa-solid fa-circle-plus"></i>
        </a>
    </security:authorize>
</div>

<c:set var="contents" scope="request" value="${publishers}"/>
<c:set var="contentUrl" scope="request" value="${UrlRoute.URL_PUBLISHER}"/>
<c:set var="contentUrlEdit" scope="request" value="${UrlRoute.URL_PUBLISHER_EDIT}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/table.jsp"/>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
