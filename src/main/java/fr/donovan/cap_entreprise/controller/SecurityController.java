package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.DTO.UserDTO;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import fr.donovan.cap_entreprise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class SecurityController {

    private UserService userService;

    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userForm", new UserDTO());
        return mav;
    }

    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(
            @ModelAttribute("userForm") UserDTO userForm,
            BindingResult bindingResult,
            ModelAndView mav
    ) {
        System.out.println("SecurityController.register");
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userService.persist(userForm);
        mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, String error) {
        if (error != null) {
            mav.addObject("error", "Your username or password is invalid.");
        }
        mav.setViewName("security/login");
        return mav;
    }

}
