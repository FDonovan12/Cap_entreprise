package fr.donovan.cap_entreprise.repository;

import fr.donovan.cap_entreprise.entity.Game;
import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public Page<Review> findByModeratorIsNotNullOrGamer(User gamer, Pageable pageable);


    @Query("SELECT r.game, AVG(r.rating) FROM Review r WHERE ?1 = r.game GROUP BY r.game")
    double getRatingOfGame(Game game);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.game IN(?1) GROUP BY r.game")
    List<Double> getRatingOfGames(List<Game> games);

}