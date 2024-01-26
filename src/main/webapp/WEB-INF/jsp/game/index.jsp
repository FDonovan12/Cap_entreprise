<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Jeux"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les jeux</h1>
<div class="col-12">
    <div class="d-flex">
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

        <span class="mt-auto mb-2">
            <a href="${currentUrl}" class="btn-link">
                Reset
            </a>
        </span>
    </div>
    <table class="table <c:if test="${!userLogged.veryEccentric}">table-striped-columns</c:if> table-dark table-hover">
        <thead>
            <tr>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Nom</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Editeur</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>image</td>
                <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${games}" var="game">
                <tr>
                    <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${game.name}</td>
                    <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${game.publisher.name}</td>
                    <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>${game.image}</td>
                    <td <c:if test="${userLogged.veryEccentric}">style="${jspUtils.getRainbow(500)}"</c:if>>
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
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a href="${UrlRoute.URL_GAME_NEW}">Nouveau jeu</a>
    </security:authorize>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
