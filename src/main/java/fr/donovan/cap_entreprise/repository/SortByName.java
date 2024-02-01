package fr.donovan.cap_entreprise.repository;

import java.util.List;
import java.util.Optional;

public interface SortByName<T> {

    List<T> findAllByOrderByNameAsc();

}
