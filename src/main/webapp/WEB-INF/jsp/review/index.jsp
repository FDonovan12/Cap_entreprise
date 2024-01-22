<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="review"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<h1>Tout les avis</h1>
<div class="row">
    <c:forEach items="${reviews}" var="review">
        <div class="col-3">
            review description
            ${review.description}
        </div>
    </c:forEach>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
