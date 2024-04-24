package ma.entraide.subvention.controller;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.service.CoordinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/coordination")
public class CoordinationController {

    @Autowired
    private CoordinationService coordinationService;

    @GetMapping
    public ResponseEntity<List<Coordination>> getAllCoordination() {
        List<Coordination> coordinations = coordinationService.findAllCoordination();
        return new ResponseEntity<>(coordinations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordination> getCoordinationById(@PathVariable Long id) {
        Coordination coordination = coordinationService.findCoordinationById(id);
        return new ResponseEntity<>(coordination, HttpStatus.OK);
    }

    @GetMapping("/byNom/{name}")
    public ResponseEntity<Coordination> getCoordinationById(@PathVariable String name) {
        Coordination coordination = coordinationService.findCoordinationByNom(name);
        return new ResponseEntity<>(coordination, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Coordination> ajouterCoordination(@RequestBody Coordination newCoordination){
        Coordination coordination = coordinationService.addCoordination(newCoordination);
        return new ResponseEntity<>(coordination, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordination> updateCoordination(@PathVariable Long id, @RequestBody Coordination newCoordination){
        Coordination coordination = coordinationService.updateCoordination(id,newCoordination);
        return new ResponseEntity<>(coordination, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoordination(@PathVariable Long id){
        coordinationService.deleteCoordinationById(id);
        return ResponseEntity.ok("Coordination supprimée");
    }

}