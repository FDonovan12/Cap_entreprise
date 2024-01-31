package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteUser {

    String URL_USER = "/utilisateur";
    String URL_ADMIN_USER = "/admin" + URL_USER;
    String URL_USER_NEW = URL_ADMIN_USER + "/nouveau";
    String URL_USER_EDIT = URL_ADMIN_USER + "/modification";
    String URL_USER_ECCENTRIC = URL_USER + "/excentrique";
    String URL_USER_VERY_ECCENTRIC = URL_USER + "/tres-excentrique";

    String URL_ADMIN_USER_NEW = URL_ADMIN_USER + "/nouveau";
    String URL_ADMIN_USER_EDIT = URL_ADMIN_USER + "/modification";
}
