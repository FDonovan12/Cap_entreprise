<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les jeux</h1>
<div class="col-12">
    <div class="d-flex">

        <span class="mt-auto mb-2">
            <a href="${currentUrl}" class="btn-link">
                Reset
            </a>
        </span>
    </div>
    <table class="table <c:if test="${!userLogged.veryEccentric}">table-striped-columns</c:if> table-dark table-hover">
        <thead>
            <tr>
<%--                <td ${rainbowStyleVery}>image</td>--%>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Nom"/>
                    <c:set var="sortable" value="name"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td> Jeux </td>
                <td ${rainbowStyleVery}>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${platforms}" var="platform">
                <tr>
<%--                    <td ${rainbowStyleVery}><img height="100" src="${platform.image}"></td>--%>
                    <td ${rainbowStyleVery}>${platform.name}</td>
                    <td ${rainbowStyleVery}>${platform.games.size()}</td>
                    <td ${rainbowStyleVery}>
                        <a class="btn btn-secondary" href="${UrlRoute.URL_PLATFORM}/${platform.id}">
                            <i class="fa-regular fa-eye"></i>
                        </a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a class="btn btn-success" href="${UrlRoute.URL_PLATFORM_EDIT}/${platform.id}">
                                <i class="fa-solid fa-pen"></i>
                            </a>
<%--                            <a class="btn btn-danger" href="${UrlRoute.URL_PLATFORM_DELETE}/${platform.id}">--%>
<%--                                <i class="fa-regular fa-trash-can"></i>--%>
<%--                            </a>--%>
<%--                            <a class="btn btn-success" href="${UrlRoute.URL_PLATFORM_UPLOAD}/${platform.id}">--%>
<%--                                <i class="fa-regular fa-image"></i>--%>
<%--                            </a>--%>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-between">
        <span>
            <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a href="${UrlRoute.URL_PLATFORM_NEW}">Nouvelle platforme</a>
            </security:authorize>
        </span>
    </div>
</div>
<%--${jspUtils.getPagination(re, currentUrl, currentQuery)}--%>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
