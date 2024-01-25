<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les avis</h1>
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
            <c:forEach items="${reviews}" var="review">
                    <tr>
                        <td><fmt:formatDate value="${review.createdAt}" pattern="dd MMMMM yyyy - hh:mm" /></td>
                            <%--                    <td>${review.createdAt}</td>--%>
                        <td>${review.game.name}</td>
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
                            <%--                    <td>--%>
                            <%--                        <c:if test=""></c:if>--%>
                            <%--                    </td>--%>
                        <td>
                            <a class="btn btn-secondary" href="${UrlRoute.URL_REVIEW}/${review.id}">
                                Voir
                            </a>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <c:if test="${review.moderator == null}">
                                    <a class="btn btn-success" >Valider</a>
                                </c:if>
                                <a class="btn btn-danger" >Supprimer</a>
                            </security:authorize>
                        </td>
                    </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="${UrlRoute.URL_REVIEW_NEW}">New</a>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
