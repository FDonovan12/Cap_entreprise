<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="formulaire de jeu"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Formulaire de jeu</h1>
<f:form modelAttribute="game" method="post" action="${currentUrl}" cssClass="p-1 p-md-5">
    <div class="col-11 col-md-10 col-lg-9 mx-auto mb-3 row">
        <f:label path="name" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Nom</f:label>
        <div class="col-9 col-lg-5 mb-3">
            <f:input type="text" cssClass="form-control" path="name"/>
            <f:errors path="name" cssClass="invalid-feedback"/>
        </div>

        <f:label path="publishedAt" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Date de sortie</f:label>
        <div class="col-9 col-lg-3 mb-3">
            <f:input type="date" cssClass="form-control" path="publishedAt"/>
            <f:errors path="publishedAt" cssClass="invalid-feedback"/>
        </div>

        <f:label path="image" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Image</f:label>
        <div class="col-9 col-lg-10 mb-3">
            <f:input type="text" cssClass="form-control" path="image"/>
            <f:errors path="image" cssClass="invalid-feedback"/>
        </div>

        <f:label path="classification" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Classifcation</f:label>
        <div class="col-9 col-lg-4 mb-3">
            <f:select items="${classifications}" itemLabel="name" cssClass="form-select" path="classification"/>
            <f:errors path="classification" cssClass="invalid-feedback"/>
        </div>

        <f:label path="genre" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Genre</f:label>
        <div class="col-9 col-lg-4 mb-3">
            <f:select items="${genres}" itemLabel="name" cssClass="form-select" path="genre"/>
            <f:errors path="genre" cssClass="invalid-feedback"/>
        </div>

        <f:label path="businessModel" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Modèle économique</f:label>
        <div class="col-9 col-lg-4 mb-3">
            <f:select items="${businessModels}" itemLabel="name" cssClass="form-select" path="businessModel"/>
            <f:errors path="businessModel" cssClass="invalid-feedback"/>
        </div>

        <f:label path="publisher" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Éditeur</f:label>
        <div class="col-9 col-lg-4 mb-3">
            <f:select items="${publishers}" itemLabel="name" cssClass="form-select" path="publisher"/>
            <f:errors path="publisher" cssClass="invalid-feedback"/>
        </div>

        <f:label path="platforms" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Platform</f:label>
        <div class="col-9 col-lg-10 mb-3">
            <input class="form-control" data-multiple-select-input="platforms"/>
            <f:select path="platforms"
                      multiple="multiple"
                      items="${platforms}"
                      cssClass="form-select"
                      itemLabel="name"
                      data-multiple-select="platforms"
            >
            </f:select>
            <f:errors path="platforms" cssClass="invalid-feedback"/>
        </div>

        <f:label path="description" class="col-3 col-lg-2 pe-1 col-form-label d-flex justify-content-end">Description</f:label>
        <div class="col-9 col-lg-10 mb-3">
            <f:input type="text" cssClass="form-control" path="description"/>
            <f:errors path="description" cssClass="invalid-feedback"/>
        </div>

        <div>
            <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
            <f:button class="btn btn-lg btn-danger" type="reset">Reset</f:button>
        </div>
    </div>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
