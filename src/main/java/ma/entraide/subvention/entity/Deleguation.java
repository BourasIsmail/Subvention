package ma.entraide.subvention.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deleguation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deleguation_id")
    private Long id;

    @Column(unique = true)
    private String nom;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "coordiantion_id")
    private Coordination coordination;
}
