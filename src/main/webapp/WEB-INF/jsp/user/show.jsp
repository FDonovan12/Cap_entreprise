<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${game.name}"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="d-flex">
    <h1 class="mb-2">${user.nickname}</h1>
    <div class="ps-3 fs-3">${jspUtils.getStringRating(reviewService.getRatingByObject(user))}</div>
</div>
<div class="row">
    <h2>Commentaires</h2>
    <c:set var="reviews" scope="request" value="${reviews}"/>
    <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/reviews_table.jsp"/>
</div>

<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/footer.jsp"/>
