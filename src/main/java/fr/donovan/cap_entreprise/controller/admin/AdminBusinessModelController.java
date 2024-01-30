package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.BusinessModel;
import fr.donovan.cap_entreprise.DTO.BusinessModelDTO;
import fr.donovan.cap_entreprise.service.BusinessModelService;
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
public class AdminBusinessModelController {

    private final BusinessModelService businessModelService;

    @GetMapping(path = UrlRoute.URL_ADMIN_BUSINESSMODEL)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/businessModel/index");
        mav.addObject("businessModels", businessModelService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_BUSINESSMODEL + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        BusinessModel businessModel = businessModelService.getByField(field);

        mav.setViewName("admin/businessModel/show");
        mav.addObject("businessModel", businessModel);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_BUSINESSMODEL_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new BusinessModelDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_BUSINESSMODEL_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                businessModelService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_BUSINESSMODEL_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("businessModel") BusinessModelDTO businessModelDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, businessModelDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_BUSINESSMODEL_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("businessModel") BusinessModelDTO businessModelDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, businessModelDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, BusinessModelDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/businessModel/form");
        mav.addObject("businessModel", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, BusinessModelDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/businessModel/form");
            return mav;
        }
        businessModelService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_BUSINESSMODEL);
        return mav;
    }

}
