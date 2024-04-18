package ma.entraide.subvention.controller;

import ma.entraide.subvention.entity.Demande;
import ma.entraide.subvention.entity.ResponseData;
import ma.entraide.subvention.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ma.entraide.subvention.Exceptions.ErrorResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

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


    @PutMapping(value = "/upload/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseData uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file) {
        String downloadUrl = "";
        Demande demande = demandeService.uploadFile(id, file);
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/demande/download/"+ demande.getId())
                .toUriString();
        return new ResponseData(demande.getFileName(),
                downloadUrl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Demande demande = null;
        demande = demandeService.getDemandeById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(demande.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + demande.getFileName() + "\"")
                .body(new ByteArrayResource(demande.getZipData()));
    }

    @PutMapping("/restaurer/{id}")
    public ResponseEntity<Demande> restaurer(@PathVariable Long id){
        return new ResponseEntity<>(demandeService.desarchiveDemande(id), HttpStatus.OK);
    }

}
