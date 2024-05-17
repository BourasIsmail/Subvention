package ma.entraide.subvention.repository;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.entity.Deleguation;
import ma.entraide.subvention.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    Optional<Demande> findByNomAssociation(String nomAssociation);

    Optional<Demande> findByCodeDemande(String codeDemande);

    @Query("SELECT d FROM Demande d where d.supprime = false ")
    List<Demande> findAllDemandesPresent();

    @Query("SELECT d from Demande d where d.supprime= true")
    List<Demande> findAllDemandesNotPresent();

    Optional<List<Demande>> findByEmailPresident(String emailPresident);

    @Query("SELECT d FROM Demande d WHERE d.deleguation.nom = :deleguation")
    List<Demande> findByDeleguationName(@Param("deleguation") String deleguation);

    // Custom query method to find demandes by emailPresident and year
    @Query("SELECT d FROM Demande d WHERE d.numAutorisation = :numAutorisation AND YEAR(d.dateDemande) = :year")
    List<Demande> findByNumAutorisationAndYear(@Param("numAutorisation") String numAutorisation, @Param("year") int year);

    @Query("SELECT d FROM Demande d WHERE d.deleguation.id = :id")
    List<Demande> findByDeleguation(Long id);

    @Query("SELECT d FROM Demande d WHERE d.coordination.id = :id")
    List<Demande> findByCoordination(Long id);

    @Query("SELECT COUNT(d) FROM Demande d ")
    int countDemandes();

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'قيد العمل'")
    int countDemandesEnCours();

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'موافق عليه'")
    int countDemandesAcceptees();

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'مرفوض'")
    int countDemandesRefusees();

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'مرفوض' AND d.coordination.id = :coordination")
    int countDemandesRefuseesByCoordination(@Param("coordination") Long coordination);

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'موافق عليه' AND d.coordination.id = :coordination")
    int countDemandesAccepteesByCoordination(@Param("coordination") Long coordination);

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'قيد العمل' AND d.coordination.id = :coordination")
    int countDemandesEnCoursByCoordination(@Param("coordination") Long coordination);

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'مرفوض' AND d.deleguation.id = :deleguation")
    int countDemandesRefuseesByDeleguation(@Param("deleguation") Long deleguation);

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'موافق عليه' AND d.deleguation.id = :deleguation")
    int countDemandesAccepteesByDeleguation(@Param("deleguation") Long deleguation);

    @Query("SELECT COUNT(d) FROM Demande d WHERE d.etat = 'قيد العمل' AND d.deleguation.id = :deleguation")
    int countDemandesEnCoursByDeleguation(@Param("deleguation") Long deleguation);

    @Query("SELECT d from Demande d WHERE d.coordination.nom = :coordination")
    int findByCoordinationName(@Param("coordination") Long coordination);

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'حضري'")
    int countByTypeMilieuUrbain();

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'قروي'")
    int countByTypeMilieuRural();

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'حضري' AND d.deleguation.id = :id")
    int countByTypeMilieuUrbainAndDeleguation(@Param("id") Long id);

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'قروي' AND d.deleguation.id = :id")
    int countByTypeMilieuRuralAndDeleguation(@Param("id") Long id);

    @Query("SELECT COUNT(d) from Demande d WHERE d.deleguation.id = :id")
    int countByDeleguationId(@Param("id") Long id);

    @Query("SELECT COUNT(d) from Demande d WHERE d.coordination.id = :id")
    int countByCoordinationId(@Param("id") Long id);

//    @Query("SELECT COALESCE(SUM(d.nbrAgentsHommes),0) from Demande d")
//    int sumNbrAgentsHommes();
//
//    @Query("SELECT COALESCE(SUM(d.nbrAgentsFemmes),0) from Demande d")
//    int sumNbrAgentsFemmes();

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesHommes),0) from Demande d")
    int sumNbrBeneficiairesHommes();

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesFemmes),0) from Demande d")
    int sumNbrBeneficiairesFemmes();

    @Query("SELECT COALESCE(SUM(d.nbrTotalAgents),0) from Demande d")
    int sumNbrTotalAgents();

    @Query("SELECT COALESCE(SUM(d.nbrTotalBeneficiaires),0) from Demande d")
    int sumNbrTotalBeneficiaires();

    @Query("SELECT COALESCE(SUM(d.nbrTotalBeneficiaires),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrTotalBeneficiairesByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrTotalAgents),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrTotalAgentsByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesHommes),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrBeneficiairesHommesByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesFemmes),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrBeneficiairesFemmesByCoordination(@Param("id") Long id);

//    @Query("SELECT COALESCE(SUM(d.nbrAgentsHommes),0) from Demande d WHERE d.coordination.id = :id")
//    int sumNbrAgentsHommesByCoordination(@Param("id") Long id);
//
//    @Query("SELECT COALESCE(SUM(d.nbrAgentsFemmes),0) from Demande d WHERE d.coordination.id = :id")
//    int sumNbrAgentsFemmesByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrTotalBeneficiaires),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrTotalBeneficiairesByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrTotalAgents),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrTotalAgentsByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesHommes),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrBeneficiairesHommesByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesFemmes),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrBeneficiairesFemmesByDeleguation(@Param("id") Long id);

//    @Query("SELECT COALESCE(SUM(d.nbrAgentsHommes),0) from Demande d WHERE d.deleguation.id = :id")
//    int sumNbrAgentsHommesByDeleguation(@Param("id") Long id);
//
//    @Query("SELECT COALESCE(SUM(d.nbrAgentsFemmes),0) from Demande d WHERE d.deleguation.id = :id")
//    int sumNbrAgentsFemmesByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrTotalBeneficiaires),0) from Demande d WHERE d.typeMilieu = :typeMilieu")
    int sumNbrTotalBeneficiairesByTypeMilieu(@Param("typeMilieu") String typeMilieu);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesHommes),0) from Demande d WHERE d.typeMilieu = :typeMilieu")
    int sumNbrBeneficiairesHommesByTypeMilieu(@Param("typeMilieu") String typeMilieu);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesFemmes),0) from Demande d WHERE d.typeMilieu = :typeMilieu")
    int sumNbrBeneficiairesFemmesByTypeMilieu(@Param("typeMilieu") String typeMilieu);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceMatinal),0) from Demande d")
    int sumNbrBeneficiairesServiceMatinal();

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServicePartiel),0) from Demande d")
    int sumNbrBeneficiairesServicePartiel();

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceTotal),0) from Demande d")
    int sumNbrBeneficiairesServiceTotal();

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceMatinal),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrBeneficiairesServiceMatinalByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServicePartiel),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrBeneficiairesServicePartielByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceTotal),0) from Demande d WHERE d.coordination.id = :id")
    int sumNbrBeneficiairesServiceTotalByCoordination(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceMatinal),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrBeneficiairesServiceMatinalByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServicePartiel),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrBeneficiairesServicePartielByDeleguation(@Param("id") Long id);

    @Query("SELECT COALESCE(SUM(d.nbrBeneficiairesServiceTotal),0) from Demande d WHERE d.deleguation.id = :id")
    int sumNbrBeneficiairesServiceTotalByDeleguation(@Param("id") Long id);

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'حضري' AND d.coordination.id = :coordination")
    int countByTypeMilieuUrbainAndCoordination(Long coordination);

    @Query("SELECT COUNT(d) from Demande d WHERE d.typeMilieu = 'قروي' AND d.coordination.id = :coordination")
    int countByTypeMilieuRuralAndCoordination(Long coordination);



}
