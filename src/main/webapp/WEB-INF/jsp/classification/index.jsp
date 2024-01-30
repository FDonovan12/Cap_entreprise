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
                <td width="100" ${rainbowStyleVery}> Jeux </td>
                <td width="100" ${rainbowStyleVery}> Rating </td>
                <td width="200" ${rainbowStyleVery}>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${classifications}" var="classification">
                <tr>
<%--                    <td ${rainbowStyleVery}><img height="100" src="${classification.image}"></td>--%>
                    <td ${rainbowStyleVery}>${classification.name}</td>
                    <td ${rainbowStyleVery}>${classification.games.size()}</td>
                    <td ${rainbowStyleVery}>${jspUtils.getStringRating(reviewService.getRatingByObject(classification))}</td>
                    <td width="200" ${rainbowStyleVery}>
                        <a class="btn btn-secondary" href="${UrlRoute.URL_CLASSIFICATION}/${classification.id}">
                            <i class="fa-regular fa-eye"></i>
                        </a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a class="btn btn-success" href="${UrlRoute.URL_CLASSIFICATION_EDIT}/${classification.id}">
                                <i class="fa-solid fa-pen"></i>
                            </a>
<%--                            <a class="btn btn-danger" href="${UrlRoute.URL_CLASSIFICATION_DELETE}/${classification.id}">--%>
<%--                                <i class="fa-regular fa-trash-can"></i>--%>
<%--                            </a>--%>
<%--                            <a class="btn btn-success" href="${UrlRoute.URL_CLASSIFICATION_UPLOAD}/${classification.id}">--%>
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
                <a href="${UrlRoute.URL_CLASSIFICATION_NEW}">Nouvelle classification</a>
            </security:authorize>
        </span>
    </div>
</div>
<%--${jspUtils.getPagination(re, currentUrl, currentQuery)}--%>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>