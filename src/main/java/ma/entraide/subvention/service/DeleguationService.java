package ma.entraide.subvention.service;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.entity.Deleguation;
import ma.entraide.subvention.repository.DeleguationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeleguationService {

    @Autowired
    private DeleguationRepository deleguationRepository;

    @Autowired CoordinationService coordinationService;

    public List<Deleguation> getAllDeleguation(){
        return deleguationRepository.findAll();
    }

    public Deleguation getDeleguationById(Long id){
        Optional<Deleguation> deleguationOP = deleguationRepository.findById(id);
        Deleguation deleguation = null;
        if(deleguationOP.isPresent()){
            deleguation = deleguationOP.get();
        }
        else {
            throw new ResourceNotFoundException("Deleguation introuvable");
        }
        return deleguation;
    }

    public List<Deleguation> getDeleguationByCoordination(String coordination){
        try {
            return deleguationRepository.getDeleguationByCoordinationName(coordination);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Coordination introuvable");
        }
    }

    public List<Deleguation> getDeleguationByCoordinationId(Long id){
        return deleguationRepository.findByCoordinationId(id);
    }

    public Deleguation getDeleguationByNom(String nom){
       Optional<Deleguation> deleguationOP = deleguationRepository.findByNom(nom);
       Deleguation deleguation = null;
       if(deleguationOP.isPresent()){
           deleguation = deleguationOP.get();
       }
       else{
           throw new ResourceNotFoundException("Deleguation introuvable");
       }
       return deleguation;
    }


    public Deleguation addDeleguation(Deleguation deleguation){
        Coordination coordination = coordinationService.findCoordinationById(deleguation.getCoordination().getId());
        deleguation.setCoordination(coordination);
        return deleguationRepository.save(deleguation);
    }

    public String deleteDeleguation(Long id){
        deleguationRepository.deleteById(id);
        return "deleguation supprimée";
    }

    public Deleguation updateDeleguation(Long id, Deleguation newDeleguation){
        Deleguation deleguation = deleguationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("deleguation introuvable"));
        deleguation.setNom(newDeleguation.getNom());
        deleguation.setCoordination(newDeleguation.getCoordination());

        return deleguationRepository.save(deleguation);

    }

}
