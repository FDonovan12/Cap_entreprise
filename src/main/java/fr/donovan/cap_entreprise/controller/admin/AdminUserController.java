package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.DTO.UserDTO;
import fr.donovan.cap_entreprise.service.UserService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping(path = UrlRoute.URL_ADMIN_USER)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/user/index");
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_USER + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        User user = userService.getByField(field);

        mav.setViewName("admin/user/show");
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_USER_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new UserDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_USER_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                userService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_USER_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, userDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_USER_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, userDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, UserDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/user/form");
        mav.addObject("user", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, UserDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/user/form");
            return mav;
        }
        userService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_USER);
        return mav;
    }

}
