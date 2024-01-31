<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<form method="POST" action="${UrlRoute.URL_LOGIN}" class="col-8 mx-auto">
    <h2 class="form-heading">Connection</h2>
    <div class="form-group ${error != null ? 'has-error' : ''}">

        <div class="mb-3 row">
            <label path="username" class="col-sm-2 col-form-label">Pseudo</label>
            <div class="col-sm-10 mb-3">
                <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
            </div>

            <label path="password" class="col-sm-2 col-form-label">Mot de passe</label>
            <div class="col-sm-10 mb-3">
                <input name="password" type="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <p class="invalid-feedback">${error}</p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-success btn-block" type="submit">Log In</button>
    </div>
</form>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>