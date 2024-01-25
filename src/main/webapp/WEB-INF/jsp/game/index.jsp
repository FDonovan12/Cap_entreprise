<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="game_index"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les jeux</h1>
<div class="col-12">
    <table class="table table-striped-columns table-dark table-hover">
        <thead>
            <tr>
                <td>Nom</td>
                <td>Editeur</td>
<%--                <td>genre</td>--%>
<%--                <td>classification</td>--%>
<%--                <td>businessModel</td>--%>
<%--                <td>description</td>--%>
                <td>image</td>
                <td>Operation</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${games}" var="game">
                <tr>
                    <td>${game.name}</td>
                    <td>${game.publisher.name}</td>
<%--                    <td>${game.genre.name}</td>--%>
<%--                    <td>${game.classification.name}</td>--%>
<%--                    <td>${game.businessModel.name}</td>--%>
<%--                    <td>${game.description}</td>--%>
                    <td>${game.image}</td>
                    <td>
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
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <security:authorize access="hasRole('ROLE_MODERATOR')">
        <a href="${UrlRoute.URL_GAME_NEW}">New</a>
    </security:authorize>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
