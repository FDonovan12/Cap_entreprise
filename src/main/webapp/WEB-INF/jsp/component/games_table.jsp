<%@ include file="../tag.jsp" %>

<div class="row">
    ${jspUtils.getPagination(games, currentUrl, currentQuery)}
    <c:forEach items="${games.content}" var="game">
        <a class="col-12 col-md-6 col-lg-4 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.id}">
            <div class="game-card">
                <div class="game-card-img">
                    <img alt="${game.name}" src="${game.image}">
                </div>
                <div class="d-flex justify-content-between">
                    <p>${game.name}</p>
                    <p>Note moyenne ${jspUtils.getStringRating(reviewService.getRatingByObject(game))}</p>
                </div>
            </div>
        </a>
    </c:forEach>
</div>