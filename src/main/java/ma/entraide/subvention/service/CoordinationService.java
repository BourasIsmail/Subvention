package ma.entraide.subvention.service;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.repository.CoordinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinationService {

    @Autowired
    private CoordinationRepository coordinationRepository;

    public Coordination findCoordinationById(Long id){
        Optional<Coordination> coordinationOp = coordinationRepository.findById(id);
        Coordination coordination = null;
        if(coordinationOp.isPresent()){
            coordination = coordinationOp.get();
        }
        else {
            throw new ResourceNotFoundException("coordination introuvable");
        }
        return coordination;
    }


    public Coordination findCoordinationByNom(String nom){
        Optional<Coordination> coordinationOp = coordinationRepository.findByNom(nom);
        Coordination coordination = null;
        if(coordinationOp.isPresent()){
            coordination = coordinationOp.get();
        }
        else {
            throw new ResourceNotFoundException("coordination introuvable");
        }
        return coordination;
    }

    public List<Coordination> findAllCoordination(){
        return coordinationRepository.findAll();
    }

    public Coordination addCoordination(Coordination coordination){
        return coordinationRepository.save(coordination);
    }

    public Coordination updateCoordination(Long id,Coordination newCoordination){
        Coordination coordination = coordinationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Coordination introuvable"));
        coordination.setNom(newCoordination.getNom());
        return coordinationRepository.save(coordination);
    }

    public void deleteCoordinationById(Long id){
        coordinationRepository.deleteById(id);
    }


}
