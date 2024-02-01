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
    <script type="text/javascript" src="${contextPath}/js/page/multiple-select.js"></script>
    <script type="text/javascript" src="${contextPath}/js/lib/bootstrap/bootstrap.bundle.js"></script>
    <link href="../../css/main.css" rel="stylesheet">
</head>
<body <c:if test="${userLogged.eccentric}">${rainbowStyle}</c:if>>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="w-100 d-md-flex justify-content-between">
        <div class="ms-3">
            <security:authorize access="isAuthenticated()">
                <div class="d-flex justify-content-start">
                    <a class="navbar-brand btn btn-outline-secondary border-0" href="${UrlRoute.URL_REVIEW}">
                        Avis
                    </a>
                    <a class="navbar-brand btn btn-outline-secondary border-0" href="${UrlRoute.URL_GAME}">
                        Jeux
                    </a>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"aria-expanded="false">
                                Menu
                            </a>
                            <ul class="dropdown-menu dropdown-menu-dark">
                                <li><a class="dropdown-item" href="${UrlRoute.URL_GENRE}">Genres</a></li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_CLASSIFICATION}">Classifications</a>
                                </li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_PUBLISHER}">Éditeurs</a></li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_BUSINESSMODEL}">Modèles économique</a>
                                </li>
                                <li><a class="dropdown-item" href="${UrlRoute.URL_PLATFORM}">Platformes</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </security:authorize>
        </div>
        <c:set var="colorEccentric" scope="request" value=""/>
        <c:set var="colorVeryEccentric" scope="request" value=""/>
        <c:set var="colorLeTrucQueKevinVeut" scope="request" value=""/>
        <c:if test="${userLogged.eccentric}">
            <c:set var="colorEccentric" scope="request" value="bg-success"/>
        </c:if>
        <c:if test="${userLogged.veryEccentric}">
            <c:set var="colorVeryEccentric" scope="request" value="bg-success"/>
        </c:if>
        <c:if test="${userLogged.leTrucQueKevinVeut}">
            <c:set var="colorLeTrucQueKevinVeut" scope="request" value="bg-success"/>
        </c:if>
        <security:authorize access="isAuthenticated()">
            <div class="ms-3">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Style
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <c:if test="${!userLogged.veryEccentric && !userLogged.leTrucQueKevinVeut}">
                                <li><a class="dropdown-item ${colorEccentric}" href="${UrlRoute.URL_USER_STYLE}/1/${currentPath.replaceAll("/","-_-")}">
                                    Excentrique</a>
                                </li>
                            </c:if>
                            <c:if test="${userLogged.eccentric  && !userLogged.leTrucQueKevinVeut}">
                                <li><a class="dropdown-item ${colorVeryEccentric}" href="${UrlRoute.URL_USER_STYLE}/2/${currentPath.replaceAll("/","-_-")}">
                                    Trés excentrique</a>
                                </li>
                            </c:if>
                            <li><a class="dropdown-item ${colorLeTrucQueKevinVeut}" href="${UrlRoute.URL_USER_STYLE}/3/${currentPath.replaceAll("/","-_-")}">
                                Le truc que Kevin veux</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <div class="ms-3">
                <a class="navbar-brand btn btn-outline-secondary border-0" href="${UrlRoute.URL_LOGIN}">
                    Connexion
                </a>
                <a class="navbar-brand btn btn-outline-secondary border-0" href="${UrlRoute.URL_REGISTER}">
                    Inscription
                </a>
            </div>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <div class="ms-3">
                <a class="navbar-brand btn btn-outline-secondary border-0" href="${UrlRoute.URL_USER}/${userLogged.id}">${userLogged.nickname}</a>
                <form class="m-0 p-0 btn" method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                    <button type="submit" tabindex="3" class="navbar-brand btn btn-outline-danger border-0">Déconnexion</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </security:authorize>
    </div>

</nav>

<div class="container-fluid p-4 ">