package ma.entraide.subvention.controller;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.entity.Deleguation;
import ma.entraide.subvention.service.DeleguationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/deleguation")
public class DeleguationController {

    @Autowired
    private DeleguationService deleguationService;

    @GetMapping
    public ResponseEntity<List<Deleguation>> getAllDeleguation() {
        List<Deleguation> deleguation = deleguationService.getAllDeleguation();
        return new ResponseEntity<>(deleguation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deleguation> getDeleguationById(@PathVariable Long id) {
        try {
            Deleguation deleguation = deleguationService.getDeleguationById(id);
            return new ResponseEntity<>(deleguation, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byNom/{name}")
    public ResponseEntity<Deleguation> getDeleguationByNom(@PathVariable String name){
        Deleguation deleguation = deleguationService.getDeleguationByNom(name);
        return new ResponseEntity<>(deleguation, HttpStatus.OK);
    }

    @GetMapping("/byNomCoord/{name}")
    public ResponseEntity<List<Deleguation>> getDeleguationByNomCoord(@PathVariable String name){
        List<Deleguation> deleguations = deleguationService.getDeleguationByCoordination(name);
        return new ResponseEntity<>(deleguations, HttpStatus.OK);
    }

    @GetMapping("/byIdCoord/{id}")
    public ResponseEntity<List<Deleguation>> getDeleguationByNomCoord(@PathVariable Long id){
        try {
            List<Deleguation> deleguations = deleguationService.getDeleguationByCoordinationId(id);
            return new ResponseEntity<>(deleguations, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Deleguation> ajouterDeleguation(@RequestBody Deleguation newdeleguation){
        Deleguation deleguation = deleguationService.addDeleguation(newdeleguation);
        return new ResponseEntity<>(deleguation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deleguation> ajouterDeleguation(@PathVariable Long id,@RequestBody Deleguation newdeleguation){
        Deleguation deleguation = deleguationService.updateDeleguation(id,newdeleguation);
        return new ResponseEntity<>(deleguation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeleguation(@PathVariable Long id){
        deleguationService.deleteDeleguation(id);
        return ResponseEntity.ok("Deleguation supprimée");
    }

}
