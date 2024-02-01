package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.BusinessModel;
import fr.donovan.cap_entreprise.entity.Classification;
import fr.donovan.cap_entreprise.repository.BusinessModelRepository;
import fr.donovan.cap_entreprise.DTO.BusinessModelDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<BusinessModel> findAllSorted() {
        return this.businessModelRepository.findAllByOrderByNameAsc();
    }

    public Page<BusinessModel> findAll(Pageable pageable) {
        return this.businessModelRepository.findAll(pageable);
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
        businessModel.setImage("https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg");
        if (id != null) {
            businessModel = getObjectById(id);
        }
        businessModel.setName(businessModelDTO.getName());

        return businessModelRepository.saveAndFlush(businessModel);
    }

    public BusinessModelDTO getDTOById(Long id) {
        BusinessModel businessModel = getObjectById(id);
        BusinessModelDTO dto = new BusinessModelDTO();
        dto.setName(businessModel.getName());
        return dto;
    }
}
