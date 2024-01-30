<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "Exam";
    }
    request.setAttribute("title", title);
%>

<html>
<head>
    <title>${title}</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <script type="text/javascript" src="${contextPath}/js/main.js"></script>
    <link href="../../css/main.css" rel="stylesheet">
</head>
<body <c:if test="${userLogged.eccentric}">${rainbowStyle}</c:if>>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="w-100 d-md-flex justify-content-between">
        <div class="ms-3">
            <security:authorize access="isAuthenticated()">
                <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_REVIEW}">
                    Avis
                </a>
                <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_GAME}">
                    Jeu
                </a>
            </security:authorize>
        </div>
        <c:set var="colorEccentric" scope="request" value="btn-secondary"/>
        <c:set var="colorVeryEccentric" scope="request" value="invisible"/>
        <c:if test="${userLogged.eccentric}">
            <c:set var="colorEccentric" scope="request" value="btn-success"/>
            <c:set var="colorVeryEccentric" scope="request" value="btn-secondary"/>
        </c:if>
        <c:if test="${userLogged.veryEccentric}">
            <c:set var="colorEccentric" scope="request" value="invisible"/>
            <c:set var="colorVeryEccentric" scope="request" value="btn-success"/>
        </c:if>
        <div class="ms-3">
            <security:authorize access="isAuthenticated()">
                <a class="navbar-brand me-3 btn ${colorEccentric}"
                   href="${UrlRoute.URL_USER_ECCENTRIC}/${currentPath.replaceAll("/","-_-")}">
                    Eccentric
                </a>
                <a class="navbar-brand me-3 btn ${colorVeryEccentric}"
                   href="${UrlRoute.URL_USER_VERY_ECCENTRIC}/${currentPath.replaceAll("/","-_-")}">
                    Very eccentric
                </a>
            </security:authorize>
        </div>
        <security:authorize access="isAnonymous()">
            <div class="ms-3">
                <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_LOGIN}">
                    Connection
                </a>
                <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_REGISTER}">
                    Inscription
                </a>
            </div>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <div class="ms-3">
                <a class="navbar-brand btn btn-secondary disabled" href="${UrlRoute.URL_USER}/${userLogged.id}">${userLogged.nickname}</a>
                <form class="navbar-brand m-0 p-2 btn btn-danger" method="POST" action="${UrlRoute.URL_LOGOUT}"
                      autocomplete="off">
                    <button type="submit" tabindex="3" class="bg-danger btn m-0 p-0">Deconnection</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </security:authorize>
    </div>

</nav>
<security:authorize access="isAuthenticated()">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="w-100 d-md-flex justify-content-start ms-3">
        <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_GENRE}">
            Genre
        </a>
        <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_PLATFORM}">
            Platforme
        </a>
        <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_CLASSIFICATION}">
            Classification
        </a>
        <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_BUSINESSMODEL}">
            Modele economique
        </a>
        <a class="navbar-brand btn btn-secondary" href="${UrlRoute.URL_PUBLISHER}">
            Editeur
        </a>
    </div>
</nav>
</security:authorize>

<div class="container-fluid p-5">