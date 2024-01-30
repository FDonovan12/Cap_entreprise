package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.DTO.ReviewDTO;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.service.GameService;
import fr.donovan.cap_entreprise.service.ReviewService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import fr.donovan.cap_entreprise.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final GameService gameService;

    private final UserService userService;

    @GetMapping(path = UrlRoute.URL_REVIEW)
    public ModelAndView index(ModelAndView mav, @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "createdAt" }, // order by
                                                direction = Sort.Direction.DESC)
                                                Pageable pageable,
                                                Principal principal) {
        mav.setViewName("review/index");
        User user = userService.getObjectByNickname(principal.getName());
        mav.addObject("pageReviews", reviewService.findAll(user, pageable));
        mav.addObject("games", gameService.findAll());
        return mav;
    }
    @PostMapping(path = UrlRoute.URL_REVIEW)
    public ModelAndView indexFilter(ModelAndView mav,
                                    @ModelAttribute("review") ReviewDTO reviewFilter) {

        mav.setViewName("review/index");
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_REVIEW + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Review review = reviewService.getByField(field);

        mav.setViewName("review/show");
        mav.addObject("review", review);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        mav.addObject("games", gameService.findAll());
        ReviewDTO dto = new ReviewDTO();
        dto.setGame(gameService.getObjectById(2L));
        return getFormByDTO(
                mav,
                dto,
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_NEW + "/{id}")
    public ModelAndView create(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        mav.addObject("games", List.of(gameService.getObjectById(id)));
        ReviewDTO dto = new ReviewDTO();
//        dto.setGame(gameService.getObjectById(id));
        return getFormByDTO(
                mav,
                dto,
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                reviewService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_REVIEW_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav,
            Principal principal
    ) {
        return formHandle(result, mav, reviewDTO, null, principal);
    }

    @PostMapping(path = UrlRoute.URL_REVIEW_NEW + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav,
            Principal principal,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, reviewDTO, null, principal);
    }

    @PostMapping(path = UrlRoute.URL_REVIEW_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id,
            Principal principal
    ) {
        return formHandle(result, mav, reviewDTO, id, principal);
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_DELETE + "/{id}")
    public ModelAndView deleteReview(ModelAndView mav, @PathVariable("id") long id) {
        reviewService.delete(id);
        return new ModelAndView("redirect:" + UrlRoute.URL_REVIEW);
    }

    @GetMapping(path = UrlRoute.URL_REVIEW_VALIDATE + "/{id}")
    public ModelAndView validateReview(ModelAndView mav, @PathVariable("id") long id, Principal principal) {
        reviewService.validate(id, principal.getName());
        return new ModelAndView("redirect:" + UrlRoute.URL_REVIEW);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ReviewDTO dto, String uri, boolean isEdit) {
        mav.setViewName("review/form");
        System.out.println("uri = " + uri);
        mav.addObject("review", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ReviewDTO dto, Long id, Principal principal) {
        if (result.hasErrors()) {
            mav.setViewName("review/form");
            return mav;
        }
        dto.setUser(userService.getObjectByNickname(principal.getName()));
        reviewService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW);
        return mav;
    }

}
