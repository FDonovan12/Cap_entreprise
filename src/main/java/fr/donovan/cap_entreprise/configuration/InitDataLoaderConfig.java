package fr.donovan.cap_entreprise.configuration;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import fr.donovan.cap_entreprise.entity.*;
import fr.donovan.cap_entreprise.entity.interfaces.NomenclatureInterface;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
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
//        initKevin();
        System.out.println("InitDataLoaderConfig.run");
    }

    public void initDonovan() {
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

    public void initKevin() {
        createUsers();
        createBusinessModels();
        createPlatforms();
        createPublishers();
        createClassifications();
        createGenres();
        userRepository.flush();
        createGames();
        gameRepository.flush();
        createReview();
        reviewRepository.flush();
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





    private void createUsers() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> names = new ArrayList<>();
        for (long i = 1L; i <= 200; i++) {
            if (userRepository.findById(i).isEmpty()) {
                User user;
                if (i <= 5) {
                    user = new Moderator();
                    ((Moderator) user).setPhoneNumber(faker.phoneNumber().cellPhone());
                } else {
                    user = new Gamer();
                    LocalDate localDate = new java.sql.Date(faker.date().birthday(12, 60).getTime()).toLocalDate();
                    ((Gamer) user).setBirthAt(localDate);
                }
                user.setId(i);
                String name;
                do {
                    name = Slugger.slugify(faker.funnyName().name().replace(" ", ""));
                    if(i == 1) {
                        name = "chloe";
                    }
                    if(i == 6) {
                        name = "bob";
                    }
                } while (names.contains(name));
                names.add(name);
                user.setNickname(name);
                user.setEmail(name + "@gmail.com");
                user.setPassword(passwordEncoder.encode("12345"));
                userRepository.save(user);
            }
        }
    }

    private void createBusinessModels() {
        createNomenclatures(
                businessModelRepository,
                BusinessModel.class,
                List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win")
        );
    }

    private void createPlatforms() {
        createNomenclatures(
                platformRepository,
                Platform.class,
                List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One")
        );
    }

    private void createPublishers() {
        createNomenclatures(
                publisherRepository,
                Publisher.class,
                List.of("Blizzard Entertainment", "Valve", "Riot Games", "Mojang", "Rockstar", "CD Projekt Red", "EA", "2k Games", "Ubisoft", "From Software", "Game Freak", "Nintendo", "Capcom")
        );
    }

    private void createGames() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> games = List.of("World of Warcraft", "Overwatch", "Diablo IV", "StarCraft II", "Warcraft III : reforged", "DotA 2", "Counter Strike 2", "Portal 2", "La League des Légendes", "Valorant", "Minecraft", "GTA V", "The witcher III", "Cyberpunk 2077", "Battlefield V", "Anno 1800", "Elden Ring", "Pokémon : Violet", "Pokémon : Ecarlate", "Zelda : Tears of the Kingdom", "Monster Hunter : World");
        List<Long> businessModels = List.of(2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 3L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<Long> publishers = List.of(1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 4L, 5L, 6L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L);
        List<Long> genres = List.of(16L, 1L, 7L, 15L, 15L, 2L, 1L, 6L, 2L, 1L, 14L, 14L, 4L, 6L, 1L, 15L, 4L, 4L, 4L, 4L, 10L);
        List<Long> platforms = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 2L);
        List<Long> platformsSecond = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 2L);
        List<String> images = List.of("https://cdn.thegamesdb.net/images/thumb/boxart/front/149-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/115193-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/803-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/2474-1.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/914-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/928-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/72904-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/50424-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg", "https://calimacil.com/cdn/shop/files/Geralt-calimacil-larp-replica-banner-mobile.jpg?v=1695734545&width=1500", "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/55756-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/64422-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104566-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104565-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104362-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/60572-1.jpg");
        for (int i = 0; i < games.size(); i++) {
            Long id = (long) (i + 1);
            if (gameRepository.findById(id).isEmpty()) {
                Game game = new Game();
                game.setId(id);
                game.setName(games.get(i));
                game.setDescription("<h2>" + faker.yoda().quote() + "</h2></br>" + faker.lorem().paragraph(8));
                game.setImage(images.get(i));
                LocalDate localDate = new java.sql.Date(faker.date().birthday(2, 25).getTime()).toLocalDate();
                game.setPublishedAt(localDate);
                Random rand = new Random();
                game.setModerator((Moderator) userService.getObjectById(rand.nextLong(6 - 1) + 1));
                game.setBusinessModel(businessModelService.getObjectById(businessModels.get(i)));
                game.setClassification(classificationService.getObjectById(rand.nextLong(4 - 1) + 1));
                game.setPublisher(publisherService.getObjectById(publishers.get(i)));
                game.setGenre(genreService.getObjectById(genres.get(i)));
                game.addPlatform(platformService.getObjectById(platforms.get(i)));
                game.addPlatform(platformService.getObjectById(platformsSecond.get(i)+1));
                gameRepository.save(game);
            }
        }
    }

    private void createReview() {
        Faker faker = new Faker(Locale.of("fr"));
        for (long i = 1; i <= 500; i++) {
            if (reviewRepository.findById(i).isEmpty()) {
                Review review = new Review();
                review.setId(i);
                Random rand = new Random();
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
                review.setCreatedAt(localDate);
                review.setGamer((Gamer) userService.getObjectById(rand.nextLong(201 - 6) + 6));
                review.setGame(gameService.getObjectById(rand.nextLong(22 - 1) + 1));
                float rating = (float) rand.nextLong(21 - 2) + 2;
                if (review.getGame().getId().equals(9L)) {
                    rating = (float) rand.nextLong(3);
                } else if (review.getGame().getId().equals(6L) || review.getGame().getId().equals(1L)) {
                    rating = (float) rand.nextLong(21 - 10) + 10;
                }
                review.setRating(rating);
                review.setDescription("<strong>" + faker.chuckNorris().fact() + "</strong></br></br>" + faker.lorem().paragraph(3));
                if (i%5 != 0) {
                    review.setModerator((Moderator) userService.getObjectById(rand.nextLong(6 - 1) + 1));
                    review.setModeratedAt(LocalDateTime.now());
                }
                reviewRepository.save(review);
            }
        }
    }

    private void createClassifications() {
        createNomenclatures(
                classificationRepository,
                Classification.class,
                List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18")
        );
    }

    private void createGenres() {
        createNomenclatures(
                genreRepository,
                Genre.class,
                List.of("FPS", "MOBA", "MMO", "RPG", "Voiture", "Aventure", "Hack'n'Slash", "Simulation", "Sport", "Action", "Horreur", "Plateforme", "Cartes", "Monde ouvert", "Stratégie", "MMO RPG")
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items
    ) {
        items.forEach((name) -> {
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}