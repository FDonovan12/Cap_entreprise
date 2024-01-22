package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface DAOServiceInterface<T> {

    List<T> findAll();

    T getObjectById(Long id);

}
