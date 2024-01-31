<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="Formulaire de genre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<f:form modelAttribute="genre" method="post" action="${currentUrl}" cssClass="p-1 p-md-5">
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/form.jsp"/>
</f:form>


<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
