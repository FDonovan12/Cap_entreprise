package fr.donovan.cap_entreprise.repository;

import fr.donovan.cap_entreprise.entity.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessModelRepository extends JpaRepository<BusinessModel, Long> {


}