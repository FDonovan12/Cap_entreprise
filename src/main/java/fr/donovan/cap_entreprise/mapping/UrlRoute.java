package fr.donovan.cap_entreprise.mapping;

public class UrlRoute implements
        UrlRouteGame, UrlRouteBusinessModel, UrlRouteGenre, UrlRoutePlatform, UrlRoutePublisher, UrlRouteReview, UrlRouteUser, UrlRouteClassification{

    public static final String URL_HOME = "";
    public static final String URL_API = "/api";
    public static final String URL_LOGIN = "/connexion";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_REGISTER = "/inscription";
    public static final String URL_ADMIN = "/admin";

    public static final String URL_EXPORT = "/telecharger-export-excel";

    public static final String DIR_PATH = "src/main/webapp";

    public static final String PATH_IMAGE = "/resources/image/";

    public static final String PATH_EXCEL = "/resources/image/";
}
