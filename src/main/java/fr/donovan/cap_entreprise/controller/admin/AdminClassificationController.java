package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.Classification;
import fr.donovan.cap_entreprise.DTO.ClassificationDTO;
import fr.donovan.cap_entreprise.service.ClassificationService;
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
public class AdminClassificationController {

    private final ClassificationService classificationService;

    @GetMapping(path = UrlRoute.URL_ADMIN_CLASSIFICATION)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/classification/index");
        mav.addObject("classifications", classificationService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_CLASSIFICATION + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Classification classification = classificationService.getByField(field);

        mav.setViewName("admin/classification/show");
        mav.addObject("classification", classification);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CLASSIFICATION_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new ClassificationDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_CLASSIFICATION_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                classificationService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_CLASSIFICATION_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("classification") ClassificationDTO classificationDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, classificationDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_ADMIN_CLASSIFICATION_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("classification") ClassificationDTO classificationDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, classificationDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, ClassificationDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/classification/form");
        mav.addObject("classification", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, ClassificationDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/classification/form");
            return mav;
        }
        classificationService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_CLASSIFICATION);
        return mav;
    }

}
