<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../tag.jsp" %>

<div class="col-8 mx-auto mb-3 row bg-dark bg-opacity-75 rounded p-3">
    <h1>${title}</h1>
    <f:label path="name" class="col-sm-2 col-form-label">Nom</f:label>
    <div class="col-sm-10 mb-3">
        <f:input type="text" cssClass="form-control" path="name"/>
        <f:errors path="name" cssClass="invalid-feedback"/>
    </div>
    <div>
        <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
        <f:button class="btn btn-lg btn-danger" type="reset">Reset</f:button>
    </div>
</div>