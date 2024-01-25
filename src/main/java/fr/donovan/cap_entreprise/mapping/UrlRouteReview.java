package fr.donovan.cap_entreprise.mapping;

public interface UrlRouteReview {

    String URL_REVIEW = "/review";
    String URL_REVIEW_NEW = URL_REVIEW + "/new";
    String URL_REVIEW_EDIT = URL_REVIEW + "/edit";
    String URL_REVIEW_DELETE = URL_REVIEW + "/delete";
    String URL_REVIEW_VALIDATE = URL_REVIEW + "/validate";

    String URL_ADMIN_REVIEW = "/admin" + URL_REVIEW;
    String URL_ADMIN_REVIEW_NEW = URL_ADMIN_REVIEW + "/new";
    String URL_ADMIN_REVIEW_EDIT = URL_ADMIN_REVIEW + "/edit";
}
