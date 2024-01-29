<%@ include file="../tag.jsp" %>

<div class="row">
    <c:set var="count" scope="request" value="0"/>
    <c:forEach items="${games}" var="game">
        <a class="col-12 col-md-6 col-lg-4 mt-2 main-game-card" href="${UrlRoute.URL_GAME}/${game.id}">
            <div class="game-card">
                <div class="game-card-img">
                    <img alt="${game.name}" src="${game.image}">
                </div>
                <div class="d-flex justify-content-between">
                    <p>${game.name}</p>
                    <p>${jspUtils.getStringRating(games_rating.get(count))}</p>
                </div>
            </div>
        </a>
        <c:set var="count" scope="request" value="${count+1}"/>
    </c:forEach>
</div>