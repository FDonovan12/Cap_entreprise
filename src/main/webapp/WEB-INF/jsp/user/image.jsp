<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="formulaire de jeu"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<form method="POST" action="${currentUrl}" enctype="multipart/form-data">
    <label>Choose a file :</label>
    <input type="file" name="file" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Submit"/>
</form>
<div>
    ${message}
</div>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
