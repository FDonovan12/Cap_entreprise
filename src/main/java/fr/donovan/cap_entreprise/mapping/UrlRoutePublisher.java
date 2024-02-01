package fr.donovan.cap_entreprise.mapping;

public interface UrlRoutePublisher {

    String URL_PUBLISHER = "/editeur";
    String URL_ADMIN_PUBLISHER = "/admin" + URL_PUBLISHER;
    String URL_PUBLISHER_NEW = URL_PUBLISHER + "/nouveau";
    String URL_PUBLISHER_EDIT = URL_PUBLISHER + "/modification";

    String URL_ADMIN_PUBLISHER_NEW = URL_ADMIN_PUBLISHER + "/nouveau";
    String URL_ADMIN_PUBLISHER_EDIT = URL_ADMIN_PUBLISHER + "/modification";
}
