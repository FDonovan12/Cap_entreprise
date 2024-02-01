<%@ include file="../tag.jsp" %>

<div class="row">
    ${jspUtils.getPagination(games, currentUrl, currentQuery)}
    <c:forEach items="${games.content}" var="game">
        <a class="col-6 col-md-4 col-lg-2 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.id}">
            <div class="game-card">
                <div class="game-card-img">
                    <img class="img-fluid" alt="${game.name}" src="${game.image}">
                </div>
                <div class="d-flex justify-content-between">
                    <p>${game.name}</p>
                    <div class="">
                        <div>Note </div>
                        <div> ${jspUtils.getStringRating(reviewService.getRatingByObject(game))}</div>
                    </div>
<%--                    <p>Note moyenne </p>--%>
                </div>
            </div>
        </a>
    </c:forEach>
</div>