package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.Game;
import fr.donovan.cap_entreprise.DTO.GameDTO;
import fr.donovan.cap_entreprise.entity.Moderator;
import fr.donovan.cap_entreprise.service.*;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private final GenreService genreService;

    private final ClassificationService classificationService;

    private final PlatformService platformService;

    private final PublisherService publisherService;

    private final UserService userService;

    private final BusinessModelService businessModelService;

    @GetMapping(path = UrlRoute.URL_GAME)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("game/index");
        mav.addObject("games", gameService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_GAME + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Game game = gameService.getByField(field);

        mav.setViewName("game/show");
        mav.addObject("game", game);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GAME_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new GameDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_GAME_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                gameService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_GAME_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("game") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav,
            Principal principal
    ) {
        return formHandle(result, mav, gameDTO, null, principal);
    }

    @PutMapping(path = UrlRoute.URL_GAME_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("game") GameDTO gameDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id,
            Principal principal
    ) {
        return formHandle(result, mav, gameDTO, id, principal);
    }

    @GetMapping(path = UrlRoute.URL_GAME_DELETE + "/{id}")
    public ModelAndView deleteGame(ModelAndView mav, @PathVariable("id") long id) {
        gameService.delete(id);
        return new ModelAndView("redirect:" + UrlRoute.URL_GAME);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, GameDTO dto, String uri, boolean isEdit) {
        mav.setViewName("game/form");
        mav.addObject("game", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        mav.addObject("genres", genreService.findAll());
        mav.addObject("classifications", classificationService.findAll());
        mav.addObject("publishers", publisherService.findAll());
//        mav.addObject("moderators", userService.findAll());
        mav.addObject("platforms", platformService.findAll());
        mav.addObject("businessModels", businessModelService.findAll());
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, GameDTO dto, Long id, Principal principal) {
        System.out.println("GameController.formHandle");
        if (result.hasErrors()) {
            System.out.println("result = " + result);
            mav.setViewName("game/form");
            return mav;
        }
        dto.setModerator((Moderator) userService.getObjectByNickname(principal.getName()));
        System.out.println("result = " + result);
        gameService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_GAME);
        return mav;
    }

}
