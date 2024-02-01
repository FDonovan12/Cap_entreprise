package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.BusinessModel;
import fr.donovan.cap_entreprise.DTO.BusinessModelDTO;
import fr.donovan.cap_entreprise.service.BusinessModelService;
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
public class BusinessModelController {

    private final BusinessModelService businessModelService;

    private final GameService gameService;

    @GetMapping(path = UrlRoute.URL_BUSINESSMODEL)
    public ModelAndView index(ModelAndView mav, @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "name" }, // order by
                                                direction = Sort.Direction.ASC)
                                                Pageable pageable) {
        mav.setViewName("businessModel/index");
        mav.addObject("businessModels", businessModelService.findAll(pageable));
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_BUSINESSMODEL + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field,
                                                @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "name" }, // order by
                                                direction = Sort.Direction.ASC)
                                                Pageable pageable) {
        BusinessModel businessModel = businessModelService.getByField(field);

        mav.setViewName("businessModel/show");
        mav.addObject("businessModel", businessModel);
        mav.addObject("games", gameService.getGamesByObject(businessModel, pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_BUSINESSMODEL_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new BusinessModelDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_BUSINESSMODEL_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                businessModelService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_BUSINESSMODEL_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("businessModel") BusinessModelDTO businessModelDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, businessModelDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_BUSINESSMODEL_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("businessModel") BusinessModelDTO businessModelDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, businessModelDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, BusinessModelDTO dto, String uri, boolean isEdit) {
        mav.setViewName("businessModel/form");
        mav.addObject("businessModel", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, BusinessModelDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("businessModel/form");
            return mav;
        }
        businessModelService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_BUSINESSMODEL);
        return mav;
    }

}
