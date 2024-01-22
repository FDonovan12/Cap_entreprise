package fr.donovan.cap_entreprise.controller;
import fr.donovan.cap_entreprise.entity.Publisher;
import fr.donovan.cap_entreprise.DTO.PublisherDTO;
import fr.donovan.cap_entreprise.service.PublisherService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping(path = UrlRoute.URL_PUBLISHER)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("publisher/index");
        mav.addObject("publishers", publisherService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_PUBLISHER + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Publisher publisher = publisherService.getByField(field);

        mav.setViewName("publisher/show");
        mav.addObject("publisher", publisher);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_PUBLISHER_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PublisherDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_PUBLISHER_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                publisherService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_PUBLISHER_NEW)
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherDTO publisherDTO,
        BindingResult result,
        ModelAndView mav
    ) {
        return formHandle(result, mav, publisherDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_PUBLISHER_EDIT + "/{id}")
    public ModelAndView formHandler(
        @Valid @ModelAttribute("publisher") PublisherDTO publisherDTO,
        BindingResult result,
        ModelAndView mav,
        @PathVariable Long id
    ) {
        return formHandle(result, mav, publisherDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, PublisherDTO dto, String uri, boolean isEdit) {
        mav.setViewName("publisher/form");
        mav.addObject("publisher", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, PublisherDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("publisher/form");
            return mav;
        }
        publisherService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_PUBLISHER);
        return mav;
    }

}