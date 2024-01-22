package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteClassification {

    String URL_CLASSIFICATION = "/classification";
    String URL_CLASSIFICATION_NEW = URL_CLASSIFICATION + "/new";
    String URL_CLASSIFICATION_EDIT = URL_CLASSIFICATION + "/edit";

    String URL_ADMIN_CLASSIFICATION = "/admin" + URL_CLASSIFICATION;
    String URL_ADMIN_CLASSIFICATION_NEW = URL_ADMIN_CLASSIFICATION + "/new";
    String URL_ADMIN_CLASSIFICATION_EDIT = URL_ADMIN_CLASSIFICATION + "/edit";
}
