package fr.donovan.cap_entreprise.configuration;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import fr.donovan.cap_entreprise.entity.*;
import fr.donovan.cap_entreprise.repository.*;
import fr.donovan.cap_entreprise.service.*;
import fr.donovan.cap_entreprise.utils.SearchImage;
import fr.donovan.cap_entreprise.utils.Slugger;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;

    private GameRepository gameRepository;

    private PublisherRepository publisherRepository;

    private ClassificationRepository classificationRepository;

    private GenreRepository genreRepository;

    private BusinessModelRepository businessModelRepository;

    private PlatformRepository platformRepository;

    private ReviewRepository reviewRepository;

    private PublisherService publisherService;

    private ClassificationService classificationService;

    private GenreService genreService;

    private BusinessModelService businessModelService;

    private PlatformService platformService;

    private UserService userService;

    private GameService gameService;

    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws IOException {

        runPublisher(List.of("Valve", "Riot Games", "Blizzard Entertainment"));

        runClassification(List.of("Pegi 7", "Pegi 12", "Pegi 16"));

        runGenre(List.of("FPS", "MOBA", "Carte"));

        runBusinessModels(List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win"));

        runPlatform(List.of("PlayStation 5", "X-Box Series X", "Personal Computer"));

        runUser(List.of("alice", "bob", "chloe"));

        runGames(List.of("Counter Strike", "Dota 2", "Artifact",
                "Valorant", "League of Legends", "Legends of Runeterra",
                "Overwatch", "Heroes of the Storm", "Hearthstone"));

        runReview(List.of("Vive les 360 no scope", "J'aime bien storm spirit", "Artifact",
                "Valorant", "Tahm kench a les meilleur dialogue du jeu", "Legends of Runeterra",
                "J'aime pas trop la goat meta", "Heroes of the Storm", "Trop bien les petite cartes"));

    }

    private void runReview(List<String> reviews) {
        reviews.forEach((string) -> {
            long i = (long) reviews.indexOf(string);
            Review review = new Review();
            review.setId(i + 1);
            review.setDescription(string);
            review.setRating(i*2);
            review.setGame(gameService.getObjectById(i + 1));
            review.setGamer(userService.getObjectById(i%3+1));
            review.setCreatedAt(LocalDateTime.now());
            if (i%3 == (i)/3) {
                User user = userService.getObjectById(3L);
                review.setModerator((Moderator) user);
            }
            reviewRepository.save(review);
        });
        reviewRepository.flush();
    }

    private void runGames(List<String> games) {
        games.forEach((string) -> {
            long id = (long) games.indexOf(string);
            Game game = new Game();
            game.setId(id+1);
            game.setName(string);
            game.setPublishedAt(LocalDate.now());
            game.setImage("image de " + string);
            game.setClassification(classificationService.getObjectById(id%3+1));
            game.setGenre(genreService.getObjectById(id%3+1));
            game.setPublisher(publisherService.getObjectById((id-1)/3+1));
            game.setBusinessModel(businessModelService.getObjectById(id%3+1));
            game.setPlatforms(platformService.findAll());
            game.setModerator((Moderator) userService.getObjectById(3L));

            game.setDescription(string + " est un jeu de type " + game.getGenre().getName());
            gameRepository.save(game);
        });
        gameRepository.flush();
    }

    private void runPublisher(List<String> publishers) {
        publishers.forEach((string) -> {
            Publisher publisher = new Publisher();
            publisher.setId((long) publishers.indexOf(string) + 1);
            publisher.setName(string);
            publisherRepository.save(publisher);
        });
        publisherRepository.flush();
    }

    private void runClassification(List<String> classifications) {
        classifications.forEach((string) -> {
            Classification classification = new Classification();
            classification.setId((long) classifications.indexOf(string) + 1);
            classification.setName(string);
            classificationRepository.save(classification);
        });
        classificationRepository.flush();
    }

    private void runGenre(List<String> genres) {
        genres.forEach((string) -> {
            Genre genre = new Genre();
            genre.setId((long) genres.indexOf(string) + 1);
            genre.setName(string);
            genreRepository.save(genre);
        });
        genreRepository.flush();
    }

    private void runBusinessModels(List<String> businessModels) {
        businessModels.forEach((string) -> {
            BusinessModel businessModel = new BusinessModel();
            businessModel.setId((long) businessModels.indexOf(string) + 1);
            businessModel.setName(string);
            businessModelRepository.save(businessModel);
        });
        businessModelRepository.flush();
    }

    private void runPlatform(List<String> platforms) {
        platforms.forEach((string) -> {
            Platform platform = new Platform();
            platform.setId((long) platforms.indexOf(string) + 1);
            platform.setName(string);
            platformRepository.save(platform);
        });
        platformRepository.flush();
    }

    private void runUser(List<String> users) {
        Faker faker = new Faker(Locale.of("fr"));
        users.forEach((string) -> {
            User user = new Gamer();
            if ((long) users.indexOf(string) + 1 == users.size()) {
                user = new Moderator();
                ((Moderator) user).setPhoneNumber(faker.phoneNumber().cellPhone());
            } else {
                user = new Gamer();
                ((Gamer) user).setBirthAt(LocalDate.ofInstant(faker.date().birthday(12,70).toInstant(), ZoneId.systemDefault()));
            }
            user.setId((long) users.indexOf(string) + 1);
            user.setNickname(string);
//            System.out.println("genre = " + faker.book().genre());
//            System.out.println("publisher = " + faker.book().publisher());
            user.setEmail(string + "@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user);
        });
        userRepository.flush();
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i<5000; i++) {
            set.add(faker.yoda().quote());
        }
//        set.forEach(System.out::println);
        System.out.println(set.size());
    }
}