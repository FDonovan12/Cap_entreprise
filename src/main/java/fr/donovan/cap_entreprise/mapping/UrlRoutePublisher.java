package fr.donovan.cap_entreprise.mapping;

public interface UrlRoutePublisher {

    String URL_PUBLISHER = "/publisher";
    String URL_PUBLISHER_NEW = URL_PUBLISHER + "/new";
    String URL_PUBLISHER_EDIT = URL_PUBLISHER + "/edit";

    String URL_ADMIN_PUBLISHER = "/admin" + URL_PUBLISHER;
    String URL_ADMIN_PUBLISHER_NEW = URL_ADMIN_PUBLISHER + "/new";
    String URL_ADMIN_PUBLISHER_EDIT = URL_ADMIN_PUBLISHER + "/edit";
}
