package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteBusinessModel {

    String URL_BUSINESSMODEL = "/businessmodel";
    String URL_BUSINESSMODEL_NEW = URL_BUSINESSMODEL + "/new";
    String URL_BUSINESSMODEL_EDIT = URL_BUSINESSMODEL + "/edit";

    String URL_ADMIN_BUSINESSMODEL = "/admin" + URL_BUSINESSMODEL;
    String URL_ADMIN_BUSINESSMODEL_NEW = URL_ADMIN_BUSINESSMODEL + "/new";
    String URL_ADMIN_BUSINESSMODEL_EDIT = URL_ADMIN_BUSINESSMODEL + "/edit";
}
