<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<div class="container">
    <f:form method="POST" modelAttribute="userForm" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>

        <div class="col-sm-10 mb-3">
            <f:input type="text" path="nickname" class="form-control" placeholder="nickname" autofocus="true"/>
            <f:errors path="nickname" cssClass="invalid-feedback"/>
        </div>
        <div class="col-sm-10 mb-3">
            <f:input type="text" path="email" class="form-control" placeholder="email" autofocus="true"/>
            <f:errors path="email" cssClass="invalid-feedback"/>
        </div>
        <div class="col-sm-10 mb-3">
            <f:input type="password" path="password" class="form-control" placeholder="Password"/>
            <f:errors path="password" cssClass="invalid-feedback"/>
        </div>
        <div class="col-sm-10 mb-3">
            <f:input type="date" path="birthAt" class="form-control" placeholder="birthAt"/>
            <f:errors path="birthAt" cssClass="invalid-feedback"/>
        </div>
        <f:button class="btn btn-secondary" type="reset">Reset</f:button>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </f:form>
</div>

<%@ include file="../footer.jsp" %>