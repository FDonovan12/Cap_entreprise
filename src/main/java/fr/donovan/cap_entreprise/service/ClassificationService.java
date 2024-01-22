package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Classification;
import fr.donovan.cap_entreprise.repository.ClassificationRepository;
import fr.donovan.cap_entreprise.DTO.ClassificationDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClassificationService implements DAOServiceInterface<Classification> {

    private ClassificationRepository classificationRepository;

    public List<Classification> findAll() {
        return this.classificationRepository.findAll();
    }

    public Classification getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Classification getObjectById(Long id) {
        Optional<Classification> optionalClassification = classificationRepository.findById(id);
        return optionalClassification.orElseThrow(() -> new NotFoundCapEntrepriseException("Classification", "id", id));
    }

    public Classification getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("Classification", "slug", slug);
//        Optional<Classification> optionalClassification = classificationRepository.findBySlug(slug);
//        return optionalClassification.orElseThrow(() -> new NotFoundCapEntrepriseException("Classification", "slug", slug));
    }

    public Classification persist(ClassificationDTO classificationDTO) {
        return persist(classificationDTO, null);
    }

    public Classification persist(ClassificationDTO classificationDTO, Long id) {
        Classification classification = new Classification();
        if (id != null) {
            classification = getObjectById(id);
        }

        return classificationRepository.saveAndFlush(classification);
    }

    public ClassificationDTO getDTOById(Long id) {
        Classification classification = getObjectById(id);
        ClassificationDTO dto = new ClassificationDTO();
        // dto.setName(classification.getName());
        return dto;
    }
}
