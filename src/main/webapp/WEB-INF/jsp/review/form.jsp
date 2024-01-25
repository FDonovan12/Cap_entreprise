<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="new_review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Donnez votre avis</h1>
<f:form modelAttribute="review" method="post" action="${action}" cssClass="p-5">
    <div class="mb-3 row">
        <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="description"/>
            <f:errors path="description" cssClass="invalid-feedback"/>
        </div>

        <f:label path="rating" class="col-sm-2 col-form-label">Note</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="number" cssClass="form-control" path="rating"/>
            <f:errors path="rating" cssClass="invalid-feedback"/>
        </div>

        <f:label path="image" class="col-sm-2 col-form-label">Image</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="image"/>
            <f:errors path="image" cssClass="invalid-feedback"/>
        </div>

        <f:label path="game" class="col-sm-2 col-form-label">Jeu</f:label>
        <div class="col-sm-10 mb-3">
            <f:select items="${games}" itemLabel="name" cssClass="form-select" path="game"/>
            <f:errors path="game" cssClass="invalid-feedback"/>
        </div>
    </div>
    <f:button class="btn btn-secondary" type="reset">Reset</f:button>
    <f:button class="btn btn-primary">Submit</f:button>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
