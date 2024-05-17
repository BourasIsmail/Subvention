package ma.entraide.subvention.entity;

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
}
