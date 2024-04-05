package ma.entraide.subvention.controller;

import ma.entraide.subvention.entity.Demande;
import ma.entraide.subvention.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ma.entraide.subvention.Exceptions.ErrorResponse;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/demande")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public ResponseEntity<List<Demande>> getAllDemandes(){
        List<Demande> demandes = demandeService.getAllDemande();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id){
        try {
            Demande demande = demandeService.getDemandeById(id);
            return new ResponseEntity<>(demande, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Demande> getDemandeByCode(@PathVariable String code){
        try {
            Demande demande = demandeService.getDemandeByCode(code);
            return new ResponseEntity<>(demande, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addDemande")
    public ResponseEntity<?> ajouterDemande(@RequestBody Demande newDemande) {
        try {
            Demande demande = demandeService.addDemande(newDemande);
            return new ResponseEntity<>(demande, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle the exception here and return an appropriate error response
            String errorMessage = "An error occurred while adding demande: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessage));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Demande> updateDemande(@PathVariable Long id, @RequestBody Demande newdemande){
        Demande demande = demandeService.updateDemande(id, newdemande);
        return new ResponseEntity<>(demande,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDemande(@PathVariable("id") Long id){
        demandeService.deleteDemande(id);
        return ResponseEntity.ok("demande supprimée");
    }

    @GetMapping("/countDemandesEnCours")
    public ResponseEntity<Integer> countDemandesEnCours(){
        int count = demandeService.demandesEnCours();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/countDemandesAcceptees")
    public ResponseEntity<Integer> countDemandesAcceptees(){
        int count = demandeService.demandesAcceptees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/countDemandesRefusees")
    public ResponseEntity<Integer> countDemandesRefusees(){
        int count = demandeService.demandesRefusees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/byCoordination/{coordination}")
    public ResponseEntity<List<Demande>> getDemandeByCoordination(@PathVariable Long coordination){
        List<Demande> demandes = demandeService.getDemandeByCoordination(coordination);
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/byDeleguation/{deleguation}")
    public ResponseEntity<List<Demande>> getDemandeByDeleguation(@PathVariable Long deleguation){
        List<Demande> demandes = demandeService.getDemandeByDeleguation(deleguation);
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Object> dashBoardData(@RequestParam(name = "delegiationId", required = false) Long delegiationId, @RequestParam(name = "coordinationId", required = false) Long coordinationId){
        Object data = demandeService.dashBoardData(delegiationId, coordinationId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }


}
