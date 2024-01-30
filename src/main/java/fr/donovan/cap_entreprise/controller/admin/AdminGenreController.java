package fr.donovan.cap_entreprise.controller.admin;

import fr.donovan.cap_entreprise.entity.Genre;
import fr.donovan.cap_entreprise.DTO.GenreDTO;
import fr.donovan.cap_entreprise.service.GenreService;
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
public class AdminGenreController {

    private final GenreService genreService;

    @GetMapping(path = UrlRoute.URL_ADMIN_GENRE)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("admin/genre/index");
        mav.addObject("genres", genreService.findAll());
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_ADMIN_GENRE + "/{field}")
    public ModelAndView show(ModelAndView mav, @PathVariable String field) {
        Genre genre = genreService.getByField(field);

        mav.setViewName("admin/genre/show");
        mav.addObject("genre", genre);
        return mav;
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GENRE_NEW)
    public ModelAndView create(ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                new GenreDTO(),
                httpServletRequest.getRequestURI(),
                false
        );
    }

    @GetMapping(path = UrlRoute.URL_ADMIN_GENRE_EDIT + "/{id}")
    public ModelAndView edit(@PathVariable Long id, ModelAndView mav, HttpServletRequest httpServletRequest) {
        return getFormByDTO(
                mav,
                genreService.getDTOById(id),
                httpServletRequest.getRequestURI(),
                true
        );
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GENRE_NEW)
    public ModelAndView formHandler(
            @Valid @ModelAttribute("genre") GenreDTO genreDTO,
            BindingResult result,
            ModelAndView mav
    ) {
        return formHandle(result, mav, genreDTO, null);
    }

    @PostMapping(path = UrlRoute.URL_ADMIN_GENRE_EDIT + "/{id}")
    public ModelAndView formHandler(
            @Valid @ModelAttribute("genre") GenreDTO genreDTO,
            BindingResult result,
            ModelAndView mav,
            @PathVariable Long id
    ) {
        return formHandle(result, mav, genreDTO, id);
    }

    private ModelAndView getFormByDTO(ModelAndView mav, GenreDTO dto, String uri, boolean isEdit) {
        mav.setViewName("admin/genre/form");
        mav.addObject("genre", dto);
        mav.addObject("action", uri);
        mav.addObject("isEdit", isEdit);
        return mav;
    }

    private ModelAndView formHandle(BindingResult result, ModelAndView mav, GenreDTO dto, Long id) {
        if (result.hasErrors()) {
            mav.setViewName("admin/genre/form");
            return mav;
        }
        genreService.persist(dto, id);
        mav.setViewName("redirect:" + UrlRoute.URL_ADMIN_GENRE);
        return mav;
    }

}
