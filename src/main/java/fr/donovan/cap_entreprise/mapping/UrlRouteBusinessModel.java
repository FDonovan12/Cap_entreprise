package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteBusinessModel {

    String URL_BUSINESSMODEL = "/modele-economique";
    String URL_ADMIN_BUSINESSMODEL = "/admin" + URL_BUSINESSMODEL;
    String URL_BUSINESSMODEL_NEW = URL_ADMIN_BUSINESSMODEL + "/nouveau";
    String URL_BUSINESSMODEL_EDIT = URL_ADMIN_BUSINESSMODEL + "/modification";

    String URL_ADMIN_BUSINESSMODEL_NEW = URL_ADMIN_BUSINESSMODEL + "/nouveau";
    String URL_ADMIN_BUSINESSMODEL_EDIT = URL_ADMIN_BUSINESSMODEL + "/modification";
}
