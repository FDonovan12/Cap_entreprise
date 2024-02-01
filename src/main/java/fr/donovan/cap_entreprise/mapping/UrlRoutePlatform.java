package fr.donovan.cap_entreprise.mapping;

public interface UrlRoutePlatform {

    String URL_PLATFORM = "/platforme";
    String URL_ADMIN_PLATFORM = "/admin" + URL_PLATFORM;
    String URL_PLATFORM_NEW = URL_PLATFORM + "/nouvelle";
    String URL_PLATFORM_EDIT = URL_PLATFORM + "/modification";

    String URL_ADMIN_PLATFORM_NEW = URL_ADMIN_PLATFORM + "/nouvelle";
    String URL_ADMIN_PLATFORM_EDIT = URL_ADMIN_PLATFORM + "/modification";
}
