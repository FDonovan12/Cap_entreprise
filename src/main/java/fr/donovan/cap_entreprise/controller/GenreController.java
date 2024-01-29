package fr.donovan.cap_entreprise.controller;

import fr.donovan.cap_entreprise.entity.Genre;
import fr.donovan.cap_entreprise.DTO.GenreDTO;
import fr.donovan.cap_entreprise.service.GenreService;
import fr.donovan.cap_entreprise.mapping.UrlRoute;
import fr.donovan.cap_entreprise.service.ReviewService;
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
public class GenreController {

    private final GenreService genreService;

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_GENRE)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("genre/index");
        mav.addObject("genres", genreService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_GENRE + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Genre genre = genreService.getByField(field);

        mav.setViewName("genre/show");
        mav.addObject("genre", genre);
        mav.addObject("games_rating", reviewService.getRatingOfGames(genre.getGames()));
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_GENRE_NEW)
    public ModelAndView create(
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                new GenreDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_GENRE_EDIT + "/{id}")
    public ModelAndView edit(
            @PathVariable Long id,
            ModelAndView mav,
            HttpServletRequest httpServletRequest
    ) {
        return getFormByDTO(
                mav,
                genreService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_GENRE_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("genre") GenreDTO genreDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, genreDTO, null);
    }

    @PutMapping(path = UrlRoute.URL_GENRE_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("genre") GenreDTO genreDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, genreDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, GenreDTO dto, String uri, boolean isEdit) {
        mav.setViewName("genre/form");
        mav.addObject("genre", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, GenreDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("genre/form");
            return mav;
        }
        genreService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_GENRE);
        return mav;
    }

}
