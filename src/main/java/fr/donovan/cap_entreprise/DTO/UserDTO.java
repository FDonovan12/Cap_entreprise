package fr.donovan.cap_entreprise.DTO;

import fr.donovan.cap_entreprise.repository.UserRepository;
import fr.donovan.cap_entreprise.validator.annotation.UniqueName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "This should be a valid nickname")
    protected String nickname;

    @NotBlank(message = "This should be a valid password")
    protected String password;

    @NotBlank(message = "This should be a valid email")
    protected String email;

    @NotBlank(message = "This should be a valid birthAt")
    private LocalDate birthAt;

}