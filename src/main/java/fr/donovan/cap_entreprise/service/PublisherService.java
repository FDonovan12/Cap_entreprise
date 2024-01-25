package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Publisher;
import fr.donovan.cap_entreprise.repository.PublisherRepository;
import fr.donovan.cap_entreprise.DTO.PublisherDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublisherService implements DAOServiceInterface<Publisher> {

    private PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return this.publisherRepository.findAll();
    }

    public Publisher getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Publisher getObjectById(Long id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        return optionalPublisher.orElseThrow(() -> new NotFoundCapEntrepriseException("Publisher", "id", id));
    }

    public Publisher getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<Publisher> optionalPublisher = publisherRepository.findBySlug(slug);
//        return optionalPublisher.orElseThrow(() -> new NotFoundCapEntrepriseException("Publisher", "slug", slug));
    }

    public Publisher persist(PublisherDTO publisherDTO) {
        return persist(publisherDTO, null);
    }

    public Publisher persist(PublisherDTO publisherDTO, Long id) {
        Publisher publisher = new Publisher();
        if (id != null) {
            publisher = getObjectById(id);
        }

        return publisherRepository.saveAndFlush(publisher);
    }

    public PublisherDTO getDTOById(Long id) {
        Publisher publisher = getObjectById(id);
        PublisherDTO dto = new PublisherDTO();
        // dto.setName(publisher.getName());
        return dto;
    }
}
