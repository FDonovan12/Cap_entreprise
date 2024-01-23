package fr.donovan.cap_entreprise.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @NotBlank(message = "This should be a valid description")
    private String description;

    @NotNull(message = "This should be a valid rating")
    @Min(value = 0, message = "This have to be greater or equals than 0")
    @Max(value = 20, message = "This have to be lower or equals than 20")
    private float rating;

    @NotBlank(message = "This should be a valid image")
    private String image;

    @Min(value = 1, message = "This have to be greater than 0")
    @NotNull(message = "This should be a valid game")
    private Long game_id;

    @Min(value = 1, message = "This have to be greater than 0")
    @NotNull(message = "This should be a valid gamer")
    private Long gamer_id;
}