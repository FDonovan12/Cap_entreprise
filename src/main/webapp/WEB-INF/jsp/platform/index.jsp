<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Platformes"/>
<c:set var="contents" scope="request" value="${platforms}"/>
<c:set var="contentUrl" scope="request" value="${UrlRoute.URL_PLATFORM}"/>
<c:set var="contentUrlEdit" scope="request" value="${UrlRoute.URL_PLATFORM_EDIT}"/>
<c:set var="contentUrlNew" scope="request" value="${UrlRoute.URL_PLATFORM_NEW}"/>

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
