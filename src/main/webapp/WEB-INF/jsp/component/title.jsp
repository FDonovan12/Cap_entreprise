<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>

<div class="d-flex justify-content-between">
    <div class="d-flex justify-content-start">
        <h1>${title}</h1>
        <security:authorize access="hasRole('ROLE_MODERATOR')">
            <a class="btn btn-link text-success fs-2" href="${contentUrlNew}">
                <i class="fa-solid fa-circle-plus"></i>
            </a>
        </security:authorize>
    </div>
    <c:if test="${userLogged.leTrucQueKevinVeut}">
        <div class="sort-filter">
            <c:set var="label" scope="request" value="Nom"/>
            <c:set var="sortable" value="name"/>
            <%@ include file="../component/sortable.jsp" %>
        </div>
    </c:if>
</div>