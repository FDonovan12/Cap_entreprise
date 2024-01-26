package fr.donovan.cap_entreprise.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.cap_entreprise.entity.interfaces.EntityInterface;
import jakarta.persistence.*;
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
@Entity
public class Platform implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String image;

    @ManyToMany
    @JoinTable(name = "game_platform",
            joinColumns = @JoinColumn(name="platform_id"),
            inverseJoinColumns = @JoinColumn(name="game_id"))
    private List<Game> games = new ArrayList<>();
}