package fr.donovan.cap_entreprise.service;

import fr.donovan.cap_entreprise.entity.Gamer;
import fr.donovan.cap_entreprise.entity.Moderator;
import fr.donovan.cap_entreprise.entity.Review;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.repository.UserRepository;
import fr.donovan.cap_entreprise.DTO.UserDTO;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements DAOServiceInterface<User>, UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public List<User> findAllModerator() {
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

    public User getObjectByNickname(String nickname) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        return optionalUser.orElseThrow(() -> new NotFoundCapEntrepriseException("User", "nickname", nickname));
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
        Gamer user = new Gamer();
//        if (id != null) {
//            user = getObjectById(id);
//        }
        user.setNickname(userDTO.getNickname());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
//        user.setBirthAt(LocalDate.parse(userDTO.getBirthAt()));
        user.setBirthAt(userDTO.getBirthAt());

        return userRepository.saveAndFlush(user);
    }

    public UserDTO getDTOById(Long id) {
        User user = getObjectById(id);
        UserDTO dto = new UserDTO();
        // dto.setName(user.getName());
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getObjectByNickname(username);

        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }

    public User eccentric(String name) {
        User user = getObjectByNickname(name);
        user.setEccentric(!user.isEccentric());
        return userRepository.saveAndFlush(user);
    }

    public User veryEccentric(String name) {
        User user = getObjectByNickname(name);
        user.setVeryEccentric(!user.isVeryEccentric());
        return userRepository.saveAndFlush(user);
    }
}
