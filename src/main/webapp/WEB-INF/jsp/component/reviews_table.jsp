<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>

${jspUtils.getPagination(reviews, currentUrl, currentQuery)}

<div class="row">
    <c:forEach items="${reviews.content}" var="review">
        <div class="col-lg-4 col-md-6 col-sm-12 mt-4">
            <div class="main-review-card w-100">
                <p class="text-center">
                    Rédigé le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}
                    par <a class="btn-link" href="${UrlRoute.URL_USER}/${review.gamer.id}">${review.gamer.nickname}</a>
                    <figcaption class="blockquote-footer text-center">
                        <c:if test="${not empty review.moderator}">
                            Modéré par <cite title="Source Title">${review.moderator.nickname}</cite> -
                            le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}
                        </c:if>
                        <c:if test="${empty review.moderator}">
                            <cite title="Source Title">En attente de moderation ⌛</cite>
                            <security:authorize access="hasRole('ROLE_MODERATOR')">
                                <a class="btn btn-link text-success"
                                   href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/1"
                                   title="Accepter"
                                >
                                    <i class="fa fa-check fa-2x"></i>
                                </a>
                                /
                                <a class="btn btn-link text-danger"
                                   href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/0"
                                   title="Refuser"
                                >
                                    <i class="fa-solid fa-xmark fa-2x"></i>
                                </a>
                            </security:authorize>
                        </c:if>
                    </figcaption>
                </p>
                <div class="review-card w-100">
                    <p class="review-description">
                            ${jspUtils.excerpt(review.description, 209)}
                    </p>
                    <div class="d-flex justify-content-between">
                        <p class="${jspUtils.getCssClas(review.rating)}">
                                ${jspUtils.getStringRating(review.rating)} / 20
                        </p>
                        <a class="btn-link" href="${UrlRoute.URL_GAME}/${review.game.id}">
                                ${review.game.name}
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>