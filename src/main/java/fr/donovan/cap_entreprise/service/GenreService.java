package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Genre;
import fr.donovan.cap_entreprise.repository.GenreRepository;
import fr.donovan.cap_entreprise.DTO.GenreDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GenreService implements DAOServiceInterface<Genre> {

    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return this.genreRepository.findAll();
    }

    public Genre getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Genre getObjectById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        return optionalGenre.orElseThrow(() -> new NotFoundCapEntrepriseException("Genre", "id", id));
    }

    public Genre getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<Genre> optionalGenre = genreRepository.findBySlug(slug);
//        return optionalGenre.orElseThrow(() -> new NotFoundCapEntrepriseException("Genre", "slug", slug));
    }

    public Genre persist(GenreDTO genreDTO) {
        return persist(genreDTO, null);
    }

    public Genre persist(GenreDTO genreDTO, Long id) {
        Genre genre = new Genre();
        if (id != null) {
            genre = getObjectById(id);
        }

        return genreRepository.saveAndFlush(genre);
    }

    public GenreDTO getDTOById(Long id) {
        Genre genre = getObjectById(id);
        GenreDTO dto = new GenreDTO();
        // dto.setName(genre.getName());
        return dto;
    }
}
