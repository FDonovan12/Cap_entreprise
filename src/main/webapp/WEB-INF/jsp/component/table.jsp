<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../tag.jsp" %>

<div class="col-12">
    ${jspUtils.getPagination(contents, currentUrl, currentQuery)}
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
            <td width="100" ${rainbowStyleVery}> Note Moyenne </td>
            <td width="200" ${rainbowStyleVery}>
                <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reset-filter.jsp"/>
            </td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contents.content}" var="content">
            <tr>
                    <%--                    <td ${rainbowStyleVery}><img height="100" src="${content.image}"></td>--%>
                <td ${rainbowStyleVery}>${content.name}</td>
                <td ${rainbowStyleVery}>${content.games.size()}</td>
                <td ${rainbowStyleVery}>${jspUtils.getStringRating(reviewService.getRatingByObject(content))}</td>
                <td width="200" ${rainbowStyleVery}>
                    <a class="btn btn-outline-secondary border-0 text-white" href="${contentUrl}/${content.id}">
                        <i class="fa-regular fa-eye"></i>
                    </a>
                    <security:authorize access="hasRole('ROLE_MODERATOR')">
                        <a class="btn btn-outline-warning border-0" href="${contentUrlEdit}/${content.id}">
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
</div>