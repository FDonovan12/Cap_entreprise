package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.mapping.UrlRoute;
import fr.donovan.cap_entreprise.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController {

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_HOME)
    public String index() {
        return "redirect:" + UrlRoute.URL_REVIEW;
    }
}
