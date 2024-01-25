package fr.donovan.cap_entreprise.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public String getPagination(Page<Object> page, String url) {
        int totalPage = page.getTotalPages();
        int currentPage = page.getNumber()+1;

        String invisibleShort = "<span class=\"invisible\">#</span>";
        String invisibleLong = "<span class=\"invisible\">##</span>";
        // Pageable url nb_page char
        String format = "<a class=\"%s\" href=\"%s?page=%d\">%s</a>";
        String result = "<div class=\"navigation d-flex justify-content-center my-4\"><div class=\"pagination\">";

        if (!page.isFirst()) {
            result += getLinkPage(format,"pageable", url, 0, "<<");
        } else {
            result += invisibleLong;
        }
        for (int i = currentPage-2; i <= currentPage+2 ; i++) {
            if (i >= 1 && i <= totalPage) {
                if (i == currentPage) {
                    result += getLinkPage(format,"current", url, i, i+"");
                } else {
                    result += getLinkPage(format,"pageable", url, i, i+"");
                }
            } else {
                result += invisibleShort;
            }
        }
        if (!page.isLast()) {
            result += getLinkPage(format,"pageable", url, totalPage, ">>");
        } else {
            result += invisibleLong;
        }
        result += "</div></div>";

        result += "<div class=\"navigation d-flex justify-content-center my-4\"><div class=\"pagination\">";
        System.out.println("totalPage = " + totalPage);
        System.out.println("totalPage/10 = " + totalPage/10);
        int nbPage = Math.min(9, totalPage/2);
        int part = (int)(totalPage/(nbPage+1));
        for (int i = 1; i <= nbPage; i++) {
            System.out.println("i = " + i);
            System.out.println("i*10+1 = " + i*10+1);
            result += getLinkPage(format,"pageable", url, (int)(i*totalPage/(nbPage+1)), (int)(i*totalPage/(nbPage+1))+"");
        }
        result += "</div></div>";
        return result;
    }

    public String getLinkPage(String format, String cssClass, String url, int nb_page, String character) {
        return String.format(format, cssClass, url, nb_page, character);
    }

    public String getRainbow(int number) {
        String result = "background: linear-gradient(0.2turn";
        for (int i = 0; i < number; i++) {
            int current = number/3;
            int red = (int)(Math.random()*256);
            int green = (int)(Math.random()*256);
            int blue = (int)(Math.random()*256);
            result += String.format(", rgb(%d,%d,%d)", red, green, blue);
        }
        return result+")";
    }

}