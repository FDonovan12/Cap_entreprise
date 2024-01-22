package fr.donovan.cap_entreprise.DTO;
import fr.donovan.cap_entreprise.repository.PublisherRepository;
import fr.donovan.cap_entreprise.validator.annotation.UniqueName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublisherDTO {

    @NotBlank(message = "This should be a valid name")
    private String name;

}