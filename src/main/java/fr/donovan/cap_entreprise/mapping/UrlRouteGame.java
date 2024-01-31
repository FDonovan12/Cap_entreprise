package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteGame {

    String URL_GAME = "/jeux";
    String URL_ADMIN_GAME = "/admin" + URL_GAME;
    String URL_GAME_NEW = URL_ADMIN_GAME + "/nouveau";
    String URL_GAME_EDIT = URL_ADMIN_GAME + "/modification";
    String URL_GAME_UPLOAD = URL_ADMIN_GAME + "/téléchargement";

    String URL_ADMIN_GAME_NEW = URL_ADMIN_GAME + "/nouveau";
    String URL_ADMIN_GAME_EDIT = URL_ADMIN_GAME + "/modification";
    String URL_ADMIN_GAME_DELETE = URL_ADMIN_GAME + "/suppression";
    String URL_GAME_DELETE = URL_ADMIN_GAME + "/suppression";
}
