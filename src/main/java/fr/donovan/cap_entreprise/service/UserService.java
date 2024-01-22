package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Gamer;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.repository.UserRepository;
import fr.donovan.cap_entreprise.DTO.UserDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements DAOServiceInterface<User> {

    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return getObjectById(id);
        } catch (NumberFormatException e) {
            return getObjectBySlug(field);
        }
    }

    public User getObjectById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new NotFoundCapEntrepriseException("User", "id", id));
    }

    public User getObjectBySlug(String slug) {
        throw new NotFoundCapEntrepriseException("User", "slug", slug);
//        Optional<User> optionalUser = userRepository.findBySlug(slug);
//        return optionalUser.orElseThrow(() -> new NotFoundCapEntrepriseException("User", "slug", slug));
    }

    public User persist(UserDTO userDTO) {
        return persist(userDTO, null);
    }

    public User persist(UserDTO userDTO, Long id) {
        User user = new Gamer();
        if (id != null) {
            user = getObjectById(id);
        }

        return userRepository.saveAndFlush(user);
    }

    public UserDTO getDTOById(Long id) {
        User user = getObjectById(id);
        UserDTO dto = new UserDTO();
        // dto.setName(user.getName());
        return dto;
    }
}
