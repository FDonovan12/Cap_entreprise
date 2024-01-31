package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.Platform;
import fr.donovan.cap_entreprise.DTO.PlatformDTO;
import fr.donovan.cap_entreprise.service.GameService;
import fr.donovan.cap_entreprise.service.PlatformService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
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
public class PlatformController {

    private final PlatformService platformService;

    private final GameService gameService;

    @GetMapping(path = UrlRoute.URL_PLATFORM)
    public ModelAndView index(ModelAndView mav, @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "id" }, // order by
                                                direction = Sort.Direction.DESC)
                                                Pageable pageable) {
        mav.setViewName("platform/index");
        mav.addObject("platforms", platformService.findAll(pageable));
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_PLATFORM + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field,
                                                @PageableDefault(
                                                size = 6, // nb Element par page
                                                sort = { "id" }, // order by
                                                direction = Sort.Direction.DESC)
                                                Pageable pageable) {
        Platform platform = platformService.getByField(field);

        mav.setViewName("platform/show");
        mav.addObject("platform", platform);
        mav.addObject("games", gameService.getGamesByObject(platform, pageable));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_PLATFORM_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new PlatformDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_PLATFORM_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                platformService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_PLATFORM_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, platformDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_PLATFORM_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("platform") PlatformDTO platformDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, platformDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, PlatformDTO dto, String uri, boolean isEdit) {
        mav.setViewName("platform/form");
        mav.addObject("platform", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, PlatformDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("platform/form");
            return mav;
        }
        platformService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_PLATFORM);
        return mav;
    }

}
