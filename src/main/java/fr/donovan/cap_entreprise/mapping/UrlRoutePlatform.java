package fr.donovan.cap_entreprise.mapping;

public interface UrlRoutePlatform {

    String URL_PLATFORM = "/platform";
    String URL_PLATFORM_NEW = URL_PLATFORM + "/new";
    String URL_PLATFORM_EDIT = URL_PLATFORM + "/edit";

    String URL_ADMIN_PLATFORM = "/admin" + URL_PLATFORM;
    String URL_ADMIN_PLATFORM_NEW = URL_ADMIN_PLATFORM + "/new";
    String URL_ADMIN_PLATFORM_EDIT = URL_ADMIN_PLATFORM + "/edit";
}
