package ma.entraide.subvention.controller;

import ma.entraide.subvention.entity.AutreFonctionnaire;
import ma.entraide.subvention.service.AutreFonctionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/fonctionnaire")
public class AutreFonctionnaireController {
    @Autowired
    private AutreFonctionnaireService service;

    @GetMapping
    public List<AutreFonctionnaire> getAll() {
        return service.getAllAutreFonctionnaire();
    }




}
