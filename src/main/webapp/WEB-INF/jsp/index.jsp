<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tous les avis</h1>
<div class="col-12">
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
