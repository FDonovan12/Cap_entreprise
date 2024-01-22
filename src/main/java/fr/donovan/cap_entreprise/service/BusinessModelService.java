package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.BusinessModel;
import fr.donovan.cap_entreprise.repository.BusinessModelRepository;
import fr.donovan.cap_entreprise.DTO.BusinessModelDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BusinessModelService implements DAOServiceInterface<BusinessModel> {

    private BusinessModelRepository businessModelRepository;

    public List<BusinessModel> findAll() {
        return this.businessModelRepository.findAll();
    }

    public BusinessModel getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public BusinessModel getObjectById(Long id) {
        Optional<BusinessModel> optionalBusinessModel = businessModelRepository.findById(id);
        return optionalBusinessModel.orElseThrow(() -> new NotFoundCapEntrepriseException("BusinessModel", "id", id));
    }

    public BusinessModel getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<BusinessModel> optionalBusinessModel = businessModelRepository.findBySlug(slug);
//        return optionalBusinessModel.orElseThrow(() -> new NotFoundCapEntrepriseException("BusinessModel", "slug", slug));
    }

    public BusinessModel persist(BusinessModelDTO businessModelDTO) {
        return persist(businessModelDTO, null);
    }

    public BusinessModel persist(BusinessModelDTO businessModelDTO, Long id) {
        BusinessModel businessModel = new BusinessModel();
        if (id != null) {
            businessModel = getObjectById(id);
        }

        return businessModelRepository.saveAndFlush(businessModel);
    }

    public BusinessModelDTO getDTOById(Long id) {
        BusinessModel businessModel = getObjectById(id);
        BusinessModelDTO dto = new BusinessModelDTO();
        // dto.setName(businessModel.getName());
        return dto;
    }
}
