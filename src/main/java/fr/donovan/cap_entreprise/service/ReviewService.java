package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.*;
import fr.donovan.cap_entreprise.repository.ReviewRepository;
import fr.donovan.cap_entreprise.DTO.ReviewDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import jakarta.transaction.NotSupportedException;
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

    public List<Review> findByGamer(User gamer) {
        return this.reviewRepository.findByGamer(gamer);
    }

    public Page<Review> findAll(User user, Pageable pageable) {
        System.out.println("pageable = " + pageable);
        if (user instanceof Gamer) {
            return reviewRepository.findByModeratorIsNotNullOrGamer(user, pageable);
        }
        return  reviewRepository.findAll(pageable);
    }

    public List<Double> getRatingOfGames(List<Game> games) {
        return reviewRepository.getRatingOfGames(games);
    }

    public Object getByGame(Game game, Pageable pageable) {
        return reviewRepository.findByGame(game, pageable);
    }

    public Double getRatingByObject(Object object) {
        if (object instanceof Genre cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof Publisher cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof Platform cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof Classification cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof BusinessModel cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof Game cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        if (object instanceof User cast) {
            return reviewRepository.getRatingByObject(cast);
        }
        throw new NotFoundCapEntrepriseException("Review", "rating", object);
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
