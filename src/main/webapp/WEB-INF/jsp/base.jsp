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
    <link href="../../css/main.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="row w-100">
        <div class="d-flex justify-content-between">
            <div>
                <a class="navbar-brand ms-3 btn btn-secondary" href="${UrlRoute.URL_REVIEW}">
                    Avis
                </a>
                <a class="navbar-brand ms-3 btn btn-secondary" href="${UrlRoute.URL_GAME}">
                    Jeu
                </a>
            </div>
            <security:authorize access="isAnonymous()">
                <div>
                    <a class="navbar-brand ms-3 btn btn-secondary" href="${UrlRoute.URL_LOGIN}">
                        Login
                    </a>
                    <a class="navbar-brand ms-3 btn btn-secondary" href="${UrlRoute.URL_REGISTER}">
                        Register
                    </a>
                </div>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <div>
                    <a class="navbar-brand ms-3 btn btn-secondary" href="${UrlRoute.URL_USER}/<security:authentication property="name"/>">
                        <security:authentication property="name"/>
                    </a>
                    <form class="navbar-brand m-0 p-2 ms-3 btn btn-danger" method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                        <button type="submit" tabindex="3" class="bg-danger btn m-0 p-0">Logout</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </security:authorize>
        </div>
    </div>

</nav>
<div class="container-fluid p-5" style="${jspUtils.getRainbow(500)}">