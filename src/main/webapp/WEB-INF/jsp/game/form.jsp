<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="formulaire de jeu"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Formulaire de jeu</h1>
<f:form modelAttribute="game" method="post" action="${action}" cssClass="p-5">
    <div class="mb-3 row">
        <f:label path="name" class="col-sm-2 col-form-label">Nom</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="name"/>
            <f:errors path="name" cssClass="invalid-feedback"/>
        </div>

        <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="description"/>
            <f:errors path="description" cssClass="invalid-feedback"/>
        </div>

        <f:label path="publishedAt" class="col-sm-2 col-form-label">Date de sortie</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="date" cssClass="form-control" path="publishedAt"/>
            <f:errors path="publishedAt" cssClass="invalid-feedback"/>
        </div>

        <f:label path="image" class="col-sm-2 col-form-label">Image</f:label>
        <div class="col-sm-10 mb-3">
            <f:input type="text" cssClass="form-control" path="image"/>
            <f:errors path="image" cssClass="invalid-feedback"/>
        </div>

        <f:label path="classification" class="col-sm-2 col-form-label">Classifcation</f:label>
        <div class="col-sm-10 mb-3">
            <f:select items="${classifications}" itemLabel="name" cssClass="form-select" path="classification"/>
<%--            <f:input type="number" cssClass="form-control" path="classification"/>--%>
            <f:errors path="classification" cssClass="invalid-feedback"/>
        </div>

        <f:label path="genre" class="col-sm-2 col-form-label">Genre</f:label>
        <div class="col-sm-10 mb-3">
            <f:select items="${genres}" itemLabel="name" cssClass="form-select" path="genre"/>
<%--            <f:input type="number" cssClass="form-control" path="genre"/>--%>
            <f:errors path="genre" cssClass="invalid-feedback"/>
        </div>

        <f:label path="publisher" class="col-sm-2 col-form-label">Editeur</f:label>
        <div class="col-sm-10 mb-3">
            <f:select items="${publishers}" itemLabel="name" cssClass="form-select" path="publisher"/>
<%--            <f:input type="number" cssClass="form-control" path="publisher"/>--%>
            <f:errors path="publisher" cssClass="invalid-feedback"/>
        </div>

        <f:label path="businessModel" class="col-sm-2 col-form-label">Modele economique</f:label>
        <div class="col-sm-10 mb-3">
            <f:select items="${businessModels}" itemLabel="name" cssClass="form-select" path="businessModel"/>
<%--            <f:input type="number" cssClass="form-control" path="businessModel"/>--%>
            <f:errors path="businessModel" cssClass="invalid-feedback"/>
        </div>

<%--        <div class="col-sm-10 mb-3">--%>
<%--            <f:input cssClass="form-control" path="moderator"/>--%>
<%--            <f:errors path="moderator" cssClass="invalid-feedback"/>--%>
<%--        </div>--%>
        <f:label path="platforms" class="col-sm-2 col-form-label">Platform</f:label>
        <div class="col-sm-10 mb-3">
            <input class="form-control" data-multiple-select-input="platform"/>
            <f:select path="platforms"
                      multiple="multiple"
                      items="${platforms}"
                      cssClass="form-select"
                      itemLabel="name"
                      data-multiple-select="platform"
            >
            </f:select>
            <f:errors path="platforms" cssClass="invalid-feedback"/>
        </div>
    </div>
    <f:button class="btn btn-secondary" type="reset">Reset</f:button>
    <f:button class="btn btn-primary">Submit</f:button>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
