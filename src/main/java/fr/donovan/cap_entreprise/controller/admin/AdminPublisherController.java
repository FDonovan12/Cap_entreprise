package fr.donovan.cap_entreprise.controller.admin;
import fr.donovan.cap_entreprise.entity.Publisher;
import fr.donovan.cap_entreprise.DTO.PublisherDTO;
import fr.donovan.cap_entreprise.service.PublisherService;
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
public class AdminPublisherController {

    private final PublisherService publisherService;

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/publisher/index");
        mav.addObject("publishers", publisherService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_PUBLISHER + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Publisher publisher = publisherService.getByField(field);

        mav.setViewName("admin/publisher/show");
        mav.addObject("publisher", publisher);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new PublisherDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                publisherService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_NEW)
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherDTO publisherDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, publisherDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PUBLISHER_EDIT + "/{id}")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherDTO publisherDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, publisherDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, PublisherDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/publisher/form");
        mav.addObject("publisher", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, PublisherDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/publisher/form");
            return mav;
        }
        publisherService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_PUBLISHER);
        return mav;
    }

}
