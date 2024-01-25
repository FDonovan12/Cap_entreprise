package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Game;
import fr.donovan.cap_entreprise.repository.GameRepository;
import fr.donovan.cap_entreprise.DTO.GameDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GameService implements DAOServiceInterface<Game> {

    private GameRepository gameRepository;

    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    public Game getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Game getObjectById(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        return optionalGame.orElseThrow(() -> new NotFoundCapEntrepriseException("Game", "id", id));
    }

    public Game getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<Game> optionalGame = gameRepository.findBySlug(slug);
//        return optionalGame.orElseThrow(() -> new NotFoundCapEntrepriseException("Game", "slug", slug));
    }

    public Game persist(GameDTO gameDTO) {
        return persist(gameDTO, null);
    }

    public Game persist(GameDTO gameDTO, Long id) {
        Game game = new Game();
        if (id != null) {
            game = getObjectById(id);
        }
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setPublishedAt(LocalDate.parse(gameDTO.getPublishedAt()));
        game.setImage(gameDTO.getImage());

        game.setPublisher(gameDTO.getPublisher());
        game.setGenre(gameDTO.getGenre());
        game.setClassification(gameDTO.getClassification());
        game.setModerator(gameDTO.getModerator());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setBusinessModel(gameDTO.getBusinessModel());

        return gameRepository.saveAndFlush(game);
    }

    public GameDTO getDTOById(Long id) {
        Game game = getObjectById(id);
        GameDTO gameDTO = new GameDTO();
        // dto.setName(game.getName());
        gameDTO.setName(game.getName());
        gameDTO.setDescription(game.getDescription());
        gameDTO.setPublishedAt(game.getPublishedAt() + "");
        gameDTO.setImage(game.getImage());

        gameDTO.setPublisher(game.getPublisher());
        gameDTO.setGenre(game.getGenre());
        gameDTO.setClassification(game.getClassification());
        gameDTO.setModerator(game.getModerator());
        gameDTO.setPlatforms(game.getPlatforms());
        gameDTO.setBusinessModel(game.getBusinessModel());
        return gameDTO;
    }

    public void delete(long id) {
        gameRepository.delete(getObjectById(id));
    }
}
