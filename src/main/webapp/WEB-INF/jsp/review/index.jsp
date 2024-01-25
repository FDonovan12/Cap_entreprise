<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="review_index"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les jeux</h1>
<div class="col-12">
    <table class="table table-striped-columns table-dark table-hover">
        <thead>
            <tr>
                <td>Date de creation</td>
                <td>Jeu</td>
                <td>Joueur</td>
                <td>Note</td>
                <td>Statut</td>
                <td>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageReviews.content}" var="review">
                    <tr>
<%--                        <td>${dateUtils.getDateFormat(review.createdAt, "dd MMMM yyyy à hh:mm")}</td>--%>
                        <td>${jspUtils.getFa(dateUtils.getDateFormat(review.createdAt, "dd MMMM yyyy à hh:mm"),"")}</td>
                            <%--                    <td>${review.createdAt}</td>--%>
<%--                        <td>${review.game.name}</td>--%>
                        <td>${jspUtils.getFa(review.game.name,"")}</td>
                        <td>${review.gamer.nickname}</td>
                        <td>${review.rating}</td>
                        <td>
                            <c:choose>
                                <c:when test="${review.moderator == null}">
                                    A moderer
                                </c:when>
                                <c:otherwise>
                                    Moderer par ${review.moderator.nickname}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
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
    <a href="${UrlRoute.URL_REVIEW_NEW}">New</a>
    ${jspUtils.getPagination(pageReviews, "/review")}

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
