package ma.entraide.subvention.service;

import ma.entraide.subvention.entity.AutreFonctionnaire;
import ma.entraide.subvention.entity.Demande;
import ma.entraide.subvention.repository.AutreFonctionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutreFonctionnaireService {
    @Autowired
    private AutreFonctionnaireRepository autreFonctionnaireRepository;

    @Autowired
    private DemandeService demandeService;


    public AutreFonctionnaire addAutreFonctionnaire(AutreFonctionnaire a) {
        return autreFonctionnaireRepository.save(a);
    }

public AutreFonctionnaire add(Long id, AutreFonctionnaire a) {
        Demande demande = demandeService.getDemandeById(id);
        a.setDemande(demande);
        return autreFonctionnaireRepository.save(a);
}


    public List<AutreFonctionnaire> getAllAutreFonctionnaire() {
        return autreFonctionnaireRepository.findAll();
    }

    public void delete(Long id){
        Optional<AutreFonctionnaire> a = autreFonctionnaireRepository.findById(id)
                .orElseThrow(()->);
    }
}
