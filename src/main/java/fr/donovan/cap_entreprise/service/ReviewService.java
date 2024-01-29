package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.*;
import fr.donovan.cap_entreprise.repository.ReviewRepository;
import fr.donovan.cap_entreprise.DTO.ReviewDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewService implements DAOServiceInterface<Review> {

    private ReviewRepository reviewRepository;

    private GameService gameService;

    private UserService userService;

    public List<Review> findAll() {
        return this.reviewRepository.findAll();
    }

    public Page<Review> findAll(User user, Pageable pageable) {
        System.out.println("pageable = " + pageable);
        if (user instanceof Gamer) {
            return reviewRepository.findByModeratorIsNotNullOrGamer(user, pageable);
        }
        return  reviewRepository.findAll(pageable);
    }

    public double getRatingOfGame(Game game) {
        return reviewRepository.getRatingOfGame(game);
    }

    public List<Double> getRatingOfGames(List<Game> games) {
        return reviewRepository.getRatingOfGames(games);
    }

    public Review getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Review getObjectById(Long id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.orElseThrow(() -> new NotFoundCapEntrepriseException("Review", "id", id));
    }

    public Review getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<Review> optionalReview = reviewRepository.findBySlug(slug);
//        return optionalReview.orElseThrow(() -> new NotFoundCapEntrepriseException("Review", "slug", slug));
    }

    public Review persist(ReviewDTO reviewDTO) {
        return persist(reviewDTO, null);
    }

    public Review persist(ReviewDTO reviewDTO, Long id) {
        Review review = new Review();
        if (id != null) {
            review = getObjectById(id);
        }
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());
        review.setGame(reviewDTO.getGame());
        review.setGamer(reviewDTO.getUser());

        return reviewRepository.saveAndFlush(review);
    }

    public ReviewDTO getDTOById(Long id) {
        Review review = getObjectById(id);
        ReviewDTO dto = new ReviewDTO();
        // dto.setName(review.getName());
        return dto;
    }

    public void delete(long id) {
        reviewRepository.delete(getObjectById(id));
    }

    public Review validate(long id, String name) {
        Review review = getObjectById(id);
        Moderator moderator = (Moderator) userService.getObjectByNickname(name);
        review.setModerator(moderator);
        return reviewRepository.saveAndFlush(review);
    }
}
