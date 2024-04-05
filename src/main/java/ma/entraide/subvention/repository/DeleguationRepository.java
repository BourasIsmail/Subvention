package ma.entraide.subvention.repository;

import ma.entraide.subvention.entity.Deleguation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeleguationRepository extends JpaRepository<Deleguation, Long> {
    Optional<Deleguation> findByNom(String nom);

    @Query("SELECT d from Deleguation d WHERE d.coordination.nom = :nomCoordination")
    public List<Deleguation> getDeleguationByCoordinationName(@Param("nomCoordination")String nomCoordination);

    public List<Deleguation> findByCoordinationId(Long nomCoordination);
}
