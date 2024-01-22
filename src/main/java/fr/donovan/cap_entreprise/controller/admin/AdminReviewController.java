package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.DTO.ReviewDTO;
import fr.donovan.cap_entreprise.service.ReviewService;
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
public class AdminReviewController {

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/review/index");
        mav.addObject("reviews", reviewService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_REVIEW + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Review review = reviewService.getByField(field);

        mav.setViewName("admin/review/show");
        mav.addObject("review", review);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new ReviewDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_REVIEW_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                reviewService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_REVIEW_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, reviewDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_ADMIN_REVIEW_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("review") ReviewDTO reviewDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, reviewDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ReviewDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/review/form");
        mav.addObject("review", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ReviewDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/review/form");
            return mav;
        }
        reviewService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_REVIEW);
        return mav;
    }

}
