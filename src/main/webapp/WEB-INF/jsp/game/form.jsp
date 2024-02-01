<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="formulaire de jeu"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="row bg-dark bg-opacity-75 rounded p-3">
    <h1>Formulaire de jeu</h1>
    <f:form modelAttribute="game" method="post" action="${currentUrl}" cssClass="p-1 p-md-3">
        <div class="col-12 col-md-10 col-lg-8 mx-auto row">

            <div class="col-12 col-lg-7 mb-1 p-1">
                <f:label path="name" class="pe-1 col-form-label">Nom</f:label>
                <div>
                    <f:input type="text" cssClass="form-control" path="name"/>
                    <f:errors path="name" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-5 mb-1 p-1">
                <f:label path="publishedAt" class=" pe-1 col-form-label">Date de sortie</f:label>
                <div>
                    <f:input type="date" cssClass="form-control" path="publishedAt"/>
                    <f:errors path="publishedAt" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-6 mb-1 p-1">
                <f:label path="businessModel" class="col-form-label">Modèle économique</f:label>
                <div>
                    <f:select items="${businessModels}" itemLabel="name" cssClass="form-select" path="businessModel"/>
                    <f:errors path="businessModel" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-6 mb-1 p-1">
                <f:label path="publisher" class="col-form-label">Éditeur</f:label>
                <div>
                    <f:select items="${publishers}" itemLabel="name" cssClass="form-select" path="publisher"/>
                    <f:errors path="publisher" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-4 mb-1 p-1">
                <f:label path="classification" class="col-form-label">Classifcation</f:label>
                <div>
                    <f:select items="${classifications}" itemLabel="name" cssClass="form-select" path="classification"/>
                    <f:errors path="classification" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-4 mb-1 p-1">
                <f:label path="genre" class="col-form-label">Genre</f:label>
                <div>
                    <f:select items="${genres}" itemLabel="name" cssClass="form-select" path="genre"/>
                    <f:errors path="genre" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-4 mb-1 p-1">
                <f:label path="platforms" class="col-form-label">Platform</f:label>
                <div>
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
            </div>

            <div class="col-12 col-lg-12 mb-1 p-1">
                <f:label path="image" class="col-form-label">Image</f:label>
                <div>
                    <f:input type="text" cssClass="form-control" path="image"/>
                    <f:errors path="image" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div class="col-12 col-lg-12 mb-1 p-1">
                <f:label path="description" class="col-form-label">Description</f:label>
                <div>
                    <f:input type="text" cssClass="form-control" path="description"/>
                    <f:errors path="description" cssClass="invalid-feedback"/>
                </div>
            </div>

            <div>
                <f:button class="btn btn-lg btn-success" type="submit">Submit</f:button>
                <f:button class="btn btn-lg btn-danger" type="reset">Reset</f:button>
            </div>
        </div>
    </f:form>
</div>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
