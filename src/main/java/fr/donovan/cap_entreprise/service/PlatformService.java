package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Platform;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.repository.PlatformRepository;
import fr.donovan.cap_entreprise.DTO.PlatformDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlatformService implements DAOServiceInterface<Platform> {

    private PlatformRepository platformRepository;

    public List<Platform> findAll() {
        return this.platformRepository.findAll();
    }

    public List<Platform> findAllSorted() {
        return this.platformRepository.findAllByOrderByNameAsc();
    }

    public Page<Platform> findAll(Pageable pageable) {
        return this.platformRepository.findAll(pageable);
    }

    public Platform getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public Platform getObjectById(Long id) {
        Optional<Platform> optionalPlatform = platformRepository.findById(id);
        return optionalPlatform.orElseThrow(() -> new NotFoundCapEntrepriseException("Platform", "id", id));
    }

    public Platform getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("BusinessModel", "slug", slug);
//        Optional<Platform> optionalPlatform = platformRepository.findBySlug(slug);
//        return optionalPlatform.orElseThrow(() -> new NotFoundCapEntrepriseException("Platform", "slug", slug));
    }

    public Platform persist(PlatformDTO platformDTO) {
        return persist(platformDTO, null);
    }

    public Platform persist(PlatformDTO platformDTO, Long id) {
        Platform platform = new Platform();
        platform.setImage("https://static.vecteezy.com/system/resources/previews/005/337/799/original/icon-image-not-found-free-vector.jpg");
        if (id != null) {
            platform = getObjectById(id);
        }
        platform.setName(platformDTO.getName());

        return platformRepository.saveAndFlush(platform);
    }

    public PlatformDTO getDTOById(Long id) {
        Platform platform = getObjectById(id);
        PlatformDTO dto = new PlatformDTO();
        dto.setName(platform.getName());
//        dto.setImage(platform.getImage());
        return dto;
    }
}
