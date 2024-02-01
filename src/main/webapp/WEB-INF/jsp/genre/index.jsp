<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Genres"/>
<c:set var="contents" scope="request" value="${genres}"/>
<c:set var="contentUrl" scope="request" value="${UrlRoute.URL_GENRE}"/>
<c:set var="contentUrlEdit" scope="request" value="${UrlRoute.URL_GENRE_EDIT}"/>
<c:set var="contentUrlNew" scope="request" value="${UrlRoute.URL_GENRE_NEW}"/>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class=" bg-dark bg-opacity-75 rounded p-3">
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/title.jsp"/>

<c:if test="${!userLogged.leTrucQueKevinVeut}">
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/table.jsp"/>
</c:if>

<c:if test="${userLogged.leTrucQueKevinVeut}">
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/horizontal_table.jsp"/>
</c:if>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
