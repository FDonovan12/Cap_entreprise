package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.DTO.UserDTO;
import fr.donovan.cap_entreprise.service.ReviewService;
import fr.donovan.cap_entreprise.service.UserService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_USER)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("user/index");
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_USER + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field,
                                                @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "createdAt" }, // order by
                                                direction = Sort.Direction.DESC)
                                                Pageable pageable) {
        User user = userService.getByField(field);

        mav.setViewName("user/show");
        mav.addObject("user", user);
        mav.addObject("reviews", reviewService.findByGamer(user, pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_USER_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new UserDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_USER_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                userService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_USER_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, userDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_USER_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("user") UserDTO userDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, userDTO, id);
    }

    @GetMapping(path = UrlRoute.URL_USER_STYLE+ "/{style}" + "/{url}")
    public ModelAndView eccentric(ModelAndView mav, Principal principal,
                                                    @PathVariable int style,
                                                    @PathVariable String url) {
        userService.style(style, principal.getName());
        return new ModelAndView("redirect:" + url.replaceAll("-_-", "/"));
    }

    private ModelAndView getFormByDTO(ModelAndView mav, UserDTO dto, String uri, boolean isEdit) {
        mav.setViewName("user/form");
        mav.addObject("user", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, UserDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("user/form");
            return mav;
        }
        userService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_USER);
        return mav;
    }

}
