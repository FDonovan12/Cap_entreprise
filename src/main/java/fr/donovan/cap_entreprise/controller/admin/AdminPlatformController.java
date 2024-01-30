package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.Platform;
import fr.donovan.cap_entreprise.DTO.PlatformDTO;
import fr.donovan.cap_entreprise.service.PlatformService;
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
public class AdminPlatformController {

    private final PlatformService platformService;

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/platform/index");
        mav.addObject("platforms", platformService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_PLATFORM + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Platform platform = platformService.getByField(field);

        mav.setViewName("admin/platform/show");
        mav.addObject("platform", platform);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new PlatformDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_PLATFORM_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                platformService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PLATFORM_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_PLATFORM_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, platformDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, PlatformDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/platform/form");
        mav.addObject("platform", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, PlatformDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/platform/form");
            return mav;
        }
        platformService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_PLATFORM);
        return mav;
    }

}
