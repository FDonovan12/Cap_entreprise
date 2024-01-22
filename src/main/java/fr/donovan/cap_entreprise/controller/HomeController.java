package fr.donovan.cap_entreprise.controller;

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

    @GetMapping(path = "/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

}
