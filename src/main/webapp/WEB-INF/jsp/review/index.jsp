<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="reviews"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="d-flex justify-content-start">
    <h1>Avis</h1>
    <a class="btn btn-link text-success fs-2" href="${UrlRoute.URL_REVIEW_NEW}"><i class="fa-solid fa-circle-plus"></i></a>
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a href="${UrlRoute.URL_EXPORT}" class="btn btn-link">
            <i class="fa-solid fa-file-excel me-1"></i>
            Télécharger export Excel
        </a>
    </security:authorize>
</div>
<div class="col-12">
<%--    <div class="d-flex justify-content-between">--%>
<%--        <c:set var="label" scope="request" value="Date"/>--%>
<%--        <c:set var="sortable" value="createdAt"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>
<%--&lt;%&ndash;        <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/sortable.jsp"/>&ndash;%&gt;--%>

<%--        <c:set var="label" scope="request" value="Note"/>--%>
<%--        <c:set var="sortable" value="rating"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>

<%--        <c:set var="label" scope="request" value="Jeu"/>--%>
<%--        <c:set var="sortable" value="game.name"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>

<%--        <c:set var="label" scope="request" value="Joueur"/>--%>
<%--        <c:set var="sortable" value="gamer.nickname"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>
<%--    </div>--%>
    ${jspUtils.getPagination(pageReviews, currentUrl, currentQuery)}
    <table class="table table-dark table-hover <c:if test="${!userLogged.veryEccentric}">table-striped-columns</c:if>">
        <thead>
            <tr>
                <td width="200" ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Date de creation"/>
                    <c:set var="sortable" value="createdAt"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Jeu"/>
                    <c:set var="sortable" value="game.name"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Joueur"/>
                    <c:set var="sortable" value="gamer.nickname"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Statut"/>
                    <c:set var="sortable" value="moderator"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td width="50" ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Note"/>
                    <c:set var="sortable" value="rating"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td width="200" ${rainbowStyleVery}>
                    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reset-filter.jsp"/>
                </td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageReviews.content}" var="review">
                    <tr>
                        <td ${rainbowStyleVery}>${dateUtils.getDateFormat(review.createdAt, "dd MMMM yyyy à hh:mm")}</td>
                        <td ${rainbowStyleVery}>${review.game.name}</td>
                        <td ${rainbowStyleVery}>${review.gamer.nickname}</td>
                        <td ${rainbowStyleVery}>
                            <c:choose>
                                <c:when test="${review.moderator == null}">
                                    A moderer
                                </c:when>
                                <c:otherwise>
                                    Moderer par ${review.moderator.nickname}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td ${rainbowStyleVery}>${jspUtils.getStringRating(review.rating)}</td>
                        <td ${rainbowStyleVery}>
                            <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW}/${review.id}">
                                <i class="fa-regular fa-eye"></i>
                            </a>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <c:if test="${review.moderator == null}">
                                    <a class="btn btn-success" href="${UrlRoute.URL_REVIEW_VALIDATE}/${review.id}">
                                        <i class="fa-regular fa-circle-check"></i>
                                    </a>
                                    <a class="btn btn-danger" href="${UrlRoute.URL_REVIEW_DELETE}/${review.id}">
                                        <i class="fa-regular fa-trash-can"></i>
                                    </a>
                                </c:if>
                            </security:authorize>
                        </td>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
