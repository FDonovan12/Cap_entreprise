package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.Classification;
import fr.donovan.cap_entreprise.DTO.ClassificationDTO;
import fr.donovan.cap_entreprise.service.ClassificationService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import fr.donovan.cap_entreprise.service.GameService;
import fr.donovan.cap_entreprise.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class ClassificationController {

    private final ClassificationService classificationService;

    private final GameService gameService;

    @GetMapping(path = UrlRoute.URL_CLASSIFICATION)
    public ModelAndView index(ModelAndView mav, @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "name" }, // order by
                                                direction = Sort.Direction.ASC)
                                                Pageable pageable) {
        mav.setViewName("classification/index");
        mav.addObject("classifications", classificationService.findAll(pageable));
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_CLASSIFICATION + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field,
                                                @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "name" }, // order by
                                                direction = Sort.Direction.ASC)
                                                Pageable pageable) {
        Classification classification = classificationService.getByField(field);

        mav.setViewName("classification/show");
        mav.addObject("classification", classification);
        mav.addObject("games", gameService.getGamesByObject(classification, pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_CLASSIFICATION_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new ClassificationDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_CLASSIFICATION_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                classificationService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_CLASSIFICATION_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("classification") ClassificationDTO classificationDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, classificationDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_CLASSIFICATION_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("classification") ClassificationDTO classificationDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, classificationDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ClassificationDTO dto, String uri, boolean isEdit) {
        mav.setViewName("classification/form");
        mav.addObject("classification", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ClassificationDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("classification/form");
            return mav;
        }
        classificationService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_CLASSIFICATION);
        return mav;
    }

}
