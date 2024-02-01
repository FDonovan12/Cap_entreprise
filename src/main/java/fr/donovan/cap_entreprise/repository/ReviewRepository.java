package fr.donovan.cap_entreprise.repository;

import fr.donovan.cap_entreprise.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByModeratorIsNotNullOrGamer(User gamer, Pageable pageable);

    Page<Review> findByGamer(User gamer, Pageable pageable);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE ?1 = r.game and r.moderator is not null GROUP BY r.game")
    Double getRatingByObject(Game game);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.game IN(?1) and r.moderator is not null GROUP BY r.game")
    List<Double> getRatingOfGames(List<Game> games);

    @Query("SELECT AVG(r.rating) FROM Review r JOIN Game g ON g = r.game WHERE g.classification = ?1 and r.moderator is not null GROUP BY g.classification")
    Double getRatingByObject(Classification classification);

    @Query("SELECT AVG(r.rating) FROM Review r JOIN Game g ON g = r.game WHERE g.businessModel = ?1 and r.moderator is not null GROUP BY g.businessModel")
    Double getRatingByObject(BusinessModel businessModel);

    @Query("SELECT AVG(r.rating) FROM Review r JOIN Game g ON g = r.game WHERE g.genre = ?1 and r.moderator is not null GROUP BY g.genre")
    Double getRatingByObject(Genre genre);

    @Query("SELECT AVG(r.rating) FROM Review r JOIN Game g ON g = r.game JOIN g.platforms p WHERE ?1 = p and r.moderator is not null GROUP BY p")
    Double getRatingByObject(Platform platform);

    @Query("SELECT AVG(r.rating) FROM Review r JOIN Game g ON g = r.game WHERE g.publisher = ?1 and r.moderator is not null GROUP BY g.publisher")
    Double getRatingByObject(Publisher publisher);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.gamer = ?1 and r.moderator is not null GROUP BY r.gamer")
    Double getRatingByObject(User user);

    Page<Review> findByGame(Game game, Pageable pageable);
}