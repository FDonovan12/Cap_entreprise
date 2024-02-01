<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Inscription"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container col-8 mx-auto bg-dark bg-opacity-75 rounded p-3">
    <f:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Inscription</h2>

        <div class="mb-3 row">
            <f:label path="nickname" class="col-sm-2 col-form-label">Pseudo</f:label>
            <div class="col-sm-10 mb-3">
                <f:input type="text" path="nickname" class="form-control" placeholder="nickname" autofocus="true"/>
                <f:errors path="nickname" cssClass="invalid-feedback"/>
            </div>

            <f:label path="email" class="col-sm-2 col-form-label">Email</f:label>
            <div class="col-sm-10 mb-3">
                <f:input type="text" path="email" class="form-control" placeholder="email" autofocus="true"/>
                <f:errors path="email" cssClass="invalid-feedback"/>
            </div>

            <f:label path="password" class="col-sm-2 col-form-label">Mot de passe</f:label>
            <div class="col-sm-10 mb-3">
                <f:input type="password" path="password" class="form-control" placeholder="Password"/>
                <f:errors path="password" cssClass="invalid-feedback"/>
            </div>

            <f:label path="birthAt" class="col-sm-2 col-form-label">Date de naissance</f:label>
            <div class="col-sm-10 mb-3">
                <f:input type="date" path="birthAt" class="form-control" placeholder="birthAt"/>
                <f:errors path="birthAt" cssClass="invalid-feedback"/>
            </div>
        </div>
        <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
        <button class="btn btn-lg btn-danger" type="reset">Reset</button>
    </f:form>
</div>

<%@ include file="../footer.jsp" %>