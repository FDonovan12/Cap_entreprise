package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.*;
import fr.donovan.cap_entreprise.repository.GameRepository;
import fr.donovan.cap_entreprise.DTO.GameDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static fr.donovan.cap_entreprise.mapping.UrlRoute.*;

@AllArgsConstructor
@Service
public class GameService implements DAOServiceInterface<Game> {

    private GameRepository gameRepository;


    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    public List<Game> findAllSorted() {
        return this.gameRepository.findAllByOrderByNameAsc();
    }

    public Page<Game> findAll(Pageable pageable) {
        return this.gameRepository.findAll(pageable);
    }


    public Page<Game> getGamesByObject(Object object, Pageable pageable) {
        if (object instanceof Genre cast) {
            return gameRepository.getByGenre(cast, pageable);
        }
        if (object instanceof Classification cast) {
            return gameRepository.getByClassification(cast, pageable);
        }
        if (object instanceof Platform cast) {
            return gameRepository.getByPlatformsContains(cast, pageable);
        }
        if (object instanceof Publisher cast) {
            return gameRepository.getByPublisher(cast, pageable);
        }
        if (object instanceof BusinessModel cast) {
            return gameRepository.getByBusinessModel(cast, pageable);
        }
        throw new NotFoundCapEntrepriseException("Game", "getGamesByObject", object);
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
        game.setImage("https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg");
        if (id != null) {
            game = getObjectById(id);
        }
        game.setName(gameDTO.getName());
        game.setDescription(gameDTO.getDescription());
        game.setPublishedAt(LocalDate.parse(gameDTO.getPublishedAt()));
//        game.setImage(gameDTO.getImage());

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

    public String upload(String path, MultipartFile file) {
        try {
            File dir = new File(DIR_PATH + PATH_IMAGE + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            return PATH_IMAGE + path + file.getOriginalFilename();
        } catch (IOException e) {
            return "Error : Something goes wrong...";
        }
    }

    public void addImage(String image, Long id) {
        Game game = getObjectById(id);
        game.setImage(image);
        gameRepository.saveAndFlush(game);
    }
}
