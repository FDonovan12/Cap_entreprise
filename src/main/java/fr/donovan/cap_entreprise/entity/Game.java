package fr.donovan.cap_entreprise.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.cap_entreprise.entity.interfaces.EntityInterface;
import jakarta.persistence.*;
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
@Entity
public class Game implements EntityInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate publishedAt;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    private Classification classification;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private BusinessModel businessModel;

    @ManyToOne
    private Moderator moderator;

    @ManyToMany
    @JoinTable(name = "game_platform",
            joinColumns = @JoinColumn(name="game_id"),
            inverseJoinColumns = @JoinColumn(name="platform_id"))
    private List<Platform> platforms = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)

    private List<Review> reviews = new ArrayList<>();

    public void addPlatform(Platform platform) {
        if (!platforms.contains(platform)) {
            platforms.add(platform);
        }
    }
}