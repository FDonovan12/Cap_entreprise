<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${genre.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class=" bg-dark bg-opacity-75 rounded p-3">
    <div class="d-flex justify-content-between">
        <div class="d-flex">
            <h1 class="mb-2">${businessModel.name}</h1>
            <%--        <img class="img-fluid" src="${businessModel.image}"  alt="">--%>
            <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a class="btn btn-link text-warning" href="${UrlRoute.URL_BUSINESSMODEL_EDIT}/${businessModel.id}">
                    <i class="fa-solid fa-pen"></i>
                </a>
            </security:authorize>
        </div>
        <div class="ps-3 fs-3">Note moyenne ${jspUtils.getStringRating(reviewService.getRatingByObject(businessModel))}</div>
    </div>
    <c:set var="games" scope="request" value="${games}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/games_table.jsp"/>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>

