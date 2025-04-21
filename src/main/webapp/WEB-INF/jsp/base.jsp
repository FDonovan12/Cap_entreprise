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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
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
                    <div class="p-0 m-0 btn dropdown">
                        <button class="navbar-brand btn btn-outline-secondary rounded-0 text-white dropbtn">Menu</button>
                        <div class="dropdown-content">
                            <a class="dropdown-item" href="${UrlRoute.URL_GENRE}">Genres</a>
                            <a class="dropdown-item" href="${UrlRoute.URL_CLASSIFICATION}">Classifications</a>
                            <a class="dropdown-item" href="${UrlRoute.URL_PUBLISHER}">Éditeurs</a>
                            <a class="dropdown-item" href="${UrlRoute.URL_BUSINESSMODEL}">Modèles économique</a>
                            <a class="dropdown-item" href="${UrlRoute.URL_PLATFORM}">Platformes</a>
                        </div>
                    </div>
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
                <div class="p-0 m-0 btn dropdown">
                    <button class="navbar-brand btn btn-outline-secondary rounded-0 text-white dropbtn">Style</button>
                    <div class="dropdown-content">
                        <c:if test="${!userLogged.veryEccentric && !userLogged.leTrucQueKevinVeut}">
                        <a class="dropdown-item ${colorEccentric}"
                            href="${UrlRoute.URL_USER_STYLE}/1/${currentPath.replaceAll("/","-_-")}">
                            Excentrique
                        </a>
                        </c:if>
                        <c:if test="${userLogged.eccentric  && !userLogged.leTrucQueKevinVeut}">
                        <a class="dropdown-item ${colorVeryEccentric}"
                            href="${UrlRoute.URL_USER_STYLE}/2/${currentPath.replaceAll("/","-_-")}">
                            Trés excentrique
                        </a>
                        </c:if>
                        <a class="dropdown-item ${colorLeTrucQueKevinVeut}"
                            href="${UrlRoute.URL_USER_STYLE}/3/${currentPath.replaceAll("/","-_-")}">
                            Le truc que Kevin veux
                        </a>
                    </div>
                </div>
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