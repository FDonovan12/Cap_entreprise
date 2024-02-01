package fr.donovan.cap_entreprise.repository;

import fr.donovan.cap_entreprise.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, SortByName<Game>  {


    Page<Game> getByGenre(Genre genre, Pageable pageable);

    Page<Game> getByClassification(Classification classification, Pageable pageable);

    Page<Game> getByPublisher(Publisher publisher, Pageable pageable);

    Page<Game> getByPlatformsContains(Platform platform, Pageable pageable);

    Page<Game> getByBusinessModel(BusinessModel businessModel, Pageable pageable);
}