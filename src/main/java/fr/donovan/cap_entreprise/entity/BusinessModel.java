package fr.donovan.cap_entreprise.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.cap_entreprise.entity.interfaces.EntityInterface;
import fr.donovan.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.donovan.cap_entreprise.entity.interfaces.SluggerInterface;
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
public class BusinessModel implements EntityInterface, NomenclatureInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String image;

    @OneToMany(mappedBy = "businessModel")
    private List<Game> games = new ArrayList<>();

}