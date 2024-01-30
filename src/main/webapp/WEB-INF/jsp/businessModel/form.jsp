<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="formulaire de modele economique"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Formulaire de modele economique</h1>
<f:form modelAttribute="businessModel" method="post" action="${currentUrl}" cssClass="p-1 p-md-5">
    <div class="mb-3 row">
        <f:label path="name" class="col-sm-2 col-form-label">Nom</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="name"/>
            <f:errors path="name" cssClass="invalid-feedback"/>
        </div>
    </div>
    <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
    <f:button class="btn btn-lg btn-danger" type="reset">Reset</f:button>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
