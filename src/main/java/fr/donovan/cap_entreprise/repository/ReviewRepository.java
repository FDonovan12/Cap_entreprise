package fr.donovan.cap_entreprise.repository;

import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public Page<Review> findByModeratorIsNotNullOrGamerOrderByModerator(User gamer, Pageable pageable);

}