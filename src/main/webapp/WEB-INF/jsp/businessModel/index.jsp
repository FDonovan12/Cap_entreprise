<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="d-flex justify-content-start">
    <h1>Modéle economique</h1>
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a class="btn btn-link text-success fs-2" href="${UrlRoute.URL_BUSINESSMODEL_NEW}">
            <i class="fa-solid fa-circle-plus"></i>
        </a>
    </security:authorize>
</div>
<div class="col-12">
    ${jspUtils.getPagination(businessModels, currentUrl, currentQuery)}
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
                <td width="200" ${rainbowStyleVery}>
                    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reset-filter.jsp"/>
                </td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${businessModels.content}" var="businessModel">
                <tr>
<%--                    <td ${rainbowStyleVery}><img height="100" src="${businessModel.image}"></td>--%>
                    <td ${rainbowStyleVery}>${businessModel.name}</td>
                    <td ${rainbowStyleVery}>${businessModel.games.size()}</td>
                    <td ${rainbowStyleVery}>${jspUtils.getStringRating(reviewService.getRatingByObject(businessModel))}</td>
                    <td width="200" ${rainbowStyleVery}>
                        <a class="btn btn-secondary" href="${UrlRoute.URL_BUSINESSMODEL}/${businessModel.id}">
                            <i class="fa-regular fa-eye"></i>
                        </a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a class="btn btn-success" href="${UrlRoute.URL_BUSINESSMODEL_EDIT}/${businessModel.id}">
                                <i class="fa-solid fa-pen"></i>
                            </a>
<%--                            <a class="btn btn-danger" href="${UrlRoute.URL_BUSINESSMODEL_DELETE}/${businessModel.id}">--%>
<%--                                <i class="fa-regular fa-trash-can"></i>--%>
<%--                            </a>--%>
<%--                            <a class="btn btn-success" href="${UrlRoute.URL_BUSINESSMODEL_UPLOAD}/${businessModel.id}">--%>
<%--                                <i class="fa-regular fa-image"></i>--%>
<%--                            </a>--%>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%--${jspUtils.getPagination(re, currentUrl, currentQuery)}--%>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
