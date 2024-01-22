package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.Game;
import fr.donovan.cap_entreprise.DTO.GameDTO;
import fr.donovan.cap_entreprise.service.GameService;
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
public class AdminGameController {

    private final GameService gameService;

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/game/index");
        mav.addObject("games", gameService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_GAME + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Game game = gameService.getByField(field);

        mav.setViewName("admin/game/show");
        mav.addObject("game", game);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new GameDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                gameService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GAME_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("game") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, gameDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_ADMIN_GAME_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("game") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, gameDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, GameDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/game/form");
        mav.addObject("game", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, GameDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/game/form");
            return mav;
        }
        gameService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_GAME);
        return mav;
    }

}
