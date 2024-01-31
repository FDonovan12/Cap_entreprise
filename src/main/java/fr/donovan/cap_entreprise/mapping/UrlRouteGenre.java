package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteGenre {

    String URL_GENRE = "/genre";
    String URL_ADMIN_GENRE = "/admin" + URL_GENRE;
    String URL_GENRE_NEW = URL_ADMIN_GENRE + "/nouveau";
    String URL_GENRE_EDIT = URL_ADMIN_GENRE + "/modification";

    String URL_ADMIN_GENRE_NEW = URL_ADMIN_GENRE + "/nouveau";
    String URL_ADMIN_GENRE_EDIT = URL_ADMIN_GENRE + "/modification";
}
