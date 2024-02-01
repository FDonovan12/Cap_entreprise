<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="new_review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Donnez votre avis</h1>
<f:form modelAttribute="review" method="post" action="${currentUrl}" cssClass="p-1 p-md-5">
    <div class="col-8 mx-auto mb-3 row">
        <f:label path="description" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Description</f:label>
        <div class="col-9 col-lg-10 mb-3">
            <f:input type="text" cssClass="form-control" path="description"/>
            <f:errors path="description" cssClass="invalid-feedback"/>
        </div>

        <f:label path="game" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Jeu</f:label>
        <div class="col-9 col-lg-5 mb-3">
            <f:select items="${games}" itemLabel="name" cssClass="form-select" path="game"/>
            <f:errors path="game" cssClass="invalid-feedback"/>
        </div>

        <f:label path="rating" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Note</f:label>
        <div class="col-9 col-lg-3 mb-3">
            <f:input type="number" cssClass="form-control" path="rating"/>
            <f:errors path="rating" cssClass="invalid-feedback"/>
        </div>

        <div>
            <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
            <f:button class="btn btn-lg btn-danger" type="reset">Reset</f:button>
        </div>
    </div>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
