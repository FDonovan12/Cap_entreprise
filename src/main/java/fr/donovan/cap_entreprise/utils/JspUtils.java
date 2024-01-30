package fr.donovan.cap_entreprise.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class JspUtils {

    public String excerpt(String text, int size) {
        if (text.length() <= size) return text;
        return text.substring(0, Math.max(size-3, 0)) + "...";
    }

    public String getCssClas(float rating) {
        if (rating <= 5) return "rating-5";
        if (rating <= 10) return "rating-10";
        if (rating <= 15) return "rating-15";
        if (rating < 20) return "rating-19";
        return "rating-20";
    }

    public String getFa(int number, String delimiter) {
        return getFa(number + "", delimiter);
    }

    public String getFa(String string, String delimiter) {
        String prefix = "<i class=\"fa-solid fa-";
        String suffix = "\"></i>";
        String s;
        List<String> list = new ArrayList<>();

        String result = Arrays.stream(string.split(""))
                .map(element -> {
                    if (element.matches("[0-9a-zA-Z]")) {
                        return (prefix + element + suffix);
                    }
                    return element;
                }).collect(Collectors.joining(delimiter));

        return result;
    }

    public String getPagination(Page<Object> page, String url, String currentQuery) {
        int totalPage = page.getTotalPages();
        int currentPage = page.getNumber()+1;

        String invisibleShort = "<span class=\"invisible\">#</span>";
        String invisibleLong = "<span class=\"invisible\">##</span>";

        String format = "<a class=\"%s\" href=\"%s\">%s</a>";
        String result = "<div class=\"navigation d-flex justify-content-center my-4\"><div class=\"pagination\">";

        if (!page.isFirst()) {
            result += getLinkPage(format,"pageable", url, currentQuery, 0, "<<");
        } else {
            result += invisibleLong;
        }
        for (int i = currentPage-2; i <= currentPage+2 ; i++) {
            if (i >= 1 && i <= totalPage) {
                if (i == currentPage) {
                    result += getLinkPage(format,"current", url, currentQuery, i, i+"");
                } else {
                    result += getLinkPage(format,"pageable", url, currentQuery, i, i+"");
                }
            } else {
                result += invisibleShort;
            }
        }
        if (!page.isLast()) {
            result += getLinkPage(format,"pageable", url, currentQuery, totalPage, ">>");
        } else {
            result += invisibleLong;
        }
        result += "</div></div>";

        result += "<div class=\"d-flex justify-content-around\"><div>Total page " + totalPage + "</div>";
        result += "<div>Total element " + page.getTotalElements() + "</div></div>";

//        result += "<div class=\"navigation d-flex justify-content-center my-4\"><div class=\"pagination\">";
//        int nbPage = Math.min(9, totalPage/2);
//        for (int i = 1; i <= nbPage; i++) {
//            result += getLinkPage(format,"pageable", url, currentQuery, (int)(i*totalPage/(nbPage+1)), (int)(i*totalPage/(nbPage+1))+"");
//        }
//        result += "</div></div>";

        return result;
    }

    public String getLinkPage(String format, String cssClass, String url, String currentQuery, int nb_page, String character) {
        String newUrl = getUrlFrom(url, currentQuery, "page="+nb_page);
        return String.format(format, cssClass, newUrl, character);
    }

    public String getRainbow(int number, String method, double duration) {
        System.out.println("Integer.toHexString(16) = " + Integer.toHexString(16));
        System.out.println("Integer.toHexString(160) = " + Integer.toHexString(160));
        String result = "style=\"background: linear-gradient(" + method;
        for (int i = 0; i < number; i++) {
            int current = number/3;

            int redInt = (int)(Math.random()*256);
            int greenInt = (int)(Math.random()*256);
            int blueInt = (int)(Math.random()*256);

            String red = StringUtils.leftPad(Integer.toHexString(redInt), 2, '0');
            String green = StringUtils.leftPad(Integer.toHexString(greenInt), 2, '0');
            String blue = StringUtils.leftPad(Integer.toHexString(blueInt), 2, '0');

            if (red.length() <2 || green.length() <2 || blue.length() <2) {
                System.out.println("red + green + blue = " + red + " " + green + " " + blue);
                System.out.println("int red + green + blue = " + redInt + " " + greenInt + " " + blueInt);
            }
//            result += String.format(", rgb(%d,%d,%d)", red, green, blue);
//            result += ", #ee7752, #e73c7e, #23a6d5, #23d5ab";
            result += String.format(", #%s%s%s", red, green, blue);
        }
        return result+"); background-size: 400% 400%; animation: gradient "+duration+"s ease infinite\"";
    }

    public String getUrlFrom(String currentUrl, String currentQuery, String newSort) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(currentUrl);
        if (!currentQuery.isEmpty()) {
            for (String oldQueryParamSplit : currentQuery.split("&")) {
                url = addQueryParam(url, oldQueryParamSplit);
            }
        }
        url = addQueryParam(url, newSort);
        return url.toUriString();
    }

    private UriComponentsBuilder addQueryParam(UriComponentsBuilder uri, String query) {
        if (query.equals("continue")) {
            return uri;
        }
        String[] parsed = query.split("=");
        return addQueryParam(uri, parsed[0], parsed[1]);
    }

    private UriComponentsBuilder addQueryParam(UriComponentsBuilder uri, String queryParamName, String queryParamValue) {

        if (queryParamName.equals("page")) {
            if ( uri.toUriString().contains("page")) {
                return UriComponentsBuilder.fromHttpUrl(
                        uri.toUriString()
                                .replaceAll("page=[0-9]+",
                                        "page=" + queryParamValue));
            } else {
                uri.queryParam(queryParamName, queryParamValue);
            }
        }
        
        if (queryParamName.equals("sort")) {
            String queryAttribute = queryParamValue.split(",")[0];
            if (uri.toUriString().contains(queryParamName + "=" + queryAttribute + ",")) {
                String replacement = "";
                if (!uri.toUriString().contains(queryParamName + "=" + queryParamValue)) {
                    replacement = "sort=" + queryAttribute + ",desc";
                    if (queryParamValue.contains("asc")) {
                        replacement = "sort=" + queryAttribute + ",asc";
                    }
                }
                uri = UriComponentsBuilder.fromHttpUrl(
                        uri.toUriString()
                                .replaceAll("sort=" + queryAttribute + ",(asc|desc)", replacement));
                return uri;
            }
            return uri.queryParam(queryParamName, queryParamValue);
        }
        return uri;
    }

    public String getStringRating(float rating) {
        rating = (float) Math.round(rating * 100) /100;
        return ("" + rating).replaceAll(".0$", "");
    }
}