package ma.entraide.subvention.repository;

import ma.entraide.subvention.entity.AutreFonctionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutreFonctionnaireRepository extends JpaRepository<AutreFonctionnaire, Long> {
}
