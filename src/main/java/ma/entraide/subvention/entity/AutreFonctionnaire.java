package ma.entraide.subvention.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutreFonctionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autreFonctionnaire_id")
    private Long id;

    private String nom;

    private int nbrHomme;

    private int nbrFemme;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    @JsonIgnore
    private Demande demande;

}
