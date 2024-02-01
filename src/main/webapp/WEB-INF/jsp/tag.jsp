<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="fr.donovan.cap_entreprise.mapping.UrlRoute" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="fr.donovan.cap_entreprise.utils.DateUtils" %>
<%@ page import="fr.donovan.cap_entreprise.utils.JspUtils" %>
<%@ page import="fr.donovan.cap_entreprise.service.UserService" %>
<%@ page import="fr.donovan.cap_entreprise.entity.User" %>
<%@ page import="fr.donovan.cap_entreprise.service.ReviewService" %>
<c:set var="nbRainbow" value="50"/>
<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String path = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
    Object query = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
    String queryString = "";
    if (query != null) {
        queryString = query.toString();
    }
    request.setAttribute("currentQuery", queryString);
    request.setAttribute("currentPath", path);
    request.setAttribute("currentUrl", url + path);

    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
    if (ctx != null) {
        request.setAttribute("dateUtils", ctx.getBean(DateUtils.class));
        request.setAttribute("jspUtils", ctx.getBean(JspUtils.class));
    }
    User user = null;
    UserService userService = ctx.getBean(UserService.class);
    ReviewService reviewService = ctx.getBean(ReviewService.class);
    request.setAttribute("reviewService", reviewService);

    if (request.getUserPrincipal() != null) {
        user = userService.getObjectByNickname(request.getUserPrincipal().getName());
        request.setAttribute("userLogged", user);
    }

    JspUtils jspUtils = new JspUtils();
    String rainbow = jspUtils.getRainbow(500, "150deg", 10);

    if (user != null && user.isVeryEccentric()) {
        request.setAttribute("rainbowStyleVery", rainbow);
    }

    if (user != null && user.isEccentric()) {
        request.setAttribute("rainbowStyle", rainbow);
    }
%>