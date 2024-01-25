package fr.donovan.cap_entreprise.DTO;

import fr.donovan.cap_entreprise.entity.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    @NotBlank(message = "This should be a valid name")
    private String name;

    @NotBlank(message = "This should be a valid description")
    private String description;

    @NotNull(message = "This should be a valid date")
    private String publishedAt;

    @NotBlank(message = "This should be a valid image")
    private String image;

    @NotNull(message = "This should be not null")
    private Classification classification;

    @NotNull(message = "This should be not null")
    private Genre genre;

    @NotNull(message = "This should be not null")
    private Publisher publisher;

    @NotNull(message = "This should be not null")
    private BusinessModel businessModel;

    private Moderator moderator;

    private List<Platform> platforms;
}