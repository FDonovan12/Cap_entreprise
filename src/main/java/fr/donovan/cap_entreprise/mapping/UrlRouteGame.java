package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteGame {

    String URL_GAME = "/game";
    String URL_GAME_NEW = URL_GAME + "/new";
    String URL_GAME_EDIT = URL_GAME + "/edit";

    String URL_ADMIN_GAME = "/admin" + URL_GAME;
    String URL_ADMIN_GAME_NEW = URL_ADMIN_GAME + "/new";
    String URL_ADMIN_GAME_EDIT = URL_ADMIN_GAME + "/edit";
}
