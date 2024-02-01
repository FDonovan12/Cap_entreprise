package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteClassification {

    String URL_CLASSIFICATION = "/classification";
    String URL_ADMIN_CLASSIFICATION = "/admin" + URL_CLASSIFICATION;
    String URL_CLASSIFICATION_NEW = URL_CLASSIFICATION + "/nouvelle";
    String URL_CLASSIFICATION_EDIT = URL_CLASSIFICATION + "/modification";

    String URL_ADMIN_CLASSIFICATION_NEW = URL_ADMIN_CLASSIFICATION + "/nouvelle";
    String URL_ADMIN_CLASSIFICATION_EDIT = URL_ADMIN_CLASSIFICATION + "/modification";
}
