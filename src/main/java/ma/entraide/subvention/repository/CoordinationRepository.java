package ma.entraide.subvention.repository;

import ma.entraide.subvention.entity.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinationRepository extends JpaRepository<Coordination, Long> {
    Optional<Coordination> findByNom(String nom);
}
