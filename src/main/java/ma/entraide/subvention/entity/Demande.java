package ma.entraide.subvention.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name = "autreFonctionnaire_id")
    private List<AutreFonctionnaire> autreFonctionnaire;

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
    private int nbrAgentsHommesEN;

    @Column(nullable = false)
    private int nbrAgentsFemmesEN;

    @Column(nullable = false)
    private int nbrAgentsHommesAssociation;

    @Column(nullable = false)
    private int nbrAgentsFemmesAssociation;

    @Column(nullable = false)
    private int nbrAgentsHommesReanimationNational;

    @Column(nullable = false)
    private int nbrAgentsFemmesReanimationNational;

    @Column(nullable = false)
    private int nbrAgentsHommesCollectiviteTerritoriales;

    @Column(nullable = false)
    private int nbrAgentsFemmesCollectiviteTerritoriales;


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

    @Column(nullable = false)
    private int capacitePourHomme;

    @Column(nullable = false)
    private int capacitePourFemmes;

    //champs generé automatiquement
    private Date dateDemande;

    private int nbrTotalBeneficiaires;

    private int nbrTotalAgentsEN;

    private int nbrTotalAgentsAssociation;

    private int nbrTotalAgentsReanimationNational;

    private int nbrTotalAgentsCollectiviteTerritoriales;


    private int nbrTotalAgents;

    private String capaciteChargeTotal;

    //champs unique
    @Column(unique = true)
    private String codeDemande;

    @Column(nullable = false)
    private boolean supprime;

    private Date dateSuppression;

    private Date dateDerniereModification;

    //champs de la demande de subvention remplie par la delegation

    private String rib;


    private int nbrBeneficiairesServiceTotal;

    private int nbrBeneficiairesServiceMatinal;

    private int nbrBeneficiairesServicePartiel;


    private String dateCollecte;

    private int dureeValidite;

    private double revenuTotalAnneePrecedente;

    private double recetteTotalAnneePrecedente;

    private String etat;

    //urban ou rural

    private String typeMilieu;

    //Zipe file

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] zipData;

    private String fileName;

    private String fileType;

    private List<String> cible = new ArrayList<>();

    private double montantSuggereParAssoc;

    private double montantSuggereParDeleg;

    //nombre de personne dans chaque categorie cible

    private int age;

    private int enfantNeglige;

    private int enfantSitDifficile;

    private int etudiant;

    private int personneSitDifficile;

    private int personneHandicape;

    private int femmeSitDifficile;



}
