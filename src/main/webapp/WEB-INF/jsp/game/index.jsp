<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les jeux</h1>
<div class="col-12">
    <div class="d-flex">

<%--        <c:set var="label" scope="request" value="Note"/>--%>
<%--        <c:set var="sortable" value="rating"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>

<%--        <c:set var="label" scope="request" value="Jeu"/>--%>
<%--        <c:set var="sortable" value="game.name"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>

<%--        <c:set var="label" scope="request" value="Joueur"/>--%>
<%--        <c:set var="sortable" value="gamer.nickname"/>--%>
<%--        <%@ include file="../component/sortable.jsp" %>--%>

        <span class="mt-auto mb-2">
            <a href="${currentUrl}" class="btn-link">
                Reset
            </a>
        </span>
    </div>
    <table class="table <c:if test="${!userLogged.veryEccentric}">table-striped-columns</c:if> table-dark table-hover">
        <thead>
            <tr>
                <td ${rainbowStyleVery}>image</td>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Nom"/>
                    <c:set var="sortable" value="name"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td ${rainbowStyleVery}>
                    <c:set var="label" scope="request" value="Editeur"/>
                    <c:set var="sortable" value="publisher.name"/>
                    <%@ include file="../component/sortable.jsp" %>
                </td>
                <td width="130" ${rainbowStyleVery}>Note moyenne</td>
                <td width="200" ${rainbowStyleVery}>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${games.content}" var="game">
                <tr>
                    <td ${rainbowStyleVery}><img height="100" src="${game.image}"></td>
                    <td ${rainbowStyleVery}>${game.name}</td>
                    <td ${rainbowStyleVery}>${game.publisher.name}</td>
                    <td ${rainbowStyleVery}>${jspUtils.getStringRating(reviewService.getRatingByGame(game))}</td>
                    <td ${rainbowStyleVery}>
                        <a class="btn btn-secondary" href="${UrlRoute.URL_GAME}/${game.id}">
                            <i class="fa-regular fa-eye"></i>
                        </a>
                        <security:authorize access="hasRole('ROLE_MODERATOR')">
                            <a class="btn btn-success" href="${UrlRoute.URL_GAME_EDIT}/${game.id}">
                                <i class="fa-solid fa-pen"></i>
                            </a>
                            <a class="btn btn-danger" href="${UrlRoute.URL_GAME_DELETE}/${game.id}">
                                <i class="fa-regular fa-trash-can"></i>
                            </a>
                            <a class="btn btn-success" href="${UrlRoute.URL_GAME_UPLOAD}/${game.id}">
                                <i class="fa-regular fa-image"></i>
                            </a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-between">
        <span>
            <security:authorize access="hasRole('ROLE_MODERATOR')">
                <a href="${UrlRoute.URL_GAME_NEW}">Nouveau jeu</a>
            </security:authorize>
        </span>

        <span>Total page ${games.totalPages}</span>
        <span></span>
    </div>
</div>
${jspUtils.getPagination(games, currentUrl, currentQuery)}
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
