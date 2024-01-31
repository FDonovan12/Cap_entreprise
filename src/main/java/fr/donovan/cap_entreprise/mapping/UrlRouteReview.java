package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteReview {

    String URL_REVIEW = "/avis";
    String URL_ADMIN_REVIEW = "/admin" + URL_REVIEW;
    String URL_REVIEW_NEW = URL_ADMIN_REVIEW + "/nouveau";
    String URL_REVIEW_EDIT = URL_ADMIN_REVIEW + "/modification";

    String URL_ADMIN_REVIEW_NEW = URL_ADMIN_REVIEW + "/nouveau";
    String URL_ADMIN_REVIEW_EDIT = URL_ADMIN_REVIEW + "/modification";
    String URL_REVIEW_DELETE = URL_ADMIN_REVIEW + "/suppression";
    String URL_REVIEW_VALIDATE = URL_ADMIN_REVIEW + "/validation";
}
