package ma.entraide.subvention.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.zip.ZipFile;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demande_id")
    private Long id;

    //champs de la demande de subvention remplie par l'association

    @Column(nullable = false)
    private String nomAssociation;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "deleguation_id")
    private Deleguation deleguation;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "coordiantion_id")
    private Coordination coordination;

    @Column(nullable = false)
    private String numAutorisation;

    @Column(nullable = false)
    private String addresse;

    @Column(nullable = false)
    private String telephonePresident;

    @Column(nullable = false)
    private String emailPresident;

    @Column(nullable = false)
    private String nomPresident;

    @Column(nullable = false)
    private int nbrBeneficiairesHommes;

    @Column(nullable = false)
    private int nbrBeneficiairesFemmes;

    @Column(nullable = false)
    private int nbrAgentsHommes;

    @Column(nullable = false)
    private int nbrAgentsFemmes;

    @Column(nullable = false)
    private String sujetDemande;

    @Column(nullable = false)
    private String nomEtablissement;

    @Column(nullable = false)
    private String nomDirecteur;

    @Column(nullable = false)
    private String telDirecteur;

    @Column(nullable = false)
    private String emailDirecteur;

    //champs generé automatiquement
    private Date dateDemande;

    private int nbrTotalBeneficiaires;

    private int nbrTotalAgents;

    //champs unique
    @Column(unique = true)
    private String codeDemande;

    //champs de la demande de subvention remplie par la delegation

    private String rib;

    private String capaciteChargeTotal;

    private int nbrBeneficiairesServiceTotal;

    private int nbrBeneficiairesServiceMatinal;

    private int nbrBeneficiairesServicePartiel;


    private Date dateCollecte;

    private int dureeValidite;

    private double revenuTotalAnneePrecedente;

    private double recetteTotalAnneePrecedente;

    private String etat;

    //urban ou rural

    private String typeMilieu;

    //Zipe file

    @Lob
    private byte[] zipData;

    private String fileName;

    private String fileType;


}
