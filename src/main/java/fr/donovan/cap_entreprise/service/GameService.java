package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Game;
import fr.donovan.cap_entreprise.repository.GameRepository;
import fr.donovan.cap_entreprise.DTO.GameDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

        return gameRepository.saveAndFlush(game);
    }

    public GameDTO getDTOById(Long id) {
        Game game = getObjectById(id);
        GameDTO dto = new GameDTO();
        // dto.setName(game.getName());
        return dto;
    }
}
