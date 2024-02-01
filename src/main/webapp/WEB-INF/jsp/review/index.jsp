<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Avis"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<div class="d-flex justify-content-between">
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

    <div class="sort-filter">
        <div class="d-flex justify-content-between">
            <c:set var="label" scope="request" value="Date"/>
            <c:set var="sortable" value="createdAt"/>
            <%@ include file="../component/sortable.jsp" %>
            <%--        <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/sortable.jsp"/>--%>

            <c:set var="label" scope="request" value="Note"/>
            <c:set var="sortable" value="rating"/>
            <%@ include file="../component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Jeu"/>
            <c:set var="sortable" value="game.name"/>
            <%@ include file="../component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Joueur"/>
            <c:set var="sortable" value="gamer.nickname"/>
            <%@ include file="../component/sortable.jsp" %>

            <c:set var="label" scope="request" value="Modérer"/>
            <c:set var="sortable" value="moderator"/>
            <%@ include file="../component/sortable.jsp" %>

            <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reset-filter.jsp"/>
        </div>
    </div>
</div>

<c:if test="${!userLogged.leTrucQueKevinVeut}">
    <div class="col-12">
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
    <%--                    <c:set var="label" scope="request" value="Statut"/>--%>
    <%--                    <c:set var="sortable" value="moderator"/>--%>
    <%--                    <%@ include file="../component/sortable.jsp" %>--%>
                        <select class="form-select sortable-select">
                            <option value="all" data-filter-url="${currentUrl}">
                                Tous les commentaires
                            </option>
                            <option value="sort=moderator,desc"
                                    data-filter-url="${jspUtils.getUrlFrom(currentUrl, currentQuery, "sort=moderator,desc")}"
                            >
                                Modérés
                            </option>
                            <option value="sort=moderator,asc"
                                    data-filter-url="${jspUtils.getUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"
                            >
                                À modérer
                            </option>
                        </select>
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
                                <a class="btn btn-outline-secondary border-0 text-white" href="${UrlRoute.URL_REVIEW}/${review.id}">
                                    <i class="fa-regular fa-eye"></i>
                                </a>
                                <security:authorize access="hasRole('ROLE_MODERATOR')">
                                    <c:if test="${review.moderator == null}">
                                        <a class="btn btn-outline-secondary border-0 text-success" href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/1">
                                            <i class="fa-regular fa-circle-check"></i>
                                        </a>
                                        <a class="btn btn-outline-secondary border-0 text-danger" href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/0">
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
</c:if>

<c:if test="${userLogged.leTrucQueKevinVeut}">
    <c:set var="reviews" scope="request" value="${pageReviews}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reviews_table.jsp"/>
</c:if>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
