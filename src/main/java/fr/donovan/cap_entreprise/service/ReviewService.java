package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.repository.ReviewRepository;
import fr.donovan.cap_entreprise.DTO.ReviewDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
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
        review.setGame(gameService.getObjectById(reviewDTO.getGame_id()));
        review.setGamer(userService.getObjectById(reviewDTO.getGamer_id()));

        return reviewRepository.saveAndFlush(review);
    }

    public ReviewDTO getDTOById(Long id) {
        Review review = getObjectById(id);
        ReviewDTO dto = new ReviewDTO();
        // dto.setName(review.getName());
        return dto;
    }
}
