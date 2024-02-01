package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Genre;
import fr.donovan.cap_entreprise.entity.Platform;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.repository.GenreRepository;
import fr.donovan.cap_entreprise.DTO.GenreDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<Genre> findAllSorted() {
        return this.genreRepository.findAllByOrderByNameAsc();
    }

    public Page<Genre> findAll(Pageable pageable) {
        return this.genreRepository.findAll(pageable);
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
        genre.setImage("https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg");
        if (id != null) {
            genre = getObjectById(id);
        }
        genre.setName(genreDTO.getName());

        return genreRepository.saveAndFlush(genre);
    }

    public GenreDTO getDTOById(Long id) {
        Genre genre = getObjectById(id);
        GenreDTO dto = new GenreDTO();
        dto.setName(genre.getName());
        return dto;
    }
}
